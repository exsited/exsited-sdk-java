package com.exsited.config;

import com.exsited.errors.SdkConfigError;
import com.exsited.errors.SdkUsageError;
import com.exsited.http.RetryPolicy;
import com.exsited.Redaction;

import java.net.http.HttpClient;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/** Immutable SDK configuration: timeouts, retries, TLS, logging, printing. */
public final class SdkConfig {

    public static final String DEFAULT_API_VERSION = ApiVersion.V4.pathSegment();
    public static final double DEFAULT_TIMEOUT_SECONDS = 30.0;
    public static final double DEFAULT_CONNECT_TIMEOUT_SECONDS = 10.0;
    /** Total attempts including the first, so 1 disables retrying. */
    public static final int DEFAULT_MAX_RETRIES = 1;
    /** Aliases {@link RetryPolicy#DEFAULT_RETRYABLE_HTTP_CODES}, so the two cannot drift. */
    public static final Set<Integer> DEFAULT_RETRYABLE_HTTP_CODES =
            RetryPolicy.DEFAULT_RETRYABLE_HTTP_CODES;
    /** Aliases {@link RetryPolicy#DEFAULT_UNSAFE_RETRYABLE_HTTP_CODES}. */
    public static final Set<Integer> DEFAULT_UNSAFE_RETRYABLE_HTTP_CODES =
            RetryPolicy.DEFAULT_UNSAFE_RETRYABLE_HTTP_CODES;
    public static final double DEFAULT_RETRY_BACKOFF_SECONDS = 0.5;
    /** Default ceiling on one wait, in seconds; bounds a server's {@code Retry-After} too. */
    public static final double DEFAULT_RETRY_BACKOFF_MAX_SECONDS = 20.0;
    public static final int DEFAULT_POOL_MAXSIZE = 10;
    public static final long DEFAULT_MAX_RESPONSE_BYTES = 64L * 1024 * 1024;
    /** The encoded body with framing, not the sources. */
    public static final long DEFAULT_MAX_REQUEST_BYTES = 64L * 1024 * 1024;
    /** Every file read, every {@code byte[]} and every form field's wire text together. */
    public static final long DEFAULT_MAX_UPLOAD_BYTES = 64L * 1024 * 1024;
    /** Every boundary line and part header block, none of the content. */
    public static final long DEFAULT_MAX_MULTIPART_OVERHEAD_BYTES = 1024L * 1024;
    /** 8 MiB; a full system root bundle is a few hundred kilobytes. */
    public static final long DEFAULT_MAX_CA_BUNDLE_BYTES = 8L * 1024 * 1024;
    /** Nothing routine is recorded until a caller lowers it. */
    public static final String DEFAULT_LOG_LEVEL = "WARNING";
    public static final String PRINT_FORMAT_PRETTY = "pretty";
    public static final String PRINT_FORMAT_COMPACT = "compact";
    public static final String DEFAULT_PRINT_FORMAT = PRINT_FORMAT_PRETTY;

    /** {@code responseFormat}: {@code getData()} is the decoded map, list or bytes. */
    public static final String RESPONSE_FORMAT_MAP = ResponseFormat.MAP.value();
    /** {@code responseFormat}: {@code getData()} is the body's own text. */
    public static final String RESPONSE_FORMAT_JSON = ResponseFormat.JSON.value();
    /** {@code responseFormat}: {@code getData()} is the typed view. */
    public static final String RESPONSE_FORMAT_DTO = ResponseFormat.DTO.value();
    public static final String DEFAULT_RESPONSE_FORMAT = RESPONSE_FORMAT_MAP;
    /** The lowest-priority tier: anything set in code wins over it. */
    public static final String RESPONSE_FORMAT_ENVIRONMENT_VARIABLE = "EXSITED_RESPONSE_FORMAT";

    /** A sentinel that never looks like a release. Do not change it to a version number. */
    private static final String FALLBACK_VERSION = "0.0.0+source";

    /** From the jar manifest when packaged; the source-tree sentinel otherwise. */
    public static final String SDK_VERSION = readImplementationVersion();
    public static final String DEFAULT_USER_AGENT = "exsited-java-sdk/" + SDK_VERSION;

    /** In severity order, which is the order the message lists them in. */
    private static final List<String> VALID_LOG_LEVELS =
            List.of("DEBUG", "INFO", "WARNING", "ERROR", "CRITICAL");
    private static final Set<String> VALID_PRINT_FORMATS =
            Set.of(PRINT_FORMAT_PRETTY, PRINT_FORMAT_COMPACT);
    /** The three response formats, in the order every refusal lists them. */
    public static final List<String> RESPONSE_FORMATS =
            List.of(RESPONSE_FORMAT_MAP, RESPONSE_FORMAT_JSON, RESPONSE_FORMAT_DTO);
    /** ASCII digits with no leading zero: {@code v04} is not a mounted version. */
    private static final Pattern API_VERSION_PATTERN =
            Pattern.compile("^v?[1-9][0-9]*$", Pattern.CASE_INSENSITIVE);
    /** 100-599 is every status there is; the ceiling bounds a runaway collection. */
    private static final int MAX_HTTP_CODES = 500;

    private final AuthConfig auth;
    private final String apiVersion;
    private final double timeoutSeconds;
    private final double connectTimeoutSeconds;
    private final int maxRetries;
    private final Set<Integer> retryableHttpCodes;
    private final Set<Integer> unsafeRetryableHttpCodes;
    private final double retryBackoffSeconds;
    private final double retryBackoffMaxSeconds;
    private final boolean verifySsl;
    private final String caBundle;
    private final String proxyUrl;
    private final int poolMaxsize;
    private final long maxResponseBytes;
    private final long maxRequestBytes;
    private final long maxUploadBytes;
    private final long maxMultipartOverheadBytes;
    private final long maxCaBundleBytes;
    private final Map<String, String> defaultHeaders;
    private final boolean raiseOnError;
    private final String responseFormat;
    private final String userAgent;
    private final String logLevel;
    private final boolean logBodies;
    private final String logFile;
    private final boolean printApiInfo;
    private final boolean printRequestPayload;
    private final boolean printResponseBody;
    private final boolean printHeaders;
    private final boolean printCurl;
    private final boolean printOnlyOnError;
    private final String printFormat;
    private final boolean printColor;
    private final int printMaxChars;
    private final HttpClient httpClient;

