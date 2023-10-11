package io.nirahtech.libraries.oauth2;

import io.nirahtech.libraries.oauth2.configuration.OAuth2Configuration;

public final class OAuth2Factory {

    private OAuth2Factory() {}
    
    public static final OAuth2 create(final OAuth2Configuration configuration) {
        return new OAuth2Impl(configuration);
    }
}
