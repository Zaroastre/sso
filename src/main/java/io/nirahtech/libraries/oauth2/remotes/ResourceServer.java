package io.nirahtech.libraries.oauth2.remotes;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import io.nirahtech.http.client.HttpClient;
import io.nirahtech.http.common.HttpRequest;
import io.nirahtech.http.common.HttpResponse;
import io.nirahtech.http.common.HttpVerb;
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
        builder.append("?access_token=").append(request.getAccessToken().getValue());
        final HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(builder.toString()))
                .method(HttpVerb.GET)
                .headers("Authorization", String.format("Bearer: %s", request.getAccessToken().getValue()))
                .build();
        HttpResponse httpResponse = null;
        final HttpClient httpClient = HttpClient.newHttpClient();
        httpResponse = httpClient.send(httpRequest);
        // try {
        // } catch (IOException | InterruptedException e) {
        //     e.printStackTrace();
        // }
        if (Objects.nonNull(httpResponse) && httpResponse.statusCode() >= 200 && httpResponse.statusCode() <= 299) {
            try {
                if (httpResponse.body().available() > 0) {
                    byte[] body = new byte[httpResponse.body().available()];
                    httpResponse.body().read(body);
                    Gson gson = new GsonBuilder().create();
                    final Map<String, String> json = gson.fromJson(new String(body, StandardCharsets.UTF_8), new TypeToken<Map<String, String>>(){}.getType());
                    userInfos = Optional.of(json);
                }
            } catch (JsonSyntaxException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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