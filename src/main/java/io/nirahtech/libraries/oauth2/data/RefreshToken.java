package io.nirahtech.libraries.oauth2.data;

public final record RefreshToken  (
    String value
) implements Token, Credentials {

}
