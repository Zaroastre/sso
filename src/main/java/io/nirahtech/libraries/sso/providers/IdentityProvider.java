package io.nirahtech.libraries.sso.providers;

import java.net.URI;
import java.util.Optional;

import io.nirahtech.libraries.sso.data.OAuth2User;
import io.nirahtech.libraries.sso.exceptions.OAuth2Exception;

public interface IdentityProvider {
    void signIn() throws OAuth2Exception;
    boolean isAuthenticated();
    Optional<OAuth2User> getUserInfo();
    void signOut() throws OAuth2Exception;
    URI getAuthorizationCodeUri();
}

