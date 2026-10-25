# Exsited Java SDK

Java client for the Exsited v4 REST API. Handles OAuth2 with a
cross-process token cache, retry with backoff, cursor and offset pagination,
corporate TLS and proxies, content-type decoding, a typed error hierarchy, secret
redaction and terminal diagnostics.

The wire is untouched - no case conversion, nothing renamed, nothing dropped.
`response.getMap()` is the body exactly as the API sent it, and a payload you
write is the payload the API receives. Typed views are available on top of that:
send a payload object if you prefer one, read `getDto()` if you prefer one, and
the bytes are the same either way. See [Sending and reading](#sending-and-reading).

```java
import com.exsited.client.ApiResponse;
import com.exsited.client.ExsitedClient;
import com.exsited.config.AuthConfig;
import com.exsited.config.SdkConfig;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.Account;

SdkConfig config = SdkConfig.builder(AuthConfig.builder()
        .baseUrl("https://your-instance.exsited.com")
        .clientId("...").clientSecret("...")
        .redirectUri("https://your-app.example/callback")
        .clientName("default")
        .build())
        .raiseOnError(true)
        .build();

try (ExsitedClient client = new ExsitedClient(config)) {
    ApiResponse<Account> response = client.accounts().getAccount("W13ZGY");
    System.out.println(response.getMap());        // the body as it arrived
    System.out.println(response.getDto().name);   // the typed view
}
```

`id`, not `uuid`: a response carries both, and only `id` addresses the record in a
path. The uuid answers `404`; filter on `filter_uuid` instead.

A failed call comes back as a response rather than throwing, which is right for
application code and wrong for a script that must stop. `raiseOnError(true)` above
flips it. "Failed" means not `2xx` **or** a `2xx` whose body says
`"object": "error"`, which is how a v4 error envelope names itself.

`ExsitedClient` is `AutoCloseable`: the try-with-resources block drains calls in
flight and closes the HTTP client it built.

| | |
|---|---|
| Coordinates | [`com.exsited:exsited:4.0.0`](https://central.sonatype.com/artifact/com.exsited/exsited) on Maven Central |
| Java | 21+ |
| Dependencies | none: the JSON reader and writer, the logging (`System.Logger`) and the HTTP client (`java.net.http`) are the JDK's or hand-written |
| Endpoints | 864 in 91 families, documented at [exsited.mintlify.app](https://exsited.mintlify.app/introduction) |

## Contents

- [Install](#install)
- [Credentials](#credentials)
- [Configuration](#configuration)
- [Multiple instances](#multiple-instances)
- [Authentication](#authentication)
- [Requests](#requests)
- [Pagination](#pagination)
- [Responses](#responses)
- [Sending and reading](#sending-and-reading) - three send forms, four read members
- [Errors](#errors)
- [Diagnostics](#diagnostics)
- [Retries](#retries)
- [Transport](#transport)
  - [Timeouts](#timeouts) - connect and the per-attempt ceiling
- [Endpoints](#endpoints)
- [Architecture](#architecture)
  - [The call path](#the-call-path)
  - [Objects, per instance](#objects-per-instance)
  - [Design notes](#design-notes)
- [Troubleshooting](#troubleshooting)
- [Public API](#public-api)

## Install

Add the dependency to your project's `pom.xml`:

```xml
<dependency>
    <groupId>com.exsited</groupId>
    <artifactId>exsited</artifactId>
    <version>4.0.0</version>
</dependency>
```

Maven resolves it from Maven Central with no `<repository>` to add. The
dependency tree is one node: the SDK has no dependencies of its own.

**Prerequisites.** A JDK 21 or newer. The SDK closes the
`java.net.http.HttpClient` it builds, and that client is `AutoCloseable` from
JDK 21. Any distribution works (Eclipse Temurin, Microsoft Build of OpenJDK,
Amazon Corretto).

**Behind a proxy or TLS interception** - Maven's own proxy lives in
`~/.m2/settings.xml`; the SDK's proxy and corporate CA are runtime config:

```java
SdkConfig.builder(auth)
        .proxyUrl("http://proxy.internal:8080")
        .caBundle("/path/to/corporate-ca.pem")
        .build();
```

## Credentials

**The SDK does not read a credential from the environment** - not a variable of
yours, not a `.env`. Build an `AuthConfig` from wherever your credentials live and
hand it to `SdkConfig`. Reading them from `EXSITED_*` variables, as below, is
one way.

```java
AuthConfig auth = AuthConfig.builder()
        .baseUrl(System.getenv("EXSITED_BASE_URL"))
        .clientId(System.getenv("EXSITED_CLIENT_ID"))
        .clientSecret(System.getenv("EXSITED_CLIENT_SECRET"))
        .redirectUri(System.getenv("EXSITED_REDIRECT_URI"))
        .clientName("default")
        .build();
```

| Field | Required | Default | Notes |
|---|---|---|---|
| `baseUrl` | yes | | Scheme and host, for example `https://dev-api.exsited.com` |
| `clientId` | yes | | |
| `clientSecret` | yes | | Redacted in `toString()` |
| `redirectUri` | yes | | Sent in the token request body |
| `clientName` | | `"default"` | Label in logs and output; selects an instance by name. Two instances may not share one |
| `grantType` | | `"client_credentials"` | |
| token cache | | platform directory | `.tokenCachePath("...")` to move it, `.inMemoryToken()` for memory only |

A blank required field is refused when the config is built, naming the field.
There is no `authString`: the Exsited token request carries the four fields above
and no other credential.

## Configuration

`AuthConfig` is **who you are**; `SdkConfig` is **how the SDK behaves**. Both are
immutable, validated at construction with every problem reported at once, and
redact their secrets in `toString()`. A builder names only what it changes:

```java
SdkConfig config = SdkConfig.builder(auth)
        .printApiInfo(true)
        .maxRetries(3)
        .logLevel("DEBUG")
        .build();

SdkConfig noisy = config.toBuilder().printCurl(true).build();   // derive a variant
SdkConfig quiet = config.withOverrides(b -> b.printApiInfo(false));
```

| Setting | Default | Notes |
|---|---|---|
| `apiVersion` | `"v4"` | Fills `{api_version}` in every path. `"v4"`, `"4"` or `ApiVersion.V4`. The token path does not follow it |
| `timeoutSeconds` | `30.0` | Hard ceiling on **one attempt**, body preparation included - see [Timeouts](#timeouts) |
| `connectTimeoutSeconds` | `10.0` | The connect phase only |
| `maxRetries` | `1` | Total attempts **including the first**, so `1` disables retry |
| `retryableHttpCodes` | `{429,500,502,503,504}` | Inert while `maxRetries` is `1`. At most 500 codes |
| `unsafeRetryableHttpCodes` | `{}` | The second gate a `POST`/`PUT`/`PATCH`/`DELETE` must also pass, so by default a write is never replayed on a status |
| `retryBackoffSeconds` | `0.5` | Base for exponential backoff with full jitter |
| `retryBackoffMaxSeconds` | `20.0` | Ceiling for one sleep, `Retry-After` included |
| `verifySsl` | `true` | `false` installs a trust-all context and warns |
| `caBundle` | `null` | PEM path; the trust store is exactly that bundle. Contradicts `verifySsl=false` |
| `proxyUrl` | `null` | An HTTP proxy; its userinfo answers a `407`. A socks proxy is refused: the JDK client reaches HTTP proxies only |
| `poolMaxsize` | `10` | Validated and carried; the JDK sizes its pool from `jdk.httpclient.connectionPoolSize` |
| `maxResponseBytes` | 64 MiB | The most of one response body retained |
| `maxRequestBytes` | 64 MiB | The most of one encoded request body, framing included |
| `maxUploadBytes` | 64 MiB | The most the sources of one multipart request may add up to |
| `maxMultipartOverheadBytes` | 1 MiB | The most a multipart body's boundaries and part headers may add up to |
| `maxCaBundleBytes` | 8 MiB | The most of a `caBundle` read |
| `defaultHeaders` | `{}` | RFC 9110 names and values; a `Content-Type` here must be a media type |
| `raiseOnError` | `false` | `true` throws `SdkError` instead of returning a failed response |
| `responseFormat` | `"map"` | Which member `getData()` follows: `"map"`, `"json"` or `"dto"`. Beats `EXSITED_RESPONSE_FORMAT`; a single call beats both |
| `userAgent` | `exsited-java-sdk/<version>` | Must be non-blank and sendable as a header value |
| `logLevel` | `"WARNING"` | `DEBUG`/`INFO`/`WARNING`/`ERROR`/`CRITICAL`, gated by the client before a record reaches the logger or the file |
| `logBodies` | `false` | Payload and response in DEBUG records, redacted |
| `logFile` | `null` | Also append this client's records here; blank means none |
| `printApiInfo` | `false` | Client, method, endpoint, params, status, attempt, elapsed |
| `printRequestPayload` | `false` | Redacted |
| `printResponseBody` | `false` | Bytes summarised, never dumped |
| `printHeaders` | `false` | Redacted |
| `printCurl` | `false` | A POSIX-shell curl, token redacted |
| `printOnlyOnError` | `false` | Suppress the block on 2xx. A send with no response is always shown |
| `printFormat` | `"pretty"` | Or `"compact"`, one line per call |
| `printColor` | `false` | Forced off by `NO_COLOR`, `TERM=dumb`, or stdout not being a terminal |
| `printMaxChars` | `0` | `0` is unlimited |
| `httpClient` | `null` | Inject a JDK `HttpClient` of your own. Cannot be combined with `verifySsl=false`, `caBundle` or `proxyUrl` |

There is no `maxTokenCacheBytes` option: the token cache ceiling is the
`FileTokenStore.MAX_TOKEN_CACHE_BYTES` constant, 1 MiB, fixed by the auth layer.

`responseFormat` is the **only** setting the SDK reads from the environment, as
`EXSITED_RESPONSE_FORMAT`, once when a config is built. Blank means unset; anything
other than the three words is refused, naming them.

## Multiple instances

Declare each instance as its own `AuthConfig` (or `SdkConfig`) and select by name
or position. Each is isolated: its own HTTP client, token provider and token
store. **The first entry is the default**, and an instance you never ask for never
authenticates.

```java
import com.exsited.client.ClientRegistry;

try (ClientRegistry registry = new ClientRegistry(List.of(liveAuth, stagingAuth))) {
    registry.getClient();                 // the first entry, the default
    registry.getClient("staging");        // by clientName
    registry.getClient(1);                // by position; negatives count from the end
}
```

Clients are cached **by position**, and two entries answering to one `clientName`
are refused when the registry is built; `AuthConfig` defaults that name to
`default`, so two entries that both leave it unset collide. `closeAllClients()`
closes every client the registry built and leaves one handed in through
`addClient` to its owner.

**Or register once and build anywhere:**

```java
ClientRegistry.registerInstances(List.of(liveAuth, stagingAuth));   // at startup

ClientRegistry.buildClient();                   // the default
ClientRegistry.buildClient("staging");          // by clientName
ClientRegistry.buildClient(null, b -> b.printApiInfo(true));   // overrides on a copy
ClientRegistry.listInstanceNames();             // ["live", "staging"]
```

`buildClient(instance, credentials, overrides)` selects from a list of your own
without touching the process-wide one; `SdkConfig.fromCredentials(list, instance)`
does the same without a client.

## Authentication

Automatic. OAuth2 `client_credentials` against **`POST /api/v4/oauth2/token`** on
first use, cached and renewed before expiry.

**A new grant on the same client id revokes the token issued before it.** A
`401` whose `code`, `error` or `message` names a token failure (`unauthorized`,
`invalid_access_token`, `invalid_token`, `expired_token`, `invalid_request`, as a
whole value or a whole word) clears exactly the refused token, refreshes once and
replays the call once with the same `X-Idempotency-Key`. A `401` whose body is not
a JSON object replays only a safe method. Anything else is returned as-is.

**A refused grant's message carries the token endpoint's text.** A token request
that answers outside 2xx raises `SdkAuthError("token request failed with <status>:
<text>")`, with up to 500 characters of the endpoint's response in the message.
This is the only failure message that holds a response body. `getRawResponse()` is
redacted, the message is not, so log `getStatusCode()` and `getErrorType()` rather
than `getMessage()` where that text must stay out of the log.

**Wrong credentials can come back as a redirect.** The token endpoint may send a
request it refuses to its sign-in page (`302`) rather than answer `401`. A `3xx`, or
the HTML page a followed redirect lands on, raises `SdkAuthError("the token request
was refused: check client_id, client_secret and redirect_uri (the API answered 302,
redirecting to its sign-in page)")`, with neither the response nor the Location in
the message.

By default the token is cached in a **per-user file**, keyed by a SHA-256
fingerprint of `baseUrl`, `clientId`, `clientSecret` and `grantType`, so one file
serves many instances:

| Platform | Path |
|---|---|
| Windows | `%LOCALAPPDATA%\exsited\exsited_java_token.json` |
| macOS | `~/Library/Caches/exsited/exsited_java_token.json` |
| Linux | `$XDG_CACHE_HOME/exsited/exsited_java_token.json`, else `~/.cache/exsited/exsited_java_token.json` |

```java
AuthConfig.builder()...tokenCachePath("/path/of/your/choosing/token.json").build();
AuthConfig.builder()...inMemoryToken().build();    // nothing on disk

// The full constructor is (config, tokenStore, transport, retryPolicy, tokenProvider);
// each collaborator after the config may be null to take the default.
new ExsitedClient(config, new MemoryTokenStore(), null, null, null);
```

Processes sharing that cache are serialised by `FileChannel.tryLock()` on a
sidecar file, which the OS releases when the holder exits or is killed. If the
lock cannot be taken at all - a read-only cache directory, a filesystem with no
lock support - **the call still works**, coordinating within this JVM only, and
`crossProcessLockingDegraded()` says so:

```java
FileTokenStore store = new FileTokenStore(FileTokenStore.defaultCachePath());
String why = store.crossProcessLockingDegraded();
```

Failing open is deliberate: a cache that cannot be shared should not stop the call.

## Requests

A family is a class bound to one client, reached by a typed getter named after
the package name in camelCase:

```java
client.accounts().getAccount("W13ZGY");
client.purchaseOrders().getPurchaseOrders(Map.of("limit", 10));
client.resource("accounts", Accounts.class).getAccount("W13ZGY");
new Accounts(client).getAccount("W13ZGY");
```

`ResourceBinding.registerResourceModule(name, Factory::new)` registers a family of
your own at run time, and `client.resource(name)` then answers to it.

An endpoint takes the values that identify the resource, then the payload, then
an optional `RequestOptions`, and is overloaded: a read has
`()`, `(query)` and `(query, options)`, anything else `(args)` and
`(args, options)`. `<Family>Urls` holds the path templates and body keys.

`RequestOptions` is an immutable snapshot, built with `RequestOptions.builder()`
or taken as `RequestOptions.none()`:

```java
RequestOptions options = RequestOptions.builder()
        .queryParams(Map.of("limit", 5))
        .header("X-Trace", "abc")
        .raiseOnError(true)
        .responseFormat("dto")
        .build();
```

`pathParams`, `queryParams`, `jsonBody`, `formData`, `files`, `headers`,
`authenticated`, `raiseOnError`, `responseFormat` and `dtoFactory`. The generic
senders, `client.sendGetRequest(path, options)` and the same for `POST`, `PUT`,
`PATCH` and `DELETE`, reach any path the families do not.

### One header grammar

Caller headers merge into the SDK's case-insensitively, with the SDK's own
`Authorization`, `Accept` and `User-Agent` spellings kept, and a caller can never
replace `Authorization`. A name or value outside RFC 9110's grammar is refused as
`SdkUsageError` before the token grant.

### `Content-Type` is validated, not carried

A `Content-Type`, per call or in `defaultHeaders`, must be a media type, and one
that contradicts the body it would travel with is refused before the token grant:
the transport writes the body's own type. `jsonBody` with `formData` or with
`files` is refused; `formData` with `files` is one multipart body.

Every `POST` carries an **`X-Idempotency-Key`**, generated once per call and kept
across retries and the `401` replay, so the v4 API can recognise a repeat. A key
passed per call is sent as it is; one set in `defaultHeaders` is replaced on every
`POST`, since one key for every create would collapse them into the first.

Query values are normalised: dates and times as fixed
ISO 8601 with seconds, `BigDecimal` as plain text, any `Iterable` exploded, an enum
by `toString()`, a space as `%20`. `QueryParams.filterParam("status")` and
`QueryParams.sortParam(...)` build the v4 filter and sort names; equality takes no
operator suffix, so build the names rather than concatenating. A name given twice,
a nested map, a blank path parameter and a dot-only path segment are refused
before a socket opens.

## Pagination

```java
for (Map<String, Object> account : Pagination.iterRecords(client, AccountsUrls.ACCOUNTS,
        Pagination.DEFAULT_RECORD_KEY, Map.of(QueryParams.filterParam("status"), "ACTIVE"))) {
    System.out.println(account.get("id"));
}

for (ApiResponse<Dto> page : Pagination.iterPages(client, AccountsUrls.ACCOUNTS, null, 3, true)) {
    System.out.println(page.getStatusCode());
}
```

Both are lazy: nothing is sent until the loop asks for a page. `iterPages(client,
path, queryParams)` and `iterRecords` raise on the first failed page; with
`raiseOnError` off, `iterPages` yields the failed page and stops. `maxPages` caps
the walk, and a value below 1 is refused.

**Pagination is detected per page, not configured.** The account and item
families page by **cursor**; most other v4 listings page by **offset**. A
listing's `pagination` block decides which, and both are walked. `queryParams` is
re-sent with every page, because a cursor is a position within one filtered
listing. Passing `cursor` or `offset` yourself is an `SdkUsageError`: the pager
owns them. `next_page` ends as the literal string `"NULL"`, never truth-test it.
Some listings key their records by resource name rather than `data`; pass that key
(`<Family>Urls.<LISTING>_KEY`) when they do.

## Responses

```java
response.succeeded()        // 2xx and the body does not say "object": "error"
response.getStatusCode()
response.getHeaders()
response.getContentType()
response.getErrorType()     // ErrorType.NONE on success; VALIDATION, NOT_FOUND, ...
response.getRequestInfo()   // method, URL, path template, attempts, elapsed seconds
response.getErrorMessages() // empty on success
response.getErrorCode()     // the v4 envelope's code
response.getRequestId()
response.getDocUrl()
response.getFieldErrors()   // FieldError: getField(), getCode(), getMessage(), getDocUrl()
response.raiseForStatus()   // throw now, whatever raiseOnError says
```

The envelope members (`getErrorCode()`, `getRequestId()`, `getDocUrl()`,
`getFieldErrors()`) and the `object == "error"` rule are read from the v4 error
envelope, next to the status, headers and error type every response carries.

## Sending and reading

Three send forms, all reaching the wire as the same bytes:

```java
// a map
client.accounts().cancelAccount(id,
        Map.of("account", Map.of("effective_date", "2026-08-31")));

// a JSON String, sent byte-for-byte: it keeps its spacing and key order
client.accounts().cancelAccount(id,
        "{\"account\": {\"effective_date\": \"2026-08-31\"}}");

// a Dto
AccountsDto.AccountCancel body = new AccountsDto.AccountCancel();
body.effective_date = "2026-08-31";
client.accounts().cancelAccount(id, body);
```

A `null` payload is refused before any I/O, and so is a JSON String that is not
one object or array.

Four read members, three **always** populated whatever the configuration:

```java
response.getMap()     // the decoded body: Map, List, String, byte[], or null on 204
response.getJson()    // the text that arrived, spacing and key order intact
response.getDto()     // the typed view, or null when the endpoint names no type
response.getData()    // whichever of the three responseFormat selects
```

`getMap()` is the only one that can tell a key the API **omitted** from one it sent
as **null**, which is why it is never traded away for the typed view.
`responseFormat` resolves highest first: the call, the config,
`EXSITED_RESPONSE_FORMAT`, then `map`.

**A DTO converts nothing.** A field declared `String` reads null when a number
arrives; only an int widening to a double is allowed. Every field is a boxed type,
because a primitive cannot hold a JSON null. A DTO is its declared fields and
nothing else: an all-lower field name is the wire key as it stands and a camelCase
one reads its snake_case form, `@WireName` carries a key that is a Java keyword,
two DTOs with the same field values are equal, and `Dto.fields(type)` lists a
type's fields by wire name. An envelope is declared by overriding `envelopeKey()`.
Every field type was measured from the API's answers, with the sample counts in
the doc comments. A measured object is a nested view named for its owner and
field, and a field that was always null is `Object`.

## Errors

| Type | Means | Costs a request |
|---|---|---|
| `SdkError` | the base: a failed call under `raiseOnError`, or a transport failure | yes |
| `SdkAuthError` | a `401` or `403` | yes |
| `SdkUsageError` | the SDK refused before sending: a bad argument, a closed client | no |
| `SdkAttributeError` | a `SdkUsageError` for an unknown resource name | no |
| `SdkConfigError` | the configuration is wrong | no |
| `SdkTokenCacheError`, `SdkTokenLockError` | the token cache could not be read, written or locked | no |
| `InsecureTransportWarning` | not thrown: the notice for plaintext or unverified TLS | no |

Every `SdkError` answers `getStatusCode()`, `getErrorType()`, `getRawResponse()`,
`getResponseHeaders()`, `getRequestInfo()`, `getErrorMessages()`, `getHint()`,
the four v4 getters, `explain()` for a multi-line account of the failure, and a
one-line `toString()` naming the call by its path template. Bodies and headers are
left out of a message unless `-Dexsited.sdk.errorDetail=full` opts them in.

A transport failure is `ErrorType.TIMEOUT` or `NETWORK`, with a request record and
the `IOException` kept as the cause. An interrupt during a send or a backoff
abandons the call and keeps the thread's interrupt flag, rather than retrying.

## Diagnostics

The `print*` flags render each exchange to the console, secrets redacted:

```java
SdkConfig config = SdkConfig.builder(auth)
        .printApiInfo(true)          // client, method, endpoint, params, status, timing
        .printRequestPayload(true)   // the body sent
        .printResponseBody(true)     // the body received (bytes summarised, never dumped)
        .printHeaders(true)          // request + response headers
        .printCurl(true)             // a copy-pasteable POSIX-shell curl, token redacted
        .printOnlyOnError(true)      // stay silent on 2xx
        .printFormat("pretty")       // or "compact" for one line per call
        .logFile("/var/log/exsited.log")
        .logLevel("INFO")            // exchanges are INFO records, bodies DEBUG
        .logBodies(true)             // payload and response, redacted
        .build();
```

The renderer, `client/Console.java`, prints a plain title line, then
`label: value` rows, `-- section --` rules and raw JSON, so a body copies straight
out of the terminal:

```
Exsited  GET  200  0.2313s
client      : default
api version : v4
method      : GET
endpoint    : https://dev-api.exsited.com/api/v4/accounts
params      : {"limit":1}
status      : 200
attempt     : 1 / 1
elapsed     : 0.2313s
```

`printFormat("compact")` collapses each call to one `exsited | default | GET | ... |
200 | 1/1 | 0.2313s` line. A call that never reached a response is rendered too,
and `printOnlyOnError` never hides it. `Console.renderExchange` and
`Console.buildCurlCommand` are public. Everything printed passes the console
encoder and the control-character neutraliser, so a hostile value cannot drive the
terminal.

Logging is single-line, through the JDK's `System.Logger` to
`exsited.<clientName>`, so it lands wherever the application's logging does
(java.util.logging by default, SLF4J through its platform-logging bridge). The
progress line and each exchange are INFO, a `401` about to be replayed is WARNING,
a send with no response is ERROR, and `logBodies` records are DEBUG. A record names
the path template, never the resolved path. The log file receives the same records
at the same level, one file shared by every client of one name and released when
the last of them closes.

An `http://` `baseUrl`, or `verifySsl(false)`, is announced as an
`InsecureTransportWarning` once per JVM on `System.err` and at WARNING on the
logger and the log file. Loopback is exempt from the plaintext one.
`-Dexsited.sdk.insecureTransportWarning=off` silences the stderr copy.

**Where the records go when the application configures nothing.** `System.Logger`
falls back to java.util.logging, whose default console handler writes INFO and
above to `System.err`. So a client at the default `logLevel("WARNING")` writes
its WARNING and ERROR records there: a `401` before its replay, a send that got
no response, and the insecure-transport notice. That notice is printed once:
when the client's logger already reaches a java.util.logging `ConsoleHandler` at
WARNING, the direct stderr copy is skipped. To keep them off the console, lower
the `exsited` logger, which is the parent of every client's:

```java
private static final java.util.logging.Logger EXSITED =
        java.util.logging.Logger.getLogger("exsited"); // held: JUL keeps loggers weakly

EXSITED.setLevel(java.util.logging.Level.OFF);
```

or put `exsited.level = OFF` in the `logging.properties` the JVM reads.
`logLevel("CRITICAL")` does the same from the SDK's side, and also quiets the log
file.

## Retries

Off by default: `maxRetries` is **total attempts**, and its default of `1` means no
repeat. Raised, a status in `retryableHttpCodes` is retried for a safe method, and
for a state-changing one only when it is in `unsafeRetryableHttpCodes` as well,
which is empty by default. A transport failure that never reached the server
(refused connect, unknown host, TLS handshake) is retried for any method; one that
may have arrived is retried only for an idempotent method (`GET`, `HEAD`,
`OPTIONS`, `TRACE`, `PUT`, `DELETE`). The wait is full jitter over an exponential
window from `retryBackoffSeconds`, or the server's `Retry-After` (seconds or any
of the three RFC 9110 date forms), both capped at `retryBackoffMaxSeconds`.

## Transport

The JDK's `java.net.http.HttpClient`, built from the config: connect timeout,
`verifySsl`, `caBundle`, `proxyUrl`. Redirects are **never** followed; a `3xx` is
returned. A response body is read against `maxResponseBytes` and refused, not
allocated, when it is larger; a request body is judged against `maxRequestBytes`
twice, once as its sources and once encoded. Multipart bodies are built without a
dependency and judged against `maxUploadBytes` and `maxMultipartOverheadBytes`.
Text is decoded with the charset the `Content-Type` declares.

### Timeouts

`connectTimeoutSeconds` bounds the connect phase. `timeoutSeconds` bounds **one
whole attempt**, body preparation and body read included: the JDK's own request
timeout stops at the response headers, so the transport enforces the rest itself
and reports `ErrorType.TIMEOUT` when the ceiling is reached.

## Endpoints

864 endpoints in 91 families. Every endpoint, with its request and response bodies,
is documented at [exsited.mintlify.app](https://exsited.mintlify.app/introduction).
The families, each with its endpoint count, are below; `DELETE /component/{component_uuid}` is counted in both
`custom_components` and `custom_features`, so they add up to 865:

`aba_files` 1, `account_emails` 1, `account_groups` 5, `accounting_codes` 5,
`accounts` 65, `action_scripts` 5, `additional_codes` 5, `alert_responsibilities` 5,
`alert_responsibility` 5, `alerts` 5, `applications` 5, `approval_profiles` 5,
`approval_user_groups` 5, `approvals` 5, `attribute_attachments` 1,
`billing_periods` 5, `bookings` 33, `charging_periods` 5, `communication_profiles` 5,
`communications` 1, `countries` 3, `credit_note_applications` 3, `credit_notes` 14,
`custom_attributes` 5, `custom_components` 39, `custom_events` 5,
`custom_features` 7, `custom_fields` 2, `custom_forms` 5, `day_of_month` 2,
`departments` 2, `designations` 5, `document_templates` 5, `email_templates` 5,
`employees` 5, `events` 4, `express` 1, `external_databases` 7,
`fulfillment_profiles` 5, `fulfillment_terms` 5, `gift_certificates` 12,
`integration_connections` 2, `inventory_adjustments` 2, `invoice_terms` 5,
`invoices` 30, `item_fulfillments` 9, `item_receipts` 7, `items` 41, `labours` 19,
`milestones` 5, `note_attachments` 1, `note_groups` 7, `orders` 60, `organisation` 1,
`organisations` 5, `payments` 15, `portals` 7, `pricing_periods` 5,
`proforma_invoices` 4, `purchase_credit_note_applications` 2,
`purchase_credit_notes` 8, `purchase_express` 1, `purchase_invoices` 23,
`purchase_orders` 23, `purchase_payments` 15, `purchase_refunds` 5, `quote_orders` 1,
`receive_terms` 5, `reconciliations` 2, `refunds` 12, `rental_assets` 14,
`reports` 7, `return_merchandise_authorisations` 19,
`return_vendor_authorizations` 7, `roles` 5, `sales_reps` 5, `salutations` 5,
`settings` 86, `settings_workflows` 2, `shipping_packages` 5, `shipping_rules` 6,
`system_events` 2, `tasks` 18, `teams` 5, `time_periods` 5, `timesheets` 5, `uom` 5,
`usages` 6, `webhooks` 1, `workflows` 17, `zones` 5.

What the API does that the code cannot tell you:

- **`POST /api/v4/accounts` creates an account**, answered `201`, through
  `createAccount`; `POST /api/v4/express` creates one too.
- **Reads are nested, writes are flat.** `currency` reads back as an object and is
  written as `"AUD"`. Posting a response body back unchanged is a 400.
- **`billing_start_date` is an enum, not a date** - `"DAY_OF_MONTH"` and the like.
- **Notes are form-encoded; the image is base64 JSON.**
- **`next_page` is the literal string `"NULL"`** at the end of an offset listing,
  not null.
- **A locked account's delete is a 409 with code `internal_error`** - a business
  rule wearing a server fault's name. Not retryable.

## Architecture

### The call path

A call goes family class, `ExsitedClient`, `Transport`, `HttpClient`. The family
names the path and the view; the client merges headers, adds the bearer token and
the idempotency key, and handles the `401` replay; the transport builds the URL,
encodes the body, applies the retry policy and the timeout, and decodes the
answer into an `ApiResponse`. `Console`, `FileLogSink` and `Redaction` see every
exchange and redact before anything leaves.

### Objects, per instance

One `SdkConfig` per instance, frozen. One `ExsitedClient` per config, owning one
`Transport`, one `TokenProvider` and one `TokenStore`. Family objects are built on
first use per client and hold only the client. `ClientRegistry` holds one cached
client per position.

### Design notes

- **No dependencies.** The JSON reader, the multipart encoder and the logging are
  the JDK's or hand-written, so a consumer takes on nothing and a build resolves
  nothing but Maven's own plugins.
- **The auth layer is built for the Exsited token endpoint**: token path, grant,
  cache format, lock behaviour and `401` rules.
- **The v4 conventions are the API's**: cursor and offset pagination, the error
  envelope, `object == "error"`, `X-Idempotency-Key`, the filter and sort helpers.

## Troubleshooting

| Symptom | Cause |
|---|---|
| `SDK_VERSION` is `0.0.0+source` | Running from classes rather than the jar; the version is read from the jar manifest |
| `SdkAuthError` on the first call | The credentials were refused: check `baseUrl`, `clientId`, `clientSecret` and `redirectUri` against the API client on the instance |
| Every call `401`s after a while | Another process granted a token on the same client id; each call refreshes once and replays |
| `getData()` is not the map it used to be | Something set `responseFormat`: the call, the client, or `EXSITED_RESPONSE_FORMAT`. `getMap()` never changes |
| `SdkConfigError: responseFormat` on a client that names none | `EXSITED_RESPONSE_FORMAT` carries something other than `map`, `json` or `dto` |
| A listing returns page one and stops | The records are not under `data`; pass the family's `<LISTING>_KEY` |
| `getDto()` is null on a successful call | The endpoint names no typed view, or the body is binary |
| `SdkAttributeError` from `client.resource(name)` | The name is not registered; the message lists the registered names |
| `InsecureTransportWarning` on stderr | An `http://` base URL or `verifySsl(false)` |

## Public API

- `com.exsited.client`: `ExsitedClient`, `RequestOptions`, `ApiResponse`,
  `RequestInfo`, `ErrorType`, `FieldError`, `Pagination`, `Console`,
  `ClientRegistry`, `BoundResource`, `ResourceBinding`, `DtoFactory`
- `com.exsited.config`: `SdkConfig`, `AuthConfig`, `ApiVersion`, `ResponseFormat`
- `com.exsited.errors`: `SdkError`, `SdkAuthError`, `SdkUsageError`,
  `SdkAttributeError`, `SdkConfigError`, `SdkTokenCacheError`, `SdkTokenLockError`,
  `InsecureTransportWarning`
- `com.exsited.auth`: `TokenProvider`, `TokenStore`, `FileTokenStore`,
  `MemoryTokenStore`, `TokenRecord`, `TokenCredentials`
- `com.exsited.http`: `Transport`, `RetryPolicy`, `UrlBuilder`, `QueryParams`,
  `MediaType`
- `com.exsited.json`: `Json`
- `com.exsited`: `Dto`, `WireName`, `Redaction`
- `com.exsited.resources`: `PaginationDto`, and per family `<Family>`,
  `<Family>Urls`, `<family>_dto.<Family>Dto` and the deprecated `<Family>Resource`

Anything else is package-private and may change without notice.