    private SdkConfig(Builder builder) {
        this.auth = builder.auth;
        this.apiVersion = normaliseApiVersion(builder.apiVersion);
        this.timeoutSeconds = builder.timeoutSeconds;
        this.connectTimeoutSeconds = builder.connectTimeoutSeconds;
        this.maxRetries = builder.maxRetries;
        // Not Set.copyOf: it throws a bare NullPointerException before validate() can report it.
        this.retryableHttpCodes = copyCodes(builder.retryableHttpCodes);
        this.unsafeRetryableHttpCodes = copyCodes(builder.unsafeRetryableHttpCodes);
        this.retryBackoffSeconds = builder.retryBackoffSeconds;
        this.retryBackoffMaxSeconds = builder.retryBackoffMaxSeconds;
        this.verifySsl = builder.verifySsl;
        this.caBundle = builder.caBundle;
        this.proxyUrl = builder.proxyUrl;
        this.poolMaxsize = builder.poolMaxsize;
        this.maxResponseBytes = builder.maxResponseBytes;
        this.maxRequestBytes = builder.maxRequestBytes;
        this.maxUploadBytes = builder.maxUploadBytes;
        this.maxMultipartOverheadBytes = builder.maxMultipartOverheadBytes;
        this.maxCaBundleBytes = builder.maxCaBundleBytes;
        this.defaultHeaders = java.util.Collections.unmodifiableMap(
                new LinkedHashMap<>(builder.defaultHeaders));
        this.raiseOnError = builder.raiseOnError;
        // Judged in validate() rather than here, so it is reported beside every other problem.
        this.responseFormat = foldResponseFormat(builder.responseFormat);
        this.userAgent = builder.userAgent;
        this.logLevel = builder.logLevel == null
                ? null : builder.logLevel.strip().toUpperCase(Locale.ROOT);
        this.logBodies = builder.logBodies;
        this.logFile = builder.logFile;
        this.printApiInfo = builder.printApiInfo;
        this.printRequestPayload = builder.printRequestPayload;
        this.printResponseBody = builder.printResponseBody;
        this.printHeaders = builder.printHeaders;
        this.printCurl = builder.printCurl;
        this.printOnlyOnError = builder.printOnlyOnError;
        this.printFormat = builder.printFormat == null
                ? null : builder.printFormat.strip().toLowerCase(Locale.ROOT);
        this.printColor = builder.printColor;
        this.printMaxChars = builder.printMaxChars;
        this.httpClient = builder.httpClient;
        validate();
    }

    /** The credentials are fixed here, the one thing a builder cannot be told later. */
    public static Builder builder(AuthConfig auth) {
        return new Builder(auth);
    }

    /** This config is untouched, and {@code responseFormat} comes across already resolved. */
    public Builder toBuilder() {
        Builder copy = new Builder(auth);
        copy.apiVersion = apiVersion;
        copy.timeoutSeconds = timeoutSeconds;
        copy.connectTimeoutSeconds = connectTimeoutSeconds;
        copy.maxRetries = maxRetries;
        copy.retryableHttpCodes = retryableHttpCodes;
        copy.unsafeRetryableHttpCodes = unsafeRetryableHttpCodes;
        copy.retryBackoffSeconds = retryBackoffSeconds;
        copy.retryBackoffMaxSeconds = retryBackoffMaxSeconds;
        copy.verifySsl = verifySsl;
        copy.caBundle = caBundle;
        copy.proxyUrl = proxyUrl;
        copy.poolMaxsize = poolMaxsize;
        copy.maxResponseBytes = maxResponseBytes;
        copy.maxRequestBytes = maxRequestBytes;
        copy.maxUploadBytes = maxUploadBytes;
        copy.maxMultipartOverheadBytes = maxMultipartOverheadBytes;
        copy.maxCaBundleBytes = maxCaBundleBytes;
        copy.defaultHeaders = new LinkedHashMap<>(defaultHeaders);
        copy.raiseOnError = raiseOnError;
        copy.responseFormat = responseFormat;
        copy.userAgent = userAgent;
        copy.logLevel = logLevel;
        copy.logBodies = logBodies;
        copy.logFile = logFile;
        copy.printApiInfo = printApiInfo;
        copy.printRequestPayload = printRequestPayload;
        copy.printResponseBody = printResponseBody;
        copy.printHeaders = printHeaders;
        copy.printCurl = printCurl;
        copy.printOnlyOnError = printOnlyOnError;
        copy.printFormat = printFormat;
        copy.printColor = printColor;
        copy.printMaxChars = printMaxChars;
        copy.httpClient = httpClient;
        return copy;
    }

    public SdkConfig withOverrides(java.util.function.UnaryOperator<Builder> overrides) {
        Builder builder = toBuilder();
        return (overrides == null ? builder : overrides.apply(builder)).build();
    }

    public static SdkConfig fromCredentials(List<?> credentials) {
        return fromCredentials(credentials, null, null);
    }

    public static SdkConfig fromCredentials(List<?> credentials, Object instance) {
        return fromCredentials(credentials, instance, null);
    }

    /** {@code instance}: {@code null}, a position (negative counts from the end), or a {@code clientName}. */
    public static SdkConfig fromCredentials(List<?> credentials, Object instance,
            java.util.function.UnaryOperator<Builder> overrides) {
        Object chosen = selectCredentials(credentials, instance);
        if (chosen instanceof SdkConfig config) {
            return overrides == null ? config : overrides.apply(config.toBuilder()).build();
        }
        if (chosen instanceof AuthConfig auth) {
            Builder builder = builder(auth);
            return (overrides == null ? builder : overrides.apply(builder)).build();
        }
        Builder builder = fromMapping((Map<?, ?>) chosen, instance);
        return (overrides == null ? builder : overrides.apply(builder)).build();
    }

    /** Build a builder from a mapping of config fields, the way a loader hands them over. */
    private static Builder fromMapping(Map<?, ?> block, Object instance) {
        String where = "credentials[" + (instance == null ? "0"
                : instance instanceof String name ? "'" + name + "'" : instance) + "]";
        Map<String, Object> remaining = new LinkedHashMap<>();
        for (Map.Entry<?, ?> entry : block.entrySet()) {
            if (!(entry.getKey() instanceof String key)) {
                throw new SdkConfigError(where + ": a config field name must be text, got "
                        + (entry.getKey() == null ? "null"
                                : entry.getKey().getClass().getSimpleName()));
            }
            remaining.put(key, entry.getValue());
        }

        boolean carriesAuth = remaining.containsKey("auth");
        Object suppliedAuth = remaining.remove("auth");

        Set<String> authNames = authFieldNames();
        Map<String, Object> authValues = new LinkedHashMap<>();
        for (String name : new ArrayList<>(remaining.keySet())) {
            if (authNames.contains(name)) {
                authValues.put(name, remaining.remove(name));
            }
        }

        if (carriesAuth && !authValues.isEmpty()) {
            throw new SdkConfigError("auth: an AuthConfig was supplied alongside the "
                    + "credential field(s) " + String.join(", ",
                            new java.util.TreeSet<>(authValues.keySet()))
                    + "; pass one or the other");
        }

        AuthConfig auth;
        if (carriesAuth) {
            if (!(suppliedAuth instanceof AuthConfig supplied)) {
                throw new SdkConfigError(where + ": auth must be an AuthConfig, got "
                        + (suppliedAuth == null ? "null"
                                : suppliedAuth.getClass().getSimpleName()));
            }
            auth = supplied;
        } else {
            // AuthConfig's own build() reports every missing credential, so none is listed here.
            AuthConfig.Builder authBuilder = AuthConfig.builder();
            for (Map.Entry<String, Object> entry : authValues.entrySet()) {
                assign(authBuilder, entry.getKey(), entry.getValue(), "credentials");
            }
            auth = authBuilder.build();
        }

        Builder builder = builder(auth);
        for (Map.Entry<String, Object> entry : remaining.entrySet()) {
            assign(builder, entry.getKey(), entry.getValue(), "credentials");
        }
        return builder;
    }

