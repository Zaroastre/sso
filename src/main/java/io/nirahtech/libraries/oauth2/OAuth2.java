package io.nirahtech.libraries.oauth2;

import java.util.Map;

import io.nirahtech.libraries.oauth2.data.AccessToken;
import io.nirahtech.libraries.oauth2.data.AuthorizationCode;
import io.nirahtech.libraries.oauth2.data.Scope;

public sealed interface OAuth2 permits OAuth2Impl {
    AuthorizationCode generateAuthorizationCode(Scope... scopes);
    AccessToken generateAccessToken(AuthorizationCode authorizationCode);
    Map<String, Object> retrieveUserInfo(AccessToken accessToken);
}
