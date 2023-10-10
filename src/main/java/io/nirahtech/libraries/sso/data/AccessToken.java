package io.nirahtech.libraries.sso.data;

public record AccessToken  (
    String value
) implements Token, Credentials {

}
