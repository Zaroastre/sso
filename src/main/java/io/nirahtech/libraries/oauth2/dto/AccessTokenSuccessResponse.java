package io.nirahtech.libraries.oauth2.dto;

import java.util.Map;

import io.nirahtech.libraries.oauth2.data.AccessToken;
import io.nirahtech.libraries.oauth2.data.RefreshToken;

public record AccessTokenSuccessResponse (
    AccessToken accessToken,
    String tokenType,
    int expiresIn,
    RefreshToken refreshToken,
    Map<String, Object> parameters
) {
    
}
