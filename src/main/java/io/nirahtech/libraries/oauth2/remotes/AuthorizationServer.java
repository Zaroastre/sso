package io.nirahtech.libraries.oauth2.remotes;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.nirahtech.libraries.oauth2.data.AccessToken;
import io.nirahtech.libraries.oauth2.data.Scope;
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
        stringBuilder.append(request.asURIParameters());
        
        final HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(stringBuilder.toString()))
                .POST(BodyPublishers.ofString(""))
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
                System.out.println(httpResponse.body());
                Gson gson = new GsonBuilder().create();
                final Map<String, String> json = gson.fromJson(httpResponse.body(), new TypeToken<Map<String, String>>(){}.getType());
                Set<Scope> scopes = Set.of(json.get("scope").split(" ")).stream().map(Scope::new).collect(Collectors.toSet());
                code = Optional.of(new AccessToken(json.get("access_token"), Integer.parseInt(json.get("expires_in")), scopes, json.get("token_type"), json.get("id_token")));
            }
        } else if (Objects.nonNull(httpResponse) ) {
            System.out.println(httpRequest);
            System.out.println(httpResponse);
            System.out.println(httpResponse.body());
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