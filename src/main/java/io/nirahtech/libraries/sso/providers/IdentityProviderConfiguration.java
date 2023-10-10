package io.nirahtech.libraries.sso.providers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public record IdentityProviderConfiguration (
    String projectId,
    String clientId,
    String clientSecret,
    URI authorizationCodeUri,
    URI authorizationCodeRedirectUri,
    URI accessTokenUri,
    URI accessTokenRedirectUri,
    URI userInfoUri,
    URI userInfoRedirectUri
) {

    public static final class Builder {
        private String projectId;
        private String clientId;
        private String clientSecret;
        private URI authorizationCodeUri; 
        private URI accessTokenUri; 
        private URI userInfoUri; 
        private URI authorizationCodeRedirectUri; 
        private URI accessTokenRedirectUri; 
        private URI userInfoRedirectUri; 
        private List<URI> redirectUris = new ArrayList<>();

        public Builder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
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
        public Builder redirectUri(List<URI> redirectUri) {
            this.redirectUris.addAll(redirectUri);
            return this;
        }

        public IdentityProviderConfiguration build() {
            return new IdentityProviderConfiguration(
                projectId, 
                clientId, 
                clientSecret,
                authorizationCodeUri,
                authorizationCodeRedirectUri,
                accessTokenUri,
                accessTokenRedirectUri,
                userInfoUri,
                userInfoRedirectUri);
        }
    }
}
