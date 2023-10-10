package io.nirahtech.libraries.sso.data;

import java.util.Map;

public record OAuth2User (
    String email,
    String principal,
    Map<String, Object> userInfo
) {
    
}
