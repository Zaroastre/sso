package io.nirahtech.libraries.sso.providers.google;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.nirahtech.libraries.sso.data.AccessToken;
import io.nirahtech.libraries.sso.data.AuthorizationCode;
import io.nirahtech.libraries.sso.data.OAuth2User;
import io.nirahtech.libraries.sso.exceptions.OAuth2Exception;
import io.nirahtech.libraries.sso.providers.AbstractIdentityProvider;
import io.nirahtech.libraries.sso.providers.IdentityProvider;
import io.nirahtech.libraries.sso.providers.IdentityProviderConfiguration;

public class GoogleIdentityProvider extends AbstractIdentityProvider {

    private static final Logger LOGGER = LogManager.getLogManager().getLogger(GoogleIdentityProvider.class.getName());

    public static final IdentityProvider configure(IdentityProviderConfiguration configuration) {
        return new GoogleIdentityProvider(configuration);
    }

    private GoogleIdentityProvider(final IdentityProviderConfiguration configuration) {
        super(configuration);
    }

    private AccessToken retrieveAccessToken(final URI accessTokenEndpoint, final String clientId, final String clientSecret,
            final AuthorizationCode authorizationCode, final URI redirectUrl, final String grantType) throws OAuth2Exception {
        String accessToken = null;
        final String requestBody = Map.of(
                "client_id", clientId,
                "client_secret", clientSecret,
                "code", authorizationCode.code(),
                "redirect_uri", redirectUrl.toString(),
                "grant_type", grantType).entrySet().stream()
                .map(entry -> entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        final HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(accessTokenEndpoint)
                .header("content-type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = null;
        try {
            response = this.httpClient.send(httpRequest, BodyHandlers.ofString());
        } catch (Exception exception) {
            throw new OAuth2Exception(exception);
        }
        if (response == null) {
            throw new OAuth2Exception("httpResponse is null");
        }
            String responseBody = response.body();
            Gson gson = new Gson();
            TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {
            };
            Map<String, Object> json = gson.fromJson(responseBody, typeToken.getType());
            if (json.keySet().contains("access_token")) {
                accessToken = json.get("access_token").toString();
            }
        if (accessToken == null) {
            throw new OAuth2Exception("accessToken is null");
        }
        return new AccessToken(accessToken);

    }


    private Map<String, Object> retrieveUserInfo(URI userInfoEndpoint, AccessToken accessToken) throws OAuth2Exception {
        final HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(userInfoEndpoint)
                .header("Authorization", String.format("Bearer %s", accessToken.token()))
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = this.httpClient.send(httpRequest, BodyHandlers.ofString());
        } catch (Exception exception) {
            throw new OAuth2Exception(exception);
        }
        if (response == null) {
            throw new OAuth2Exception("httpResponse is null");
        }
        Gson gson = new Gson();
        TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {};
        Map<String, Object> json = gson.fromJson(response.body(), typeToken.getType());
        
        if (json == null) {
            throw new OAuth2Exception("json is null");
        }
        return json;
    }



    private AuthorizationCode retrieveAuthorizationCode() throws OAuth2Exception {
        URI authorizationCodeUri = this.getAuthorizationCodeUri();
        // if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
        //     try {
        //         Desktop.getDesktop().browse(authorizationCodeUricd );
        //     } catch (IOException exception) {
        //         throw new OAuth2Exception(exception);
        //     }
        // }
        String code = null;
        // try {
        //     code = this.futureFirstSSOResponse.get();
        // } catch (Exception exception) {
        //     exception.printStackTrace();
        //     throw new OAuth2Exception(exception);
        // }
        return new AuthorizationCode(code);
    }



    @Override
    public URI getAuthorizationCodeUri() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.configuration.authorizationCodeUri().toString());
        stringBuilder.append("?");
        stringBuilder.append("scope=");
        stringBuilder.append(String.format("%s", "profile"));
        stringBuilder.append("&");
        stringBuilder.append("access_type=online");
        stringBuilder.append("&");
        stringBuilder.append(String.format("redirect_uri=%s", this.configuration.authorizationCodeRedirectUri()));
        stringBuilder.append("&");
        stringBuilder.append(String.format("response_type=%s", "code"));
        stringBuilder.append("&");
        stringBuilder.append(String.format("client_id=%s", this.configuration.clientId()));
        return URI.create(stringBuilder.toString());
    }

    @Override
    public void signIn() throws OAuth2Exception {
        this.executorService.submit(() -> {
            Map<String, Object> userInfo = null;
            this.authorizationCode = this.retrieveAuthorizationCode();
            this.accessToken = this.retrieveAccessToken(
                    this.configuration.accessTokenUri(), 
                    this.configuration.clientId(), 
                    this.configuration.clientSecret(), 
                    super.authorizationCode, 
                    this.configuration.authorizationCodeRedirectUri(), 
                    "authorization_code");
            userInfo = this.retrieveUserInfo(this.configuration.userInfoUri(), this.accessToken);
            this.user = new OAuth2User(null, null, userInfo);
            return this.user;
        });

    }

    @Override
    public AuthorizationCode generateAuthorizationCode() throws OAuth2Exception {
        return this.retrieveAuthorizationCode();
    }
    @Override
    public AccessToken generateAccessToken() throws OAuth2Exception {
        return this.retrieveAccessToken(
            this.configuration.accessTokenUri(),
            this.configuration.clientId(), 
            this.configuration.clientSecret(), 
            super.authorizationCode, 
            this.configuration.accessTokenRedirectUri(), 
            "authorization_code");
    }

    @Override
    public void signOut() throws OAuth2Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signOut'");
    }

}

