package io.nirahtech.libraries.oauth2.data;

public final record AccessToken  (
    String value
) implements Token, Credentials {

}
