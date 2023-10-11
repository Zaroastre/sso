package io.nirahtech.libraries.oauth2.dto;

import java.net.URI;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import io.nirahtech.libraries.oauth2.data.AuthorizationCode;
import io.nirahtech.libraries.oauth2.data.AuthorizationGrantType;
import io.nirahtech.libraries.oauth2.data.ClientId;
import io.nirahtech.libraries.oauth2.data.ResponseType;
import io.nirahtech.libraries.oauth2.data.Scope;

public class AccessTokenRequest {

    private final String contentType = "application/x-www-form-urlencoded";
    private final AuthorizationGrantType grantType = AuthorizationGrantType.AUTHORIZATION_CODE;
    private final ResponseType responseType = ResponseType.TOKEN;

    private final AuthorizationCode authorizationCode;
    private final URI redirectUri;
    private final ClientId clientId;

    
    public AccessTokenRequest(
        ClientId clientId,
        AuthorizationCode authorizationCode,
        URI redirectUri
    ) {
        this.clientId = clientId;
        this.authorizationCode = authorizationCode;
        this.redirectUri = redirectUri;
    }

    public ResponseType getResponseType() {
        return responseType;
    }
    public ClientId getClientId() {
        return clientId;
    }
    public URI getRedirectUri() {
        return redirectUri;
    }
    public String getContentType() {
        return contentType;
    }
    public AuthorizationCode getAuthorizationCode() {
        return authorizationCode;
    }
    public AuthorizationGrantType getGrantType() {
        return grantType;
    }

    public static final Builder builder(ClientId clientId, AuthorizationCode authorizationCode) {
        return new Builder(clientId, authorizationCode);
    }

    public static class Builder {
        private final ClientId clientId;
        private final AuthorizationCode authorizationCode;
        private URI redirectUri;

        private Builder(ClientId clientId, AuthorizationCode authorizationCode) {
            this.clientId = clientId;
            this.authorizationCode = authorizationCode;
        }

        public Builder redirectUri(final URI redirectUri) {
            this.redirectUri = redirectUri;
            return this;
        }
        public final AccessTokenRequest build() {
            return new AccessTokenRequest(clientId, authorizationCode, redirectUri);
        }
    }
}