    /** The snake_case names of AuthConfig's builder setters, as a loader mapping spells them. */
    private static Set<String> authFieldNames() {
        Set<String> names = new java.util.LinkedHashSet<>();
        for (java.lang.reflect.Method method : AuthConfig.Builder.class.getDeclaredMethods()) {
            if (method.getParameterCount() == 1
                    && java.lang.reflect.Modifier.isPublic(method.getModifiers())) {
                names.add(snakeCase(method.getName()));
            }
        }
        return names;
    }

    /** A key with no matching setter is refused by name rather than ignored. */
    private static void assign(Object builder, String key, Object value, String where) {
        String setter = camelCase(key);
        List<java.lang.reflect.Method> named = new ArrayList<>();
        for (java.lang.reflect.Method method : builder.getClass().getMethods()) {
            if (method.getName().equals(setter) && method.getParameterCount() == 1) {
                named.add(method);
            }
        }
        if (named.isEmpty()) {
            List<String> known = new ArrayList<>();
            for (java.lang.reflect.Method method : builder.getClass().getDeclaredMethods()) {
                if (method.getParameterCount() == 1
                        && java.lang.reflect.Modifier.isPublic(method.getModifiers())) {
                    String name = snakeCase(method.getName());
                    if (!known.contains(name)) {
                        known.add(name);
                    }
                }
            }
            java.util.Collections.sort(known);
            throw new SdkConfigError(where + ": unknown config field: " + key
                    + "; known: " + String.join(", ", known));
        }
        for (java.lang.reflect.Method method : named) {
            Object coerced = coerce(value, method.getParameterTypes()[0]);
            if (coerced == NOT_COERCIBLE) {
                continue;
            }
            try {
                method.invoke(builder, coerced);
                return;
            } catch (ReflectiveOperationException failure) {
                throw new SdkConfigError(where + ": " + key + " could not be applied: "
                        + failure.getCause());
            }
        }
        throw new SdkConfigError(where + ": " + key + ": expected "
                + named.get(0).getParameterTypes()[0].getSimpleName() + ", got "
                + (value == null ? "null" : value.getClass().getSimpleName()));
    }

    /** Returned by {@link #coerce} when a value cannot become the wanted type. */
    private static final Object NOT_COERCIBLE = new Object();

    /** Every arm is a shape a JSON, YAML or {@code .env} loader produces. */
    private static Object coerce(Object value, Class<?> wanted) {
        if (value == null) {
            return wanted.isPrimitive() ? NOT_COERCIBLE : null;
        }
        if (Map.class.isAssignableFrom(wanted) && value instanceof Map<?, ?> entries) {
            // Before isInstance: erasure would let a raw Map through to a ClassCastException.
            Map<String, String> flattened = new LinkedHashMap<>();
            for (Map.Entry<?, ?> entry : entries.entrySet()) {
                if (!(entry.getKey() instanceof String name)) {
                    return NOT_COERCIBLE;
                }
                Object item = entry.getValue();
                if (!(item instanceof String) && !(item instanceof Number)
                        && !(item instanceof Boolean)) {
                    return NOT_COERCIBLE;
                }
                flattened.put(name, String.valueOf(item));
            }
            return flattened;
        }
        if (wanted.isInstance(value)) {
            return value;
        }
        if ((wanted == double.class || wanted == Double.class) && value instanceof Number number) {
            return number.doubleValue();
        }
        if ((wanted == int.class || wanted == Integer.class) && value instanceof Number number
                && number.doubleValue() == Math.rint(number.doubleValue())) {
            return number.intValue();
        }
        if ((wanted == long.class || wanted == Long.class) && value instanceof Number number
                && number.doubleValue() == Math.rint(number.doubleValue())) {
            return number.longValue();
        }
        if (wanted == boolean.class && value instanceof Boolean flag) {
            return flag;
        }
        if (Set.class.isAssignableFrom(wanted) && value instanceof java.util.Collection<?> items) {
            Set<Integer> codes = new java.util.LinkedHashSet<>();
            for (Object item : items) {
                if (!(item instanceof Number number)) {
                    return NOT_COERCIBLE;
                }
                codes.add(number.intValue());
            }
            return codes;
        }
        return NOT_COERCIBLE;
    }

    static String snakeCase(String name) {
        StringBuilder out = new StringBuilder(name.length() + 4);
        for (int i = 0; i < name.length(); i++) {
            char character = name.charAt(i);
            if (Character.isUpperCase(character) && i > 0) {
                boolean previousLower = Character.isLowerCase(name.charAt(i - 1));
                boolean nextLower =
                        i + 1 < name.length() && Character.isLowerCase(name.charAt(i + 1));
                if (previousLower || nextLower) {
                    out.append('_');
                }
            }
            out.append(Character.toLowerCase(character));
        }
        return out.toString();
    }

    /** The inverse of {@link #snakeCase}: the builder method a mapping key names. */
    static String camelCase(String snakeCaseName) {
        StringBuilder out = new StringBuilder(snakeCaseName.length());
        boolean raise = false;
        for (int i = 0; i < snakeCaseName.length(); i++) {
            char character = snakeCaseName.charAt(i);
            if (character == '_') {
                raise = true;
                continue;
            }
            out.append(raise ? Character.toUpperCase(character) : character);
            raise = false;
        }
        return out.toString();
    }

    private static void requireConfigType(Object block, Object where) {
        if (block instanceof SdkConfig || block instanceof AuthConfig || block instanceof Map) {
            return;
        }
        throw new SdkConfigError("credentials[" + where + "]: expected an SdkConfig, an "
                + "AuthConfig or a mapping of config fields, got "
                + (block == null ? "null" : block.getClass().getSimpleName()));
    }

    /**
     * The selection rule {@code ClientRegistry} applies, over a caller's own list.
     *
     * <p>Every entry is checked and two sharing a {@code clientName} are refused
     * before anything is chosen, an entry with none answers to {@code "default"}, and a
     * position may be negative, counting from the end.
     */
    public static Object selectCredentials(List<?> credentials, Object instance) {
        if (credentials == null || credentials.isEmpty()) {
            throw new SdkUsageError("no credentials were supplied");
        }
        List<Object> blocks = new ArrayList<>(credentials);
        Map<String, Integer> names = new LinkedHashMap<>();
        for (int index = 0; index < blocks.size(); index++) {
            requireConfigType(blocks.get(index), index);
            String name = readClientName(blocks.get(index));
            Integer earlier = names.putIfAbsent(name, index);
            if (earlier != null) {
                // Refused, not first-match-wins: one tenant's credentials would go elsewhere.
                throw new SdkUsageError("two instances share clientName '" + name
                        + "' (positions " + earlier + " and " + index + "); names must "
                        + "be unique so one can be told from the other. An entry that "
                        + "leaves clientName unset is named '"
                        + AuthConfig.DEFAULT_CLIENT_NAME + "'");
            }
        }
        if (instance == null) {
            return blocks.get(0);
        }
        if (instance instanceof Integer position) {
            int size = blocks.size();
            if (position >= -size && position < size) {
                return blocks.get((position % size + size) % size);
            }
            throw new SdkUsageError("no instance at index " + position + "; "
                    + size + " registered (0-" + (size - 1) + ")");
        }
        if (instance instanceof String name) {
            Integer index = names.get(name);
            if (index != null) {
                return blocks.get(index);
            }
            throw new SdkUsageError("unknown instance '" + name + "'; registered: "
                    + String.join(", ", names.keySet()));
        }
        throw new SdkUsageError("instance selector must be null, an Integer "
                + "position or a String clientName, got "
                + instance.getClass().getSimpleName());
    }

