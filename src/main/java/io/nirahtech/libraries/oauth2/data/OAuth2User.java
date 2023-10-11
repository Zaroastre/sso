package io.nirahtech.libraries.oauth2.data;

import java.util.Map;

public final record OAuth2User (
    String email,
    String principal,
    Map<String, Object> userInfo
) {
    
}
