package io.nirahtech.libraries.oauth2.data;

import java.util.Set;

public final class AccessToken implements Token, Credentials {
    private final String value;
    private final int expiresIn;
    private final Set<Scope> scopes;
    private final String tokenType;
    private final String idToken;

    public AccessToken(String value, int expiresIn, Set<Scope> scopes, String tokenType, String idToken) {
        this.value = value;
        this.expiresIn = expiresIn;
        this.scopes = scopes;
        this.tokenType = tokenType;
        this.idToken = idToken;
    }

    public String getValue() {
        return value;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public Set<Scope> getScopes() {
        return scopes;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getIdToken() {
        return idToken;
    }
}