    /** The name an entry answers to: its {@code clientName}, else {@code "default"}. */
    public static String readClientName(Object block) {
        if (block instanceof SdkConfig config) {
            return config.getClientName();
        }
        if (block instanceof AuthConfig auth) {
            return auth.clientName();
        }
        if (block instanceof Map<?, ?> mapping) {
            Object name = mapping.containsKey("client_name")
                    ? mapping.get("client_name") : mapping.get("clientName");
            if (name instanceof String text) {
                return text;
            }
            Object auth = mapping.get("auth");
            if (auth instanceof AuthConfig supplied) {
                return supplied.clientName();
            }
            return AuthConfig.DEFAULT_CLIENT_NAME;
        }
        return null;
    }

    private void validate() {
        List<String> problems = new ArrayList<>();
        if (auth == null) {
            problems.add("auth: an AuthConfig is required");
        }
        if (apiVersion == null || !API_VERSION_PATTERN.matcher(apiVersion).matches()) {
            problems.add("apiVersion: expected something like 'v4' or '4', got "
                    + bounded(apiVersion));
        } else if (versionNamed(apiVersion) == null) {
            problems.add("apiVersion: this SDK speaks " + String.join(", ", knownVersions())
                    + ", got " + bounded(apiVersion));
        }
        if (maxRetries < 1) {
            problems.add("maxRetries: must be at least 1 (1 disables retry), got " + maxRetries);
        }
        if (!finitePositive(timeoutSeconds)) {
            problems.add("timeoutSeconds: must be a finite number > 0");
        }
        if (!finitePositive(connectTimeoutSeconds)) {
            problems.add("connectTimeoutSeconds: must be a finite number > 0");
        }
        if (Double.isNaN(retryBackoffSeconds) || Double.isInfinite(retryBackoffSeconds)
                || retryBackoffSeconds < 0) {
            problems.add("retryBackoffSeconds: must be a finite number >= 0");
        }
        if (Double.isNaN(retryBackoffMaxSeconds) || Double.isInfinite(retryBackoffMaxSeconds)
                || retryBackoffMaxSeconds < retryBackoffSeconds) {
            problems.add("retryBackoffMaxSeconds: must be finite and >= retryBackoffSeconds");
        }
        if (poolMaxsize < 1) {
            problems.add("poolMaxsize: must be at least 1");
        }
        if (maxResponseBytes < 1) {
            problems.add("maxResponseBytes: must be at least 1, got " + maxResponseBytes);
        }
        if (maxRequestBytes < 1) {
            problems.add("maxRequestBytes: must be at least 1, got " + maxRequestBytes);
        }
        if (maxUploadBytes < 1) {
            problems.add("maxUploadBytes: must be at least 1, got " + maxUploadBytes);
        }
        if (maxMultipartOverheadBytes < 1) {
            problems.add("maxMultipartOverheadBytes: must be at least 1, got "
                    + maxMultipartOverheadBytes);
        }
        if (maxCaBundleBytes < 1) {
            problems.add("maxCaBundleBytes: must be at least 1, got " + maxCaBundleBytes);
        }
        validateCodes(problems, "retryableHttpCodes", retryableHttpCodes);
        validateCodes(problems, "unsafeRetryableHttpCodes", unsafeRetryableHttpCodes);
        if (logLevel == null || !VALID_LOG_LEVELS.contains(logLevel)) {
            problems.add("logLevel: expected one of " + String.join(", ", VALID_LOG_LEVELS)
                    + ", got " + bounded(logLevel));
        }
        if (printFormat == null || !VALID_PRINT_FORMATS.contains(printFormat)) {
            problems.add("printFormat: expected one of " + PRINT_FORMAT_PRETTY + ", "
                    + PRINT_FORMAT_COMPACT + ", got " + bounded(printFormat));
        }
        if (printMaxChars < 0) {
            problems.add("printMaxChars: must be >= 0 (0 = unlimited)");
        }
        if (responseFormat == null || !RESPONSE_FORMATS.contains(responseFormat)) {
            // Loud rather than a silent fall back to map: a nearly right "dtos" would mislead.
            problems.add("responseFormat: expected one of "
                    + String.join(", ", RESPONSE_FORMATS) + ", got " + bounded(responseFormat)
                    + " (it may have come from " + RESPONSE_FORMAT_ENVIRONMENT_VARIABLE + ", "
                    + "which is read once here and is the lowest-priority default)");
        }
        if (userAgent == null || userAgent.strip().isEmpty()) {
            problems.add("userAgent: must not be blank");
        } else if (!isSendableValue(userAgent) || hasEdgeWhitespace(userAgent)) {
            // The transport's grammar, judged here so it cannot fail on the first call instead.
            problems.add("userAgent: " + bounded(userAgent) + " cannot be sent as a "
                    + "header value - it may carry only visible ASCII, a space or a tab, "
                    + "and may not begin or end with a space or a tab");
        }
        if (caBundle != null && !caBundle.strip().isEmpty()) {
            if (!verifySsl) {
                problems.add("caBundle and verifySsl=false are contradictory: one supplies a "
                        + "trust store, the other disables verification");
            }
            if (!Files.isRegularFile(Path.of(caBundle))) {
                problems.add("caBundle: no such file: " + bounded(caBundle));
            } else {
                problems.addAll(caBundleSizeProblems());
            }
        }
        if (proxyUrl != null && !proxyUrl.isBlank()) {
            problems.addAll(proxyUrlProblems(proxyUrl));
        }
        if (httpClient != null && (!verifySsl || (caBundle != null && !caBundle.isBlank())
                || (proxyUrl != null && !proxyUrl.isBlank()))) {
            // A JDK client cannot be rebuilt from itself, so these could only be ignored.
            problems.add("httpClient: a supplied client carries its own TLS and proxy "
                    + "settings, so verifySsl, caBundle and proxyUrl cannot also apply - "
                    + "set them on the client you build, or leave httpClient unset");
        }
        problems.addAll(headerProblems(defaultHeaders));

        if (!problems.isEmpty()) {
            throw new SdkConfigError(String.join("; ", problems));
        }
    }

