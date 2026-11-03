# Exsited Java SDK

Java client for the Exsited v4 REST API. It handles OAuth2 token requests with a
token cache, retries with backoff, cursor and offset pagination, proxies and
corporate TLS, and a typed error hierarchy, and it has no dependencies beyond the
JDK. Every call returns the response body exactly as the API sent it
(`getMap()`), with an optional typed view of the same data (`getDto()`).

## Requirements

- JDK 21 or newer
- An Exsited instance and an API client id and secret

## Install

Add the dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.exsited</groupId>
    <artifactId>exsited</artifactId>
    <version>4.0.1</version>
</dependency>
```

For Gradle:

```groovy
implementation 'com.exsited:exsited:4.0.1'
```

## Configure credentials

The SDK takes its credentials from an `AuthConfig` that you build. Reading them
from environment variables is one way:

```bash
export EXSITED_BASE_URL=https://your-instance.example.com
export EXSITED_CLIENT_ID=your-client-id
export EXSITED_CLIENT_SECRET=your-client-secret
export EXSITED_REDIRECT_URI=https://your-app.example.com/callback
```

```java
AuthConfig auth = AuthConfig.builder()
        .baseUrl(System.getenv("EXSITED_BASE_URL"))
        .clientId(System.getenv("EXSITED_CLIENT_ID"))
        .clientSecret(System.getenv("EXSITED_CLIENT_SECRET"))
        .redirectUri(System.getenv("EXSITED_REDIRECT_URI"))
        .clientName("default")
        .build();
```

Or pass the values directly:

```java
AuthConfig auth = AuthConfig.builder()
        .baseUrl("https://your-instance.example.com")
        .clientId("your-client-id")
        .clientSecret("your-client-secret")
        .redirectUri("https://your-app.example.com/callback")
        .clientName("default")
        .build();
```

A blank required field is refused when the config is built, and the message names
the field. The access token is cached on disk between runs; call
`.inMemoryToken()` on the builder to keep it in memory only.

## First call

```java
import com.exsited.client.ApiResponse;
import com.exsited.client.ExsitedClient;
import com.exsited.config.AuthConfig;
import com.exsited.config.SdkConfig;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.AccountList;

public class FirstCall {
    public static void main(String[] args) {
        AuthConfig auth = AuthConfig.builder()
                .baseUrl(System.getenv("EXSITED_BASE_URL"))
                .clientId(System.getenv("EXSITED_CLIENT_ID"))
                .clientSecret(System.getenv("EXSITED_CLIENT_SECRET"))
                .redirectUri(System.getenv("EXSITED_REDIRECT_URI"))
                .clientName("default")
                .build();
        SdkConfig config = SdkConfig.builder(auth).raiseOnError(true).build();

        try (ExsitedClient client = new ExsitedClient(config)) {
            ApiResponse<AccountList> response = client.accounts().getAccounts(java.util.Map.of("limit", 5));
            System.out.println(response.getStatusCode());
            System.out.println(response.getMap());
        }
    }
}
```

`ExsitedClient` is `AutoCloseable`; the try-with-resources block closes the HTTP
client the SDK built. Each resource family is a typed getter on the client, named
after the resource in camelCase, for example `client.accounts()` or
`client.purchaseOrders()`.

## Errors and pagination

With `raiseOnError(true)` a failed call throws an `SdkError`, whose
`getStatusCode()` and `explain()` describe the failure. Without it, a failed call
is returned as a response for you to inspect.

Listings that span several pages can be walked lazily:

```java
for (Map<String, Object> account : Pagination.iterRecords(client, AccountsUrls.ACCOUNTS,
        Pagination.DEFAULT_RECORD_KEY, Map.of("limit", 50))) {
    System.out.println(account.get("id"));
}
```

## Documentation

The API reference and guides are at https://exsited.mintlify.app.

## License

MIT. See the `LICENSE` file.
