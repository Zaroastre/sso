package io.nirahtech.libraries.oauth2.remotes;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import io.nirahtech.libraries.oauth2.dto.UserInfoRequest;

/**
 * ResourceOwner
 */
public final class ResourceServer {
    private final URI uri;

    private ResourceServer(final URI uri) {
        this.uri = uri;
    }

    public final Optional<Map<String, Object>> submitRequestForUserInfo(final UserInfoRequest request) {
        Optional<Map<String, Object>> userInfos = Optional.empty();
        final HttpRequest httpRequest = HttpRequest.newBuilder(this.uri)
                .GET()
                .header("Authorization", String.format("Bearer: %s", request.getAccessToken().value()))
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
                userInfos = Optional.of(Map.of());
            }
        } else if (Objects.nonNull(httpResponse)) {
            System.out.println(this.uri);
            System.out.println(httpResponse.body());
        }
        return userInfos;
    }

    public URI getUri() {
        return uri;
    }

    public static final ResourceServer create(final URI uri) {
        return new ResourceServer(uri);
    }
}