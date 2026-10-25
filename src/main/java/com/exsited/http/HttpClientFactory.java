package com.exsited.http;

import com.exsited.Redaction;
import com.exsited.config.SdkConfig;
import com.exsited.errors.SdkConfigError;
import com.exsited.errors.SdkUsageError;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedTrustManager;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.Locale;

/** Builds the JDK {@link HttpClient} a {@link Transport} sends over, from an {@link SdkConfig}. */
final class HttpClientFactory {

    private HttpClientFactory() {}

    /** The client a transport built from this config sends over; the transport owns and closes it. */
    static HttpClient create(SdkConfig config) {
        HttpClient.Builder builder = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(timeoutMillis(config.getConnectTimeoutSeconds())))
                // A 3xx is returned, never followed with the token attached.
                .followRedirects(HttpClient.Redirect.NEVER);
        if (config.getProxyUrl() != null && !config.getProxyUrl().isBlank()) {
            applyProxy(builder, config.getProxyUrl());
        }
        if (config.getCaBundle() != null && !config.getCaBundle().isBlank()) {
            applyCaBundle(builder, config.getCaBundle(), config.getMaxCaBundleBytes());
        } else if (!config.isVerifySsl()) {
            applyTrustAll(builder);
        }
        return builder.build();
    }

    /** A configured number of seconds as milliseconds, never rounding down to zero. */
    static long timeoutMillis(double seconds) {
        return Math.max(1L, (long) (seconds * 1000));
    }

    /** The proxy URL's host and port, and its userinfo as the answer to a 407. */
    private static void applyProxy(HttpClient.Builder builder, String proxyUrl) {
        String scheme = proxyUrl.substring(0, proxyUrl.indexOf(':')).toLowerCase(Locale.ROOT);
        String rest = proxyUrl.substring(proxyUrl.indexOf("//") + 2);
        int end = rest.length();
        for (int i = 0; i < rest.length(); i++) {
            char character = rest.charAt(i);
            if (character == '/' || character == '?' || character == '#') {
                end = i;
                break;
            }
        }
        String netloc = rest.substring(0, end);
        String userInfo = null;
        int at = netloc.lastIndexOf('@');
        if (at >= 0) {
            userInfo = netloc.substring(0, at);
            netloc = netloc.substring(at + 1);
        }
        String host;
        String portText;
        if (netloc.startsWith("[")) {
            int close = netloc.indexOf(']');
            host = netloc.substring(1, close);
            String tail = netloc.substring(close + 1);
            portText = tail.startsWith(":") ? tail.substring(1) : "";
        } else {
            int colon = netloc.indexOf(':');
            host = colon < 0 ? netloc : netloc.substring(0, colon);
            portText = colon < 0 ? "" : netloc.substring(colon + 1);
        }
        // A portless proxy URL means the scheme's default: 443 for https, else 80.
        int port = portText.isEmpty() ? ("https".equals(scheme) ? 443 : 80)
                : Integer.parseInt(portText);
        builder.proxy(ProxySelector.of(InetSocketAddress.createUnresolved(host, port)));
        if (userInfo != null && !userInfo.isEmpty()) {
            int colon = userInfo.indexOf(':');
            String user = percentDecode(colon < 0 ? userInfo : userInfo.substring(0, colon));
            char[] password = percentDecode(colon < 0 ? "" : userInfo.substring(colon + 1))
                    .toCharArray();
            builder.authenticator(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // The proxy's challenge only: an origin's 401 is the token layer's.
                    return getRequestorType() == RequestorType.PROXY
                            ? new PasswordAuthentication(user, password.clone()) : null;
                }
            });
        }
    }

    /** Decode the userinfo's percent-escapes, which carry {@code @}, {@code :} or {@code /}. */
    private static String percentDecode(String text) {
        StringBuilder out = new StringBuilder(text.length());
        java.io.ByteArrayOutputStream bytes = new java.io.ByteArrayOutputStream();
        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (character == '%' && i + 2 < text.length()) {
                try {
                    bytes.write(Integer.parseInt(text.substring(i + 1, i + 3), 16));
                    i += 2;
                    continue;
                } catch (NumberFormatException notAnEscape) {
                    // fall through: a bare % is kept as written
                }
            }
            if (bytes.size() > 0) {
                out.append(new String(bytes.toByteArray(), StandardCharsets.UTF_8));
                bytes.reset();
            }
            out.append(character);
        }
        if (bytes.size() > 0) {
            out.append(new String(bytes.toByteArray(), StandardCharsets.UTF_8));
        }
        return out.toString();
    }

    /** Trust exactly the certificates in a PEM bundle, reading at most {@code maxBundleBytes}. */
    private static void applyCaBundle(HttpClient.Builder builder, String pemPath, long maxBundleBytes) {
        try {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            int index = 0;
            byte[] bundle = readAtMost(Path.of(pemPath), maxBundleBytes);
            if (bundle.length > maxBundleBytes) {
                throw new SdkUsageError("caBundle: " + Redaction.redactPath(pemPath)
                        + " is over the " + maxBundleBytes + "-byte maxCaBundleBytes ceiling, "
                        + "so it was not parsed - raise the ceiling with "
                        + "SdkConfig.builder(...).maxCaBundleBytes(bytes) if the bundle is "
                        + "meant to be that large");
            }
            try (var stream = new java.io.ByteArrayInputStream(bundle)) {
                for (Certificate certificate : factory.generateCertificates(stream)) {
                    keyStore.setCertificateEntry("ca" + index++, certificate);
                }
            }
            TrustManagerFactory trust =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trust.init(keyStore);
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, trust.getTrustManagers(), null);
            builder.sslContext(context);
        } catch (SdkUsageError refusal) {
            // Already the refusal naming the ceiling and the knob; wrapping it would bury both.
            throw refusal;
        } catch (Exception error) {
            throw new SdkUsageError("caBundle: " + Redaction.redactPath(pemPath)
                    + " could not be loaded: " + Redaction.describeFailure(error), error);
        }
    }

    /** At most {@code limit + 1} bytes, so at the ceiling and over it differ. */
    private static byte[] readAtMost(Path path, long limit) throws java.io.IOException {
        long ceiling = limit == Long.MAX_VALUE ? Long.MAX_VALUE : limit + 1;
        java.io.ByteArrayOutputStream collected = new java.io.ByteArrayOutputStream();
        byte[] chunk = new byte[8192];
        try (var stream = Files.newInputStream(path)) {
            while (collected.size() < ceiling) {
                int wanted = (int) Math.min(chunk.length, ceiling - collected.size());
                int read = stream.read(chunk, 0, wanted);
                if (read < 0) {
                    break;
                }
                collected.write(chunk, 0, read);
            }
        }
        return collected.toByteArray();
    }

    /** verifySsl=false still encrypts: it only stops checking who is on the other end. */
    private static void applyTrustAll(HttpClient.Builder builder) {
        try {
            // Extended, so the host name check that JSSE runs inside the trust manager is skipped too.
            X509ExtendedTrustManager trustAll = new X509ExtendedTrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new TrustManager[] {trustAll}, new java.security.SecureRandom());
            builder.sslContext(context);
        } catch (Exception error) {
            // In the hierarchy, so a JVM refusing trust-all is a config failure callers can catch.
            throw new SdkConfigError(
                    "verifySsl=false could not be applied: this JVM refused the trust-all "
                            + "TLS context (" + error.getClass().getSimpleName()
                            + "); leave verification on, or pass caBundle instead", error);
        }
    }
}
