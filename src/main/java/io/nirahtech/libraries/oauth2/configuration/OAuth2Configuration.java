package io.nirahtech.libraries.oauth2.configuration;

import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.nirahtech.libraries.oauth2.data.ClientId;
import io.nirahtech.libraries.oauth2.data.ClientSecret;
import io.nirahtech.libraries.oauth2.data.Scope;

public class OAuth2Configuration {
    private final String projectId;
    private final ClientId clientId;
    private final ClientSecret clientSecret;
    private final URI authorizationCodeUri;
    private final URI authorizationCodeRedirectUri;
    private final URI accessTokenUri;
    private final URI accessTokenRedirectUri;
    private final URI userInfoUri;
    private final URI userInfoRedirectUri;
    private final Set<Scope> scopes;
    private final String accessType;

    public OAuth2Configuration(
            String projectId,
            ClientId clientId,
            ClientSecret clientSecret,
            URI authorizationCodeUri,
            URI authorizationCodeRedirectUri,
            URI accessTokenUri,
            URI accessTokenRedirectUri,
            URI userInfoUri,
            URI userInfoRedirectUri,
            Set<Scope> scopes,
            String accessType) {
        this.projectId = projectId;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authorizationCodeUri = authorizationCodeUri;
        this.authorizationCodeRedirectUri = authorizationCodeRedirectUri;
        this.accessTokenUri = accessTokenUri;
        this.accessTokenRedirectUri = accessTokenRedirectUri;
        this.userInfoUri = userInfoUri;
        this.userInfoRedirectUri = userInfoRedirectUri;
        this.scopes = scopes;
        this.accessType = accessType;
    }

    public String getProjectId() {
        return projectId;
    }

    public ClientId getClientId() {
        return clientId;
    }

    public ClientSecret getClientSecret() {
        return clientSecret;
    }

    public URI getAuthorizationCodeUri() {
        return authorizationCodeUri;
    }

    public URI getAuthorizationCodeRedirectUri() {
        return authorizationCodeRedirectUri;
    }

    public URI getAccessTokenUri() {
        return accessTokenUri;
    }

    public URI getAccessTokenRedirectUri() {
        return accessTokenRedirectUri;
    }

    public URI getUserInfoUri() {
        return userInfoUri;
    }

    public URI getUserInfoRedirectUri() {
        return userInfoRedirectUri;
    }

    public Set<Scope> getScopes() {
        return scopes;
    }

    public String getAccessType() {
        return accessType;
    }
    public static final class Builder {
        private String projectId;
        private ClientId clientId;
        private ClientSecret clientSecret;
        private URI authorizationCodeUri; 
        private URI accessTokenUri; 
        private URI userInfoUri; 
        private URI authorizationCodeRedirectUri; 
        private URI accessTokenRedirectUri; 
        private URI userInfoRedirectUri;
        private String accessType;
        private Set<Scope> scopes = new HashSet<>(); 

        public Builder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder clientId(ClientId clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder clientSecret(ClientSecret clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }
        
        public Builder clientId(String clientId) {
            this.clientId = new ClientId(clientId);
            return this;
        }
        
        public Builder accessType(String accessType) {
            this.accessType = accessType;
            return this;
        }

        public Builder clientSecret(String clientSecret) {
            this.clientSecret = new ClientSecret(clientSecret);
            return this;
        }

        public Builder authorizationCodeUri(URI authorizationCodeUri) {
            this.authorizationCodeUri = authorizationCodeUri;
            return this;
        }

        public Builder accessTokenUri(URI accessTokenUri) {
            this.accessTokenUri = accessTokenUri;
            return this;
        }

        public Builder userInfoUri(URI userInfoUri) {
            this.userInfoUri = userInfoUri;
            return this;
        }

        public Builder userInfoRedirectUri(URI userInfoRedirectUri) {
            this.userInfoRedirectUri = userInfoRedirectUri;
            return this;
        }
        public Builder accessTokenRedirectUri(URI accessTokenRedirectUri) {
            this.accessTokenRedirectUri = accessTokenRedirectUri;
            return this;
        }
        public Builder authorizationCodeRedirectUri(URI authorizationCodeRedirectUri) {
            this.authorizationCodeRedirectUri = authorizationCodeRedirectUri;
            return this;
        }
        public Builder scopes(Scope... scopes) {
            this.scopes.addAll(Arrays.asList(scopes));
            return this;
        }

        public OAuth2Configuration build() {
            return new OAuth2Configuration(
                projectId, 
                clientId, 
                clientSecret,
                authorizationCodeUri,
                authorizationCodeRedirectUri,
                accessTokenUri,
                accessTokenRedirectUri,
                userInfoUri,
                userInfoRedirectUri,
                scopes,
                accessType);
        }
    }
}
