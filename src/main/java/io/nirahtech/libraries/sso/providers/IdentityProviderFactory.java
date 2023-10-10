package io.nirahtech.libraries.sso.providers;

import io.nirahtech.libraries.sso.providers.google.GoogleIdentityProvider;

public final class IdentityProviderFactory {
    private IdentityProviderFactory() {
    }

    public static final IdentityProvider google(final IdentityProviderConfiguration configuration) {
        return GoogleIdentityProvider.configure(configuration);
    }
}

