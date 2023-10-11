package io.nirahtech.libraries.oauth2.data;

public sealed interface Token permits AccessToken, RefreshToken {
    String value();
}
