package io.nirahtech.libraries.oauth2.remotes;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Objects;
import java.util.Optional;

import io.nirahtech.libraries.oauth2.data.AccessToken;
import io.nirahtech.libraries.oauth2.dto.AccessTokenRequest;

/**
 * ResourceOwner
 */
public final class AuthorizationServer {
    private final URI uri;

    private AuthorizationServer(final URI uri) {
        this.uri = uri;
    }

    public final Optional<AccessToken> submitRequestForAccessToken(final AccessTokenRequest request) {
        Optional<AccessToken> code = Optional.empty();

        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.uri.toString());
        stringBuilder.append("?");
        stringBuilder.append(String.format("client_id=%s", request.getClientId().value()));
        stringBuilder.append("&");
        stringBuilder.append(String.format("code=%s", request.getAuthorizationCode().code()));
        stringBuilder.append("&");
        stringBuilder.append(String.format("redirect_uri=%s", request.getRedirectUri().toString()));
        stringBuilder.append("&");
        stringBuilder.append(String.format("grant_type=%s", request.getGrantType().name().toLowerCase()));
        
        final HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(stringBuilder.toString()))
                .GET()
                .header("Content-Type", request.getContentType())
                .build();
        HttpResponse<String> httpResponse = null;
        final HttpClient httpClient = HttpClient.newHttpClient();
        try {
            httpResponse = httpClient.send(httpRequest, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        if (Objects.nonNull(httpResponse) && httpResponse.statusCode() >= 200 && httpResponse.statusCode() <= 299) {
            if (!httpResponse.body().isEmpty()) {
                code = Optional.of(new AccessToken(httpResponse.body()));
            }
        }
        return code;
    }

    public URI getUri() {
        return uri;
    }

    public static final AuthorizationServer create(final URI uri) {
        return new AuthorizationServer(uri);
    }
}