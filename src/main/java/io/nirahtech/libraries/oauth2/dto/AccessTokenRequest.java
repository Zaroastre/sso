package io.nirahtech.libraries.oauth2.dto;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import io.nirahtech.libraries.oauth2.data.AuthorizationCode;
import io.nirahtech.libraries.oauth2.data.AuthorizationGrantType;
import io.nirahtech.libraries.oauth2.data.ClientId;
import io.nirahtech.libraries.oauth2.data.ClientSecret;
import io.nirahtech.libraries.oauth2.data.ResponseType;
import io.nirahtech.libraries.oauth2.data.Scope;

public class AccessTokenRequest {

    private final String contentType = "application/x-www-form-urlencoded";
    private final AuthorizationGrantType grantType = AuthorizationGrantType.AUTHORIZATION_CODE;
    private final ResponseType responseType = ResponseType.TOKEN;

    private final AuthorizationCode authorizationCode;
    private final URI redirectUri;
    private final ClientId clientId;
    private final ClientSecret clientSecret;
    private final Set<Scope> scopes = new HashSet<>();
    private final String accessType;

    
    public AccessTokenRequest(
        ClientId clientId,
        ClientSecret clientSecret,
        AuthorizationCode authorizationCode,
        Collection<Scope> scopes,
        URI redirectUri,
        String accessType
    ) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authorizationCode = authorizationCode;
        this.redirectUri = redirectUri;
        this.scopes.addAll(scopes);
        this.accessType = accessType;
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
    public Set<Scope> getScopes() {
        return scopes;
    }
    public String getAccessType() {
        return accessType;
    }
    public ClientSecret getClientSecret() {
        return clientSecret;
    }

    public static final Builder builder(ClientId clientId, ClientSecret clientSecret, AuthorizationCode authorizationCode) {
        return new Builder(clientId, clientSecret, authorizationCode);
    }

    public static class Builder {
        private final ClientId clientId;
        private final ClientSecret clientSecret;
        private final AuthorizationCode authorizationCode;
        private URI redirectUri;
        private String accessType;
        private final Set<Scope> scopes = new HashSet<>();

        private Builder(ClientId clientId, ClientSecret clientSecret, AuthorizationCode authorizationCode) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            this.authorizationCode = authorizationCode;
        }

        public Builder redirectUri(final URI redirectUri) {
            this.redirectUri = redirectUri;
            return this;
        }
        public Builder accessType(final String accessType) {
            this.accessType = accessType;
            return this;
        }
        public Builder scopes(final Scope... scopes) {
            this.scopes.addAll(Arrays.asList(scopes));
            return this;
        }
        public final AccessTokenRequest build() {
            return new AccessTokenRequest(clientId, clientSecret, authorizationCode, scopes, redirectUri, accessType);
        }
    }

    public String asURIParameters() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?");
        stringBuilder.append(String.format("client_id=%s", this.getClientId().getValue()));
        stringBuilder.append("&");
        stringBuilder.append(String.format("client_secret=%s", this.getClientSecret().getValue()));
        stringBuilder.append("&");
        stringBuilder.append(String.format("code=%s", this.getAuthorizationCode().getCode()));
        stringBuilder.append("&");
        if (!this.getScopes().isEmpty()) {
            stringBuilder.append("scope=");
            stringBuilder.append(String.format("%s", this.getScopes().stream().map(scope -> scope.getValue()).collect(Collectors.joining("%20"))));
            stringBuilder.append("&");
        }
        // stringBuilder.append(String.format("response_type=%s", this.getResponseType().name().toLowerCase()));
        // stringBuilder.append("&");
        stringBuilder.append(String.format("redirect_uri=%s", this.getRedirectUri().toString()));
        stringBuilder.append("&");
        stringBuilder.append(String.format("grant_type=%s", this.getGrantType().name().toLowerCase()));
        if (Objects.nonNull(this.accessType)) {
        stringBuilder.append("&");
        stringBuilder.append(String.format("access_type=%s", accessType.toLowerCase()));
        }
        return stringBuilder.toString();
    }
}
