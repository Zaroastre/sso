package io.nirahtech.libraries.oauth2.dto;

import io.nirahtech.libraries.oauth2.data.AuthorizationCode;

public class AuthorizationCodeSuccessResponse {
    private final AuthorizationCode authorizationCode;
    private final String state;

    public AuthorizationCodeSuccessResponse(AuthorizationCode authorizationCode, String state) {
        this.authorizationCode = authorizationCode;
        this.state = state;
    }

    public AuthorizationCode getAuthorizationCode() {
        return authorizationCode;
    }

    public String getState() {
        return state;
    }
}
