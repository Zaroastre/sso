package io.nirahtech.libraries.oauth2.data;

public final class AuthorizationCode {
    private final String code;

    public AuthorizationCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
