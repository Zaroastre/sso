package io.nirahtech.libraries.oauth2.dto;

import java.net.URI;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import io.nirahtech.libraries.oauth2.data.ClientId;
import io.nirahtech.libraries.oauth2.data.ResponseType;
import io.nirahtech.libraries.oauth2.data.Scope;

public class AuthorizationCodeRequest {

    private final ResponseType responseType = ResponseType.CODE;
    private final String contentType = "application/x-www-form-urlencoded";
    
    private final ClientId clientId;
    private final URI redirectUri;
    private final Set<Scope> scopes = new HashSet<>();
    
    private final String state;
    
    public AuthorizationCodeRequest(
        ClientId clientId,
        URI redirectUri,
        Set<Scope> scopes,
        String state
    ) {
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.scopes.addAll(scopes);
        this.state = state;
    }
   
    public ResponseType getResponseType() {
        return responseType;
    }
    public ClientId getClientId() {
        return clientId;
    }
    public Optional<URI> getRedirectUri() {
        return Optional.ofNullable(redirectUri);
    }
    public Set<Scope> getScopes() {
        return scopes;
    }
    public Optional<String> getState() {
        return Optional.ofNullable(state);
    }

    public String getContentType() {
        return contentType;
    }

    public static final Builder builder(ClientId clientId) {
        return new Builder(clientId);
    }

    public static class Builder {
        private final ClientId clientId;
        private URI redirectUri;
        private Set<Scope> scopes = new HashSet<>();
        private String state;

        private Builder(ClientId clientId) {
            this.clientId = clientId;
        }

        public Builder redirectUri(final URI redirectUri) {
            this.redirectUri = redirectUri;
            return this;
        }
        public Builder scopes(final Scope... scopes) {
            this.scopes.addAll(Set.of(scopes));
            return this;
        }
        public Builder state(final String state) {
            this.state = state;
            return this;
        }

        public final AuthorizationCodeRequest build() {
            return new AuthorizationCodeRequest(clientId, redirectUri, scopes, state);
        }
    }
}
