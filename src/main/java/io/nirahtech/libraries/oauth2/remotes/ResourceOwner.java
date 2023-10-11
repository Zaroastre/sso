package io.nirahtech.libraries.oauth2.remotes;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import io.nirahtech.libraries.oauth2.data.AuthorizationCode;
import io.nirahtech.libraries.oauth2.dto.AuthorizationCodeRequest;

/**
 * ResourceOwner
 */
public final  class ResourceOwner {
    private final URI uri;

    private ResourceOwner(final URI uri) {
        this.uri = uri;
    }

    public final Optional<AuthorizationCode> submitRequestForAuthorizationCode(final AuthorizationCodeRequest request) {
        Optional<AuthorizationCode> code = Optional.empty();

        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.uri.toString());
        stringBuilder.append("?");
        stringBuilder.append("scope=");
        stringBuilder.append(String.format("%s", request.getScopes().stream().map(scope -> scope.value()).collect(Collectors.joining(" "))));
        request.getRedirectUri().ifPresent(redirectUri -> {
            stringBuilder.append("&");
            stringBuilder.append(String.format("redirect_uri=%s", redirectUri.toString()));
        });
        request.getState().ifPresent(state -> {
            stringBuilder.append("&");
            stringBuilder.append(String.format("state=%s", state));
        });
        stringBuilder.append("&");
        stringBuilder.append(String.format("response_type=%s", request.getResponseType().name().toLowerCase()));
        stringBuilder.append("&");
        stringBuilder.append(String.format("client_id=%s", request.getClientId().value()));
        
        final HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(stringBuilder.toString()))
                .GET()
                .header("Content-Type", request.getContentType())
                .build();
        HttpResponse<String> httpResponse = null;
        try (final HttpClient httpClient = HttpClient.newHttpClient()) {
            httpResponse = httpClient.send(httpRequest, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        if (Objects.nonNull(httpResponse) && httpResponse.statusCode() >= 200 && httpResponse.statusCode() <= 299) {
            if (!httpResponse.body().isEmpty()) {
                code = Optional.of(new AuthorizationCode(httpResponse.body()));
            }
        }
        return code;
    }

    public static final ResourceOwner create(final URI uri) {
        return new ResourceOwner(uri);
    }
}