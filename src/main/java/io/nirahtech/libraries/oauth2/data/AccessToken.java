package io.nirahtech.libraries.oauth2.data;

import java.util.Set;

public final record AccessToken  (
    String value,
    int expiresIn,
    Set<Scope> scopes,
    String tokenType,
    String idToken
) implements Token, Credentials {

}
