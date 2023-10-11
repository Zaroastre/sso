package io.nirahtech.libraries.oauth2.dto;

import java.net.URI;
import java.util.Optional;

import io.nirahtech.libraries.oauth2.data.AccessToken;

public class UserInfoRequest {
    private final AccessToken accessToken;
    private final URI redirectUri;

    public UserInfoRequest(
        AccessToken accessToken,
        URI redirectUri
    ) {
        this.accessToken = accessToken;
        this.redirectUri = redirectUri;
    } 
    public AccessToken getAccessToken() {
        return accessToken;
    }
    public Optional<URI> getRedirectUri() {
        return Optional.ofNullable(redirectUri);
    }
    public static final Builder builder(AccessToken accessToken) {
        return new Builder(accessToken);
    }

    public static class Builder {
        private final AccessToken accessToken;
        private URI redirectUri = null;

        private Builder(AccessToken accessToken) {
            this.accessToken = accessToken;
        }

        public Builder redirectUri(final URI redirectUri) {
            this.redirectUri = redirectUri;
            return this;
        }
        public final UserInfoRequest build() {
            return new UserInfoRequest(accessToken, redirectUri);
        }
    }
}
