package io.nirahtech.libraries.oauth2.data;

import java.util.Map;

public final class OAuth2User {
    private final String email;
    private final String principal;
    private final Map<String, Object> userInfo;

    public OAuth2User(String email, String principal, Map<String, Object> userInfo) {
        this.email = email;
        this.principal = principal;
        this.userInfo = userInfo;
    }

    public String getEmail() {
        return email;
    }

    public String getPrincipal() {
        return principal;
    }

    public Map<String, Object> getUserInfo() {
        return userInfo;
    }
}
