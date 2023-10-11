package io.nirahtech.libraries.oauth2.dto;

import io.nirahtech.libraries.oauth2.data.AuthorizationCode;

public record AuthorizationCodeSuccessResponse (
    AuthorizationCode authorizationCode,
    String state
) {
    
}
