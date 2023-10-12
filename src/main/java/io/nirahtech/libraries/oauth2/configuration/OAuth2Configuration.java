package io.nirahtech.libraries.oauth2.configuration;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import io.nirahtech.libraries.oauth2.data.ClientId;
import io.nirahtech.libraries.oauth2.data.ClientSecret;
import io.nirahtech.libraries.oauth2.data.Scope;

public record OAuth2Configuration (
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
    String accessType
) {

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
            this.scopes.addAll(Set.of(scopes));
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
