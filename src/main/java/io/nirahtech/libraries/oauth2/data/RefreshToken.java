package io.nirahtech.libraries.oauth2.data;

public final class RefreshToken implements Token, Credentials {
    private final String value;

    public RefreshToken(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
