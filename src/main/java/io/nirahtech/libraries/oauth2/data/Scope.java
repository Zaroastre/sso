package io.nirahtech.libraries.oauth2.data;

public final class Scope {
    private final String value;

    public Scope(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
