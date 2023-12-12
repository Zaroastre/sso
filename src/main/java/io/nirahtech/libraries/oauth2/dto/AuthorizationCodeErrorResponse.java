package io.nirahtech.libraries.oauth2.dto;

import java.net.URI;
import java.util.Optional;

import io.nirahtech.libraries.oauth2.data.Error;

public class AuthorizationCodeErrorResponse {
    private final Error error;
    private final String state;
    private final Optional<String> errorDescription;
    private final Optional<URI> errorUri;

    public AuthorizationCodeErrorResponse(Error error, String state, Optional<String> errorDescription, Optional<URI> errorUri) {
        this.error = error;
        this.state = state;
        this.errorDescription = errorDescription;
        this.errorUri = errorUri;
    }

    public Error getError() {
        return error;
    }

    public String getState() {
        return state;
    }

    public Optional<String> getErrorDescription() {
        return errorDescription;
    }

    public Optional<URI> getErrorUri() {
        return errorUri;
    }
}