    /** Refused before anything opens it, so the refusal names the option at construction. */
    private List<String> caBundleSizeProblems() {
        try {
            long size = Files.size(Path.of(caBundle));
            if (size > maxCaBundleBytes) {
                return List.of("caBundle: " + Redaction.redactPath(caBundle) + " is " + size
                        + " bytes, over the " + maxCaBundleBytes
                        + "-byte maxCaBundleBytes ceiling - raise it with "
                        + "SdkConfig.builder(...).maxCaBundleBytes(bytes) if the bundle is "
                        + "meant to be that large");
            }
        } catch (java.io.IOException unreadable) {
            return List.of("caBundle: " + Redaction.redactPath(caBundle)
                    + " could not be measured: " + Redaction.describeFailure(unreadable));
        }
        return List.of();
    }

    /** A scheme-less {@code proxy.example:3128} otherwise fails at request time naming no field. */
    private static List<String> proxyUrlProblems(String proxyUrl) {
        // Authority, never java.net.URI: see Authority for why.
        Authority parsed = Authority.split(proxyUrl);
        if (parsed.isPortMalformed()) {
            return List.of("proxyUrl: is not a URL (unreadable port, or an unclosed "
                    + "IPv6 literal). The value is not shown: it may carry credentials");
        }
        if (parsed.getScheme().isEmpty() || parsed.getHost().isEmpty()) {
            return List.of("proxyUrl: must carry a scheme and a host, like "
                    + "http://proxy.example:3128. "
                    + "The value is not shown: it may carry credentials");
        }
        if (parsed.getScheme().startsWith("socks")) {
            // The JDK HTTP client this SDK is built on reaches HTTP proxies only.
            return List.of("proxyUrl: a socks proxy is not supported - the JDK HTTP client "
                    + "this SDK sends over speaks to HTTP proxies only. Use an http:// "
                    + "proxy. The value is not shown: it may carry credentials");
        }
        if (!"http".equals(parsed.getScheme()) && !"https".equals(parsed.getScheme())) {
            return List.of("proxyUrl: the scheme must be http or https. "
                    + "The value is not shown: it may carry credentials");
        }
        return List.of();
    }

    /** Ascending: {@code Set.of(...)} iterates differently per JVM run, and a null sorts last. */
    private static Set<Integer> copyCodes(Set<Integer> codes) {
        if (codes == null) {
            return null;
        }
        List<Integer> ordered = new ArrayList<>(codes);
        ordered.sort(java.util.Comparator.nullsLast(java.util.Comparator.naturalOrder()));
        return java.util.Collections.unmodifiableSet(new java.util.LinkedHashSet<>(ordered));
    }

    private static void validateCodes(List<String> problems, String name, Set<Integer> codes) {
        if (codes == null) {
            problems.add(name + ": expected a set of HTTP status codes, got null");
            return;
        }
        if (codes.size() > MAX_HTTP_CODES) {
            problems.add(name + ": at most " + MAX_HTTP_CODES + " status codes (100-599 is "
                    + "every code there is), got more than that");
            return;
        }
        for (Integer code : codes) {
            if (code == null || code < 100 || code > 599) {
                problems.add(name + ": " + code + " is not an HTTP status code");
            }
        }
    }

