package io.nirahtech.libraries.oauth2;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import io.nirahtech.libraries.oauth2.configuration.OAuth2Configuration;
import io.nirahtech.libraries.oauth2.data.AccessToken;
import io.nirahtech.libraries.oauth2.data.AuthorizationCode;
import io.nirahtech.libraries.oauth2.data.Scope;
import io.nirahtech.libraries.oauth2.dto.AccessTokenRequest;
import io.nirahtech.libraries.oauth2.dto.AuthorizationCodeRequest;
import io.nirahtech.libraries.oauth2.dto.UserInfoRequest;
import io.nirahtech.libraries.oauth2.remotes.AuthorizationServer;
import io.nirahtech.libraries.oauth2.remotes.ResourceOwner;
import io.nirahtech.libraries.oauth2.remotes.ResourceServer;

final class OAuth2Impl implements OAuth2 {
    private final OAuth2Configuration configuration;

    private final ResourceOwner resourceOwner;
    private final AuthorizationServer authorizationServer;
    private final ResourceServer resourceServer;

    private URI fullAuthorizationUri = null; 
    private URI fullAccessTokenUri = null; 

    OAuth2Impl(final OAuth2Configuration configuration) {
        this.configuration = configuration;
        this.resourceOwner = ResourceOwner.create(configuration.getAuthorizationCodeUri());
        this.authorizationServer = AuthorizationServer.create(configuration.getAccessTokenUri());
        this.resourceServer = ResourceServer.create(configuration.getUserInfoUri());
    }

    @Override
    public final AuthorizationCode generateAuthorizationCode() {
        final AuthorizationCodeRequest request = AuthorizationCodeRequest.builder(this.configuration.getClientId())
                .redirectUri(configuration.getAuthorizationCodeRedirectUri())
                .scopes(configuration.getScopes().toArray(new Scope[this.configuration.getScopes().size()]))
                .build();
        this.fullAuthorizationUri = URI.create(this.resourceOwner.getUri().toString() + request.asURIParameters());
        final Optional<AuthorizationCode> code = this.resourceOwner.submitRequestForAuthorizationCode(request);
        return code.orElse(null);
    }
    
    @Override
    public final AccessToken generateAccessToken(final AuthorizationCode authorizationCode) {
        final AccessTokenRequest request = new AccessTokenRequest(this.configuration.getClientId(), this.configuration.getClientSecret(), authorizationCode, this.configuration.getScopes(), this.configuration.getAccessTokenRedirectUri(), this.configuration.getAccessType());
        this.fullAccessTokenUri = URI.create(this.authorizationServer.getUri().toString() + request.asURIParameters());
        final Optional<AccessToken> token = this.authorizationServer.submitRequestForAccessToken(request);
        return token.orElse(null);
    }

    @Override
    public final Map<String, String> retrieveUserInfo(final AccessToken accessToken) {
        final UserInfoRequest request = new UserInfoRequest(accessToken, this.configuration.getUserInfoUri());
        final Optional<Map<String, String>> userInfo = this.resourceServer.submitRequestForUserInfo(request);
        return userInfo.orElse(new HashMap<>());
    }

    @Override
    public URI generateFullAuthorizationEndpoint() {
        return this.fullAuthorizationUri;
    }

    @Override
    public URI generateFullAccessTokenEndpoint() {
        return this.fullAccessTokenUri;
    }
}
