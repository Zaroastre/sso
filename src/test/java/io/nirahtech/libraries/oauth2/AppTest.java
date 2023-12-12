package io.nirahtech.libraries.oauth2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import io.nirahtech.libraries.oauth2.configuration.OAuth2Configuration;
import io.nirahtech.libraries.oauth2.data.AccessToken;
import io.nirahtech.libraries.oauth2.data.AuthorizationCode;
import io.nirahtech.libraries.oauth2.data.ClientId;
import io.nirahtech.libraries.oauth2.data.ClientSecret;
import io.nirahtech.libraries.oauth2.data.Scope;
import io.nirahtech.libraries.oauth2.dto.AccessTokenRequest;
import io.nirahtech.libraries.oauth2.dto.AuthorizationCodeRequest;
import io.nirahtech.libraries.oauth2.remotes.AuthorizationServer;
import io.nirahtech.libraries.oauth2.remotes.ResourceOwner;

/**
 * Unit test for simple App.
 */
class AppTest {
    private static final ClientId CLIENT_ID = new ClientId(
            "723967256995-g04vhniaoe42t6vhup44ka0j1lrflh1r.apps.googleusercontent.com");

    /**
     * Rigorous Test :-)
     */
    @Test
    void authorizationCodeRequestTest() {
        // Auth Code
        final ResourceOwner resourceOwner = ResourceOwner
                .create(URI.create("https://accounts.google.com/o/oauth2/v2/auth"));
        final AuthorizationCodeRequest request = AuthorizationCodeRequest.builder(CLIENT_ID)
                .scopes(new Scope("profile"))
                .redirectUri(URI.create("http://localhost:8080/webapp-1.0-SNAPSHOT/login/oauth2/code/google"))
                .build();
        final Optional<AuthorizationCode> code = resourceOwner.submitRequestForAuthorizationCode(request);
        assertTrue(Objects.nonNull(code));
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    void accessTokenRequestTest() {
        this.authorizationCodeRequestTest();

        // Access Token
        final AuthorizationServer authorizationServer = AuthorizationServer
                .create(URI.create("https://oauth2.googleapis.com/token"));
        final AccessTokenRequest request = new AccessTokenRequest(
                CLIENT_ID,
                new ClientSecret("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"),
                new AuthorizationCode("4/0AfJohXn4a7kjYRM53SkC-lWEkf3xaktNAh0qJ2iFoj0y-ZYViJFP-tTMOZwba8T-I1tZsA"),
                Arrays.asList(new Scope("openid")),
                URI.create("http://localhost:8080/webapp-1.0-SNAPSHOT/login/oauth2/code/google"),
                "offline");
        final Optional<AccessToken> accessToken = authorizationServer.submitRequestForAccessToken(request);
        assertTrue(Objects.nonNull(accessToken));
    }

    @Test
    void oauth2FactoryTest() {
        OAuth2Configuration configuration = new OAuth2Configuration.Builder()
                .clientId("723967256995-g04vhniaoe42t6vhup44ka0j1lrflh1r.apps.googleusercontent.com")
                .projectId("ride4ever")
                .authorizationCodeUri(URI.create("https://accounts.google.com/o/oauth2/v2/auth"))
                .authorizationCodeRedirectUri(URI.create("http://localhost:8080/webapp-1.0-SNAPSHOT/login/oauth2/code/google"))
                .accessTokenUri(URI.create("https://oauth2.googleapis.com/token"))
                .userInfoUri(URI.create("https://openidconnect.googleapis.com/v1/userinfo"))
                .scopes(new Scope("openid"))
                .build();
        OAuth2 oAuth2 = OAuth2Factory.create(configuration);
        assertTrue(Objects.nonNull(oAuth2));
        oAuth2.generateAuthorizationCode();
    }
}
