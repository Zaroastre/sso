package io.nirahtech.libraries.oauth2.remotes;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

import io.nirahtech.http.client.HttpClient;
import io.nirahtech.http.common.HttpRequest;
import io.nirahtech.http.common.HttpResponse;
import io.nirahtech.http.common.HttpVerb;
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
        stringBuilder.append(request.asURIParameters());
        
        final HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(stringBuilder.toString()))
                .method(HttpVerb.GET)
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
                    code = Optional.of(new AuthorizationCode(new String(body, StandardCharsets.UTF_8)));
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return code;
    }

    public URI getUri() {
        return uri;
    }

    public static final ResourceOwner create(final URI uri) {
        return new ResourceOwner(uri);
    }
}