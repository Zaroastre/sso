package io.nirahtech.libraries.sso.data;

import java.net.URI;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AuthorizationRequest {

    private final ResponseType responseType;
    private final ClientId clientId;
    private final URI redirectUri;
    private final Set<Scope> scopes = new HashSet<>();
    
    private final String state;
    
    public AuthorizationRequest(
        ResponseType responseType,
        ClientId clientId,
        URI redirectUri,
        Set<Scope> scopes,
        String state
    ) {
        this.responseType = responseType;
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.scopes.addAll(scopes);
        this.state = state;
    }
    public AuthorizationRequest(
        ResponseType responseType,
        ClientId clientId
    ) {

        this(responseType, clientId, null, Set.of(), null);
    }
    public AuthorizationRequest(
        ResponseType responseType,
        ClientId clientId,

        URI redirectUri,
        Set<Scope> scopes
        
    ) {

        this(responseType, clientId, redirectUri, scopes, null);
    }
    public AuthorizationRequest(
        ResponseType responseType,
        ClientId clientId,

        URI redirectUri
    ) {

        this(responseType, clientId, redirectUri, Set.of(), null);
    }
    public AuthorizationRequest(
        ResponseType responseType,
        ClientId clientId,

        Set<Scope> scopes
    ) {
        this(responseType, clientId, null, scopes, null);
    }
    public AuthorizationRequest(
        ResponseType responseType,
        ClientId clientId,
        
        String state
    ) {
        this(responseType, clientId, null, Set.of(), state);
    }

    public AuthorizationRequest(
        ResponseType responseType,
        ClientId clientId,

        URI redirectUri,
        String state
    ) {
        this(responseType, clientId, redirectUri, Set.of(), state);

    }
    public AuthorizationRequest(
        ResponseType responseType,
        ClientId clientId,

        Set<Scope> scopes,
        String state
    ) {
        this(responseType, clientId, null, scopes, state);
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

    public static class Builder {
        private ResponseType responseType;
        private ClientId clientId;
        private URI redirectUri;
        private Set<Scope> scopes = new HashSet<>();
        private String state;

        public final AuthorizationRequest build() {
            return new AuthorizationRequest(responseType, clientId, redirectUri, scopes, state);
        }
    }
}
