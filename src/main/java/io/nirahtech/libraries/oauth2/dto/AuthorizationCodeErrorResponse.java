package io.nirahtech.libraries.oauth2.dto;

import java.net.URI;
import java.util.Optional;

import io.nirahtech.libraries.oauth2.data.Error;

public record AuthorizationCodeErrorResponse(
    Error error,
    String state,
    Optional<String> errorDescription,
    Optional<URI> errorUri
) {
    
}
