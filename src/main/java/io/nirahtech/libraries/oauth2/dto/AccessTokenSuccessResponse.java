package io.nirahtech.libraries.oauth2.dto;

import java.util.Map;

import io.nirahtech.libraries.oauth2.data.AccessToken;
import io.nirahtech.libraries.oauth2.data.RefreshToken;

public class AccessTokenSuccessResponse {
    private final AccessToken accessToken;
    private final String tokenType;
    private final int expiresIn;
    private final RefreshToken refreshToken;
    private final Map<String, Object> parameters;

    public AccessTokenSuccessResponse(AccessToken accessToken, String tokenType, int expiresIn,
                                      RefreshToken refreshToken, Map<String, Object> parameters) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.parameters = parameters;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public RefreshToken getRefreshToken() {
        return refreshToken;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }
}
