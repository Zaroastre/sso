package io.nirahtech.libraries.oauth2.remotes;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import io.nirahtech.http.client.HttpClient;
import io.nirahtech.http.common.HttpRequest;
import io.nirahtech.http.common.HttpResponse;
import io.nirahtech.http.common.HttpVerb;
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
                .method(HttpVerb.POST)
                .headers("Content-Type", request.getContentType())
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
                    Set<Scope> scopes = Arrays.asList(json.get("scope").split(" ")).stream().map(Scope::new).collect(Collectors.toSet());
                    code = Optional.of(new AccessToken(json.get("access_token"), Integer.parseInt(json.get("expires_in")), scopes, json.get("token_type"), json.get("id_token")));
                }
            } catch (JsonSyntaxException | NumberFormatException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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