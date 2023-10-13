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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.nirahtech.libraries.oauth2.dto.UserInfoRequest;

/**
 * ResourceOwner
 */
public final class ResourceServer {
    private final URI uri;

    private ResourceServer(final URI uri) {
        this.uri = uri;
    }

    public final Optional<Map<String, String>> submitRequestForUserInfo(final UserInfoRequest request) {
        Optional<Map<String, String>> userInfos = Optional.empty();
        StringBuilder builder = new StringBuilder(this.uri.toString());
        builder.append("?access_token=").append(request.getAccessToken().value());
        final HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(builder.toString()))
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
                System.out.println(httpResponse.body());
                Gson gson = new GsonBuilder().create();
                final Map<String, String> json = gson.fromJson(httpResponse.body(), new TypeToken<Map<String, String>>(){}.getType());
                userInfos = Optional.of(json);
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