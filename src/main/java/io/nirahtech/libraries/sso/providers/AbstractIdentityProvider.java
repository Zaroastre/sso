package io.nirahtech.libraries.sso.providers;

import java.net.http.HttpClient;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.nirahtech.libraries.sso.data.AccessToken;
import io.nirahtech.libraries.sso.data.AuthorizationCode;
import io.nirahtech.libraries.sso.data.OAuth2User;

public abstract class AbstractIdentityProvider implements IdentityProvider {
    protected final IdentityProviderConfiguration configuration; 
    protected OAuth2User user = null;

    protected AccessToken accessToken = null;
    protected AuthorizationCode authorizationCode = null;
    protected final HttpClient httpClient;

    protected final ExecutorService executorService;


    protected AbstractIdentityProvider(final IdentityProviderConfiguration configuration) {
        this.configuration = configuration;
        this.httpClient = HttpClient.newHttpClient();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public boolean isAuthenticated() {
        return this.user != null;
    }

    @Override
    public Optional<OAuth2User> getUserInfo() {
        return Optional.ofNullable(this.user);
    }

}

