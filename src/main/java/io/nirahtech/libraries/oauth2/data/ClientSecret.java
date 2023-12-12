package io.nirahtech.libraries.oauth2.data;

public final class ClientSecret {
    private final String value;

    public ClientSecret(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