    private static List<String> headerProblems(Map<String, String> headers) {
        List<String> problems = new ArrayList<>();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            boolean badName = !isSendableName(name);
            boolean badValue = !isSendableValue(value) || hasEdgeWhitespace(value);
            if (badName || badValue) {
                // The name goes through bounded(): a refused name may carry a CR or LF.
                problems.add("defaultHeaders: " + bounded(name) + " cannot be sent as a "
                        + "header - a name is an RFC 9110 token (letters, digits and "
                        + "!#$%&'*+-.^_`|~ only), and neither a name nor a value may be blank "
                        + "or carry a line break, a leading or trailing space or tab, or a "
                        + "character outside printable ASCII. The value is not shown: it may "
                        + "be a credential");
                continue;
            }
            if (CONTENT_TYPE_HEADER.equalsIgnoreCase(name) && !isMediaType(value)) {
                problems.add(grammarRefusal("defaultHeaders", value));
            }
        }
        return problems;
    }

    // One header grammar, identical copies of which live in ExsitedClient and Transport; keep
    // all three the same.

    /** RFC 9110 5.1's {@code token}: letters, digits and {@code !#$%&'*+-.^_`|~}. */
    private static boolean isSendableName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            char character = name.charAt(i);
            boolean token = (character >= 'A' && character <= 'Z')
                    || (character >= 'a' && character <= 'z')
                    || (character >= '0' && character <= '9')
                    || "!#$%&'*+-.^_`|~".indexOf(character) >= 0;
            if (!token) {
                return false;
            }
        }
        return true;
    }

    /** Every character a visible ASCII one, a space or a tab. */
    private static boolean isSendableValue(String value) {
        if (value == null) {
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            char character = value.charAt(i);
            if (character != '\t' && (character < 0x20 || character > 0x7e)) {
                return false;
            }
        }
        return true;
    }

    /** Refused rather than trimmed: a trimmed value would be sent altered. */
    private static boolean hasEdgeWhitespace(String value) {
        if (value.isEmpty()) {
            return false;
        }
        char first = value.charAt(0);
        char last = value.charAt(value.length() - 1);
        return first == ' ' || first == '\t' || last == ' ' || last == '\t';
    }

    private static final String CONTENT_TYPE_HEADER = "Content-Type";

    /** Written out rather than {@code \w}, which turns Unicode-aware and accepts Cyrillic. */
    private static final String TCHAR = "[!#$%&'*+\\-.^_`|~0-9A-Za-z]";

    private static final String TOKEN = TCHAR + "+";

    /** {@code obs-text} capped at U+00FF, what a latin-1 header value can carry. */
    private static final String QUOTED_STRING =
            "\"(?:[\\t \\x21\\x23-\\x5B\\x5D-\\x7E\\x80-\\xFF]"
                    + "|\\\\[\\t \\x21-\\x7E\\x80-\\xFF])*\"";

    private static final String PARAMETER = TOKEN + "=(?:" + TOKEN + "|" + QUOTED_STRING + ")";

    /** Needs {@code matches()}: {@code $} and {@code \Z} match before a final line terminator. */
    private static final Pattern MEDIA_TYPE = Pattern.compile(
            TOKEN + "/" + TOKEN + "(?:[ \\t]*;[ \\t]*" + PARAMETER + ")*");

    /** Every value a refusal echoes is bounded to this length. */
    private static final int MAX_SHOWN = 120;

    /** From roughly 700 parameters the match raises a {@link StackOverflowError}. */
    private static final int MAX_JUDGEABLE = 1024;

    private static final String GRAMMAR_ADVICE =
            "type/subtype, optionally followed by '; parameter=value'";

    /** RFC 9110 8.3 {@code type/subtype} plus zero or more {@code ; parameter=value} pairs. */
    private static boolean isMediaType(String value) {
        if (value.length() > MAX_JUDGEABLE) {
            return false;
        }
        return MEDIA_TYPE.matcher(value).matches();
    }

    private static String grammarRefusal(String where, String value) {
        return where + ": Content-Type must be a media type like 'application/json', got "
                + bounded(value) + " - " + GRAMMAR_ADVICE;
    }

    /** Render an offending value quoted, escaped and bounded to {@link #MAX_SHOWN}. */
    private static String bounded(String value) {
        if (value == null) {
            return "null";
        }
        StringBuilder escaped = new StringBuilder(value.length() + 2);
        for (int i = 0; i < value.length(); i++) {
            char character = value.charAt(i);
            switch (character) {
                case '\\' -> escaped.append("\\\\");
                case '\'' -> escaped.append("\\'");
                case '\n' -> escaped.append("\\n");
                case '\r' -> escaped.append("\\r");
                case '\t' -> escaped.append("\\t");
                default -> {
                    if (character < 0x20 || character == 0x7f) {
                        escaped.append(String.format("\\x%02x", (int) character));
                    } else {
                        escaped.append(character);
                    }
                }
            }
        }
        String shown = escaped.toString();
        if (shown.length() > MAX_SHOWN) {
            int head = (MAX_SHOWN - 3) / 2;
            int tail = MAX_SHOWN - 3 - head;
            shown = shown.substring(0, head) + "..." + shown.substring(shown.length() - tail);
        }
        return "'" + shown + "'";
    }

    public AuthConfig getAuth() {
        return auth;
    }

    /** Normalised at construction, so {@code " 4 "} reads back as {@code "v4"}. */
    public String getApiVersion() {
        return apiVersion;
    }

    /** The whole attempt: DNS, connecting, writing, thinking time and reading the body. */
    public double getTimeoutSeconds() {
        return timeoutSeconds;
    }

    /** Inside {@link #getTimeoutSeconds()}, so a smaller ceiling leaves this unreachable. */
    public double getConnectTimeoutSeconds() {
        return connectTimeoutSeconds;
    }

    /** A fresh two-element array, connect first. */
    public double[] getTimeout() {
        return new double[] {connectTimeoutSeconds, timeoutSeconds};
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    /** Immutable and in ascending order, so two configs render alike on every run. */
    public Set<Integer> getRetryableHttpCodes() {
        return retryableHttpCodes;
    }

    /** The narrower set a state-changing method must also match before it is repeated. */
    public Set<Integer> getUnsafeRetryableHttpCodes() {
        return unsafeRetryableHttpCodes;
    }

    /** The base of the doubling window; 0 retries immediately. */
    public double getRetryBackoffSeconds() {
        return retryBackoffSeconds;
    }

    public double getRetryBackoffMaxSeconds() {
        return retryBackoffMaxSeconds;
    }

    /** False disables verification everywhere and cannot be combined with a {@code caBundle}. */
    public boolean isVerifySsl() {
        return verifySsl;
    }

    /** Checked to exist at construction; {@code null} uses the platform's own roots. */
    public String getCaBundle() {
        return caBundle;
    }

    /** May hold userinfo, so treat it as a credential. */
    public String getProxyUrl() {
        return proxyUrl;
    }

    /** Validated and carried; the JDK client sizes its own pool from {@code jdk.httpclient.connectionPoolSize}. */
    public int getPoolMaxsize() {
        return poolMaxsize;
    }

    public long getMaxResponseBytes() {
        return maxResponseBytes;
    }

    public long getMaxRequestBytes() {
        return maxRequestBytes;
    }

    public long getMaxUploadBytes() {
        return maxUploadBytes;
    }

    public long getMaxMultipartOverheadBytes() {
        return maxMultipartOverheadBytes;
    }

    public long getMaxCaBundleBytes() {
        return maxCaBundleBytes;
    }

    /** A fresh mutable copy, in the order given, so a caller's edits cannot reach the config. */
    public Map<String, String> getDefaultHeaders() {
        return new LinkedHashMap<>(defaultHeaders);
    }

    /** The same fresh copy as {@link #getDefaultHeaders()}. */
    public Map<String, String> getDefaultHeadersDict() {
        return getDefaultHeaders();
    }

    /** True throws an {@code SdkError}; false, the default, answers an {@code ApiResponse}. */
    public boolean isRaiseOnError() {
        return raiseOnError;
    }

    /** Already resolved: what was set in code, else the environment variable, else the default. */
    public String getResponseFormat() {
        return responseFormat;
    }

    /** {@code null} is refused, so a tier meaning "nothing was set" must not call this. */
    public static String normaliseResponseFormat(String responseFormat) {
        String folded = foldResponseFormat(responseFormat);
        if (folded == null || !RESPONSE_FORMATS.contains(folded)) {
            throw new SdkUsageError("responseFormat: expected one of "
                    + String.join(", ", RESPONSE_FORMATS) + ", got " + bounded(responseFormat));
        }
        return folded;
    }

    /** The one spelling of "the same format": {@code " DTO "} and {@code "dto"} are it. */
    private static String foldResponseFormat(String responseFormat) {
        return responseFormat == null ? null : responseFormat.strip().toLowerCase(Locale.ROOT);
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getLogLevel() {
        return logLevel;
    }

    /** True records them redacted; false, the default, records only method, URL and status. */
    public boolean isLogBodies() {
        return logBodies;
    }

    /** Appended in UTF-8 and released when the client closes; blank means none. */
    public String getLogFile() {
        return logFile;
    }

    /** False by default, so a library using this SDK stays silent. */
    public boolean isPrintApiInfo() {
        return printApiInfo;
    }

    public boolean isPrintRequestPayload() {
        return printRequestPayload;
    }

    public boolean isPrintResponseBody() {
        return printResponseBody;
    }

    public boolean isPrintHeaders() {
        return printHeaders;
    }

    /** The line is not runnable as it stands: the secrets in it are redacted. */
    public boolean isPrintCurl() {
        return printCurl;
    }

    /** Prints nothing for a successful call, whatever the other {@code print*} flags say. */
    public boolean isPrintOnlyOnError() {
        return printOnlyOnError;
    }

    public String getPrintFormat() {
        return printFormat;
    }

    /** A redirected stream is left plain either way. */
    public boolean isPrintColor() {
        return printColor;
    }

    /** 0, the default, means unlimited. */
    public int getPrintMaxChars() {
        return printMaxChars;
    }

    /** A client of the caller's own, or {@code null} for the one the transport builds and closes. */
    public HttpClient getHttpClient() {
        return httpClient;
    }

    // Pass-throughs. A secret comes back real; redaction happens in toString(), not here.

    public String getBaseUrl() {
        return auth.baseUrl();
    }

    public String getClientId() {
        return auth.clientId();
    }

    /** The real secret, never masked: do not log this. */
    public String getClientSecret() {
        return auth.clientSecret();
    }

    public String getRedirectUri() {
        return auth.redirectUri();
    }

    public String getClientName() {
        return auth.clientName();
    }

    public String getGrantType() {
        return auth.grantType();
    }

    /** {@code null} when the token is kept in memory. */
    public String getTokenCachePath() {
        return auth.tokenCachePath();
    }

    // The token layer's view of this config, read by the auth package.

    /** As {@link #getAuth()}, for the token layer. */
    public AuthConfig auth() {
        return auth;
    }

    /** As {@link #getBaseUrl()}, for the token layer. */
    public String baseUrl() {
        return getBaseUrl();
    }

    /** As {@link #getUserAgent()}, for the token layer. */
    public String userAgent() {
        return userAgent;
    }

    /** The path the token is minted at. */
    public String tokenPath() {
        return versionNamed(apiVersion).tokenPath();
    }

    /** The fields {@link #toString()} renders, in declaration order. */
    private static final List<String> REPR_FIELDS = List.of(
            "auth", "apiVersion", "timeoutSeconds", "connectTimeoutSeconds", "maxRetries",
            "retryableHttpCodes", "unsafeRetryableHttpCodes", "retryBackoffSeconds",
            "retryBackoffMaxSeconds", "verifySsl", "caBundle", "proxyUrl", "poolMaxsize",
            "maxResponseBytes", "maxRequestBytes", "maxUploadBytes",
            "maxMultipartOverheadBytes", "maxCaBundleBytes",
            "defaultHeaders", "raiseOnError", "responseFormat", "userAgent", "logLevel",
            "logBodies", "logFile",
            "printApiInfo", "printRequestPayload", "printResponseBody", "printHeaders",
            "printCurl", "printOnlyOnError", "printFormat", "printColor", "printMaxChars",
            "httpClient");

    /** Every field, never a subset, so diffing two configs shows every knob that differs. */
    @Override
    public String toString() {
        return "SdkConfig(" + String.join(", ", Redaction.redactFields(this, REPR_FIELDS)) + ")";
    }

    private static String normaliseApiVersion(String raw) {
        if (raw == null) {
            return null;
        }
        String value = raw.strip().toLowerCase(Locale.ROOT);
        if (!value.isEmpty() && !value.startsWith("v")) {
            value = "v" + value;
        }
        return value;
    }

    /** The version this SDK has resources for under that name, or {@code null}. */
    private static ApiVersion versionNamed(String apiVersion) {
        for (ApiVersion version : ApiVersion.values()) {
            if (version.pathSegment().equals(apiVersion)) {
                return version;
            }
        }
        return null;
    }

    private static List<String> knownVersions() {
        List<String> known = new ArrayList<>();
        for (ApiVersion version : ApiVersion.values()) {
            known.add(version.pathSegment());
        }
        return known;
    }

    private static boolean finitePositive(double value) {
        return !Double.isNaN(value) && !Double.isInfinite(value) && value > 0;
    }

    /** Read at construction, once per {@link Builder}, and never per call. */
    private static String responseFormatFromEnvironment() {
        String fromEnvironment = System.getenv(RESPONSE_FORMAT_ENVIRONMENT_VARIABLE);
        return fromEnvironment == null || fromEnvironment.strip().isEmpty()
                ? DEFAULT_RESPONSE_FORMAT : fromEnvironment;
    }

    private static String readImplementationVersion() {
        // A class loaded from a source tree has no manifest to read Implementation-Version from.
        String fromManifest = SdkConfig.class.getPackage() == null
                ? null : SdkConfig.class.getPackage().getImplementationVersion();
        return fromManifest == null ? FALLBACK_VERSION : fromManifest;
    }

    /** The URL authority grammar, hand-rolled: {@code URI.getHost()} is null for a '_' host. */
    private static final class Authority {

        private final String scheme;
        private final String host;
        private final String authorityProblem;

        private Authority(String scheme, String host, String authorityProblem) {
            this.scheme = scheme;
            this.host = host;
            this.authorityProblem = authorityProblem;
        }

        /** Split a URL into its parts. */
        static Authority split(String url) {
            String rest = url;
            String scheme = "";
            int colon = rest.indexOf(':');
            if (colon > 0 && isSchemeText(rest.substring(0, colon))) {
                scheme = rest.substring(0, colon).toLowerCase(Locale.ROOT);
                rest = rest.substring(colon + 1);
            }
            if (!rest.startsWith("//")) {
                // No "//", so no authority at all: "https:example" has none.
                return new Authority(scheme, "", null);
            }
            rest = rest.substring(2);
            int end = rest.length();
            for (int i = 0; i < rest.length(); i++) {
                char character = rest.charAt(i);
                if (character == '/' || character == '?' || character == '#') {
                    end = i;
                    break;
                }
            }
            String netloc = rest.substring(0, end);
            String hostinfo = netloc;
            int at = netloc.lastIndexOf('@');
            if (at >= 0) {
                hostinfo = netloc.substring(at + 1);
            }
            String host;
            String portText;
            if (hostinfo.startsWith("[")) {
                int close = hostinfo.indexOf(']');
                if (close < 0) {
                    return new Authority(scheme, "", "Invalid IPv6 URL");
                }
                host = hostinfo.substring(0, close + 1);
                String tail = hostinfo.substring(close + 1);
                portText = tail.startsWith(":") ? tail.substring(1) : "";
            } else {
                int portColon = hostinfo.indexOf(':');
                host = portColon < 0 ? hostinfo : hostinfo.substring(0, portColon);
                portText = portColon < 0 ? "" : hostinfo.substring(portColon + 1);
            }
            String problem = null;
            if (!portText.isEmpty()) {
                try {
                    int port = Integer.parseInt(portText);
                    if (port < 0 || port > 65535) {
                        problem = "Port out of range 0-65535";
                    }
                } catch (NumberFormatException notANumber) {
                    problem = "Port could not be cast to integer value";
                }
            }
            return new Authority(scheme, host.toLowerCase(Locale.ROOT), problem);
        }

        /** RFC 3986 {@code scheme}: a letter, then letters, digits, {@code +-.} */
        private static boolean isSchemeText(String candidate) {
            if (!Character.isLetter(candidate.charAt(0))) {
                return false;
            }
            for (int i = 1; i < candidate.length(); i++) {
                char character = candidate.charAt(i);
                if (!Character.isLetterOrDigit(character)
                        && character != '+' && character != '-' && character != '.') {
                    return false;
                }
            }
            return true;
        }

        String getScheme() {
            return scheme;
        }

        String getHost() {
            return host;
        }

        boolean isPortMalformed() {
            return authorityProblem != null;
        }
    }

    public static final class Builder {
        private final AuthConfig auth;
        private String apiVersion = DEFAULT_API_VERSION;
        private double timeoutSeconds = DEFAULT_TIMEOUT_SECONDS;
        private double connectTimeoutSeconds = DEFAULT_CONNECT_TIMEOUT_SECONDS;
        private int maxRetries = DEFAULT_MAX_RETRIES;
        private Set<Integer> retryableHttpCodes = RetryPolicy.DEFAULT_RETRYABLE_HTTP_CODES;
        private Set<Integer> unsafeRetryableHttpCodes = RetryPolicy.DEFAULT_UNSAFE_RETRYABLE_HTTP_CODES;
        private double retryBackoffSeconds = DEFAULT_RETRY_BACKOFF_SECONDS;
        private double retryBackoffMaxSeconds = DEFAULT_RETRY_BACKOFF_MAX_SECONDS;
        private boolean verifySsl = true;
        private String caBundle;
        private String proxyUrl;
        private int poolMaxsize = DEFAULT_POOL_MAXSIZE;
        private long maxResponseBytes = DEFAULT_MAX_RESPONSE_BYTES;
        private long maxRequestBytes = DEFAULT_MAX_REQUEST_BYTES;
        private long maxUploadBytes = DEFAULT_MAX_UPLOAD_BYTES;
        private long maxMultipartOverheadBytes = DEFAULT_MAX_MULTIPART_OVERHEAD_BYTES;
        private long maxCaBundleBytes = DEFAULT_MAX_CA_BUNDLE_BYTES;
        private Map<String, String> defaultHeaders = new LinkedHashMap<>();
        private boolean raiseOnError = false;
        private String responseFormat = responseFormatFromEnvironment();
        private String userAgent = DEFAULT_USER_AGENT;
        private String logLevel = DEFAULT_LOG_LEVEL;
        private boolean logBodies = false;
        private String logFile;
        private boolean printApiInfo = false;
        private boolean printRequestPayload = false;
        private boolean printResponseBody = false;
        private boolean printHeaders = false;
        private boolean printCurl = false;
        private boolean printOnlyOnError = false;
        private String printFormat = DEFAULT_PRINT_FORMAT;
        private boolean printColor = false;
        private int printMaxChars = 0;
        private HttpClient httpClient;

        private Builder(AuthConfig auth) {
            this.auth = auth;
        }

        /** {@code "v4"} or bare {@code "4"}, stripped and lowered; a leading zero is refused. */
        public Builder apiVersion(String apiVersion) {
            this.apiVersion = apiVersion;
            return this;
        }

        /** The same, named by the enum of versions this SDK has resources for. */
        public Builder apiVersion(ApiVersion apiVersion) {
            this.apiVersion = apiVersion == null ? null : apiVersion.pathSegment();
            return this;
        }

        public Builder timeoutSeconds(double timeoutSeconds) {
            this.timeoutSeconds = timeoutSeconds;
            return this;
        }

        public Builder connectTimeoutSeconds(double connectTimeoutSeconds) {
            this.connectTimeoutSeconds = connectTimeoutSeconds;
            return this;
        }

        /** Attempts including the first: 1 disables retrying, 3 sends once plus two repeats. */
        public Builder maxRetries(int maxRetries) {
            this.maxRetries = maxRetries;
            return this;
        }

        /** Each status within 100-599, at most 500 of them. */
        public Builder retryableHttpCodes(Set<Integer> retryableHttpCodes) {
            this.retryableHttpCodes = retryableHttpCodes;
            return this;
        }

        /** The narrower set that stops a committed POST being sent twice. */
        public Builder unsafeRetryableHttpCodes(Set<Integer> unsafeRetryableHttpCodes) {
            this.unsafeRetryableHttpCodes = unsafeRetryableHttpCodes;
            return this;
        }

        public Builder retryBackoffSeconds(double retryBackoffSeconds) {
            this.retryBackoffSeconds = retryBackoffSeconds;
            return this;
        }

        /** One below {@code retryBackoffSeconds} is refused. */
        public Builder retryBackoffMaxSeconds(double retryBackoffMaxSeconds) {
            this.retryBackoffMaxSeconds = retryBackoffMaxSeconds;
            return this;
        }

        public Builder verifySsl(boolean verifySsl) {
            this.verifySsl = verifySsl;
            return this;
        }

        /** Must be an existing regular file; blank keeps the platform trust store. */
        public Builder caBundle(String caBundle) {
            this.caBundle = caBundle;
            return this;
        }

        /** At least a scheme and a host, like {@code http://proxy.example:3128}. */
        public Builder proxyUrl(String proxyUrl) {
            this.proxyUrl = proxyUrl;
            return this;
        }

        public Builder poolMaxsize(int poolMaxsize) {
            this.poolMaxsize = poolMaxsize;
            return this;
        }

        /** A larger body is refused with an {@code SdkError} naming this option. */
        public Builder maxResponseBytes(long maxResponseBytes) {
            this.maxResponseBytes = maxResponseBytes;
            return this;
        }

        public Builder maxRequestBytes(long maxRequestBytes) {
            this.maxRequestBytes = maxRequestBytes;
            return this;
        }

        public Builder maxUploadBytes(long maxUploadBytes) {
            this.maxUploadBytes = maxUploadBytes;
            return this;
        }

        public Builder maxMultipartOverheadBytes(long maxMultipartOverheadBytes) {
            this.maxMultipartOverheadBytes = maxMultipartOverheadBytes;
            return this;
        }

        public Builder maxCaBundleBytes(long maxCaBundleBytes) {
            this.maxCaBundleBytes = maxCaBundleBytes;
            return this;
        }

        /**
         * Copied, so later edits are not seen; this replaces rather than merges. An
         * {@code X-Idempotency-Key} here is replaced by a fresh one on every POST; pass a key
         * per call instead.
         */
        public Builder defaultHeaders(Map<String, String> defaultHeaders) {
            this.defaultHeaders = defaultHeaders == null
                    ? new LinkedHashMap<>() : new LinkedHashMap<>(defaultHeaders);
            return this;
        }

        public Builder raiseOnError(boolean raiseOnError) {
            this.raiseOnError = raiseOnError;
            return this;
        }

        /** One of {@code map}, {@code json}, {@code dto}; anything else is refused. */
        public Builder responseFormat(String responseFormat) {
            this.responseFormat = responseFormat;
            return this;
        }

        /** The same, named by the enum. */
        public Builder responseFormat(ResponseFormat responseFormat) {
            this.responseFormat = responseFormat == null ? null : responseFormat.value();
            return this;
        }

        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public Builder logLevel(String logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        public Builder logBodies(boolean logBodies) {
            this.logBodies = logBodies;
            return this;
        }

        /** Appended in UTF-8; an unwritable path fails when the client is built. */
        public Builder logFile(String logFile) {
            this.logFile = logFile;
            return this;
        }

        public Builder printApiInfo(boolean printApiInfo) {
            this.printApiInfo = printApiInfo;
            return this;
        }

        public Builder printRequestPayload(boolean printRequestPayload) {
            this.printRequestPayload = printRequestPayload;
            return this;
        }

        public Builder printResponseBody(boolean printResponseBody) {
            this.printResponseBody = printResponseBody;
            return this;
        }

        public Builder printHeaders(boolean printHeaders) {
            this.printHeaders = printHeaders;
            return this;
        }

        public Builder printCurl(boolean printCurl) {
            this.printCurl = printCurl;
            return this;
        }

        public Builder printOnlyOnError(boolean printOnlyOnError) {
            this.printOnlyOnError = printOnlyOnError;
            return this;
        }

        public Builder printFormat(String printFormat) {
            this.printFormat = printFormat;
            return this;
        }

        public Builder printColor(boolean printColor) {
            this.printColor = printColor;
            return this;
        }

        public Builder printMaxChars(int printMaxChars) {
            this.printMaxChars = printMaxChars;
            return this;
        }

        /** Send over this client instead of one built from these settings; it stays the caller's. */
        public Builder httpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        /** The message lists every problem at once rather than only the first. */
        public SdkConfig build() {
            return new SdkConfig(this);
        }
    }
}
