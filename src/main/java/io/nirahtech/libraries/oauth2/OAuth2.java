package io.nirahtech.libraries.oauth2;

import java.net.URI;
import java.util.Map;

import io.nirahtech.libraries.oauth2.data.AccessToken;
import io.nirahtech.libraries.oauth2.data.AuthorizationCode;

public interface OAuth2 {
    AuthorizationCode generateAuthorizationCode();
    AccessToken generateAccessToken(AuthorizationCode authorizationCode);
    Map<String, String> retrieveUserInfo(AccessToken accessToken);

    URI generateFullAuthorizationEndpoint();
    URI generateFullAccessTokenEndpoint();
}
