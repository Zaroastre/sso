package io.nirahtech.libraries.oauth2.data;

/**
 * <p>An {@code Authorization Grant} is a credential representing the resource
 * owner's authorization (to access its protected resources) used by the
 * client to obtain an access token.</p>
 * <p>This specification defines four</p>
 * <ul>
 * grant types
 * <li>authorization code</li>
 * <li>implicit</li>
 * <li>resource owner password credentials</li>
 * <li>client credentials</li>
 * </ul>
 * <p>As well as an extensibility mechanism for defining additional types.</p>

 */
public enum AuthorizationGrantType {

    /**
     <p>The authorization code is obtained by using an authorization server
        as an intermediary between the client and resource owner.  Instead of
        requesting authorization directly from the resource owner, the client
        directs the resource owner to an authorization server (via its
        user-agent as defined in [RFC2616]), which in turn directs the
        resource owner back to the client with the authorization code.</p>

        <p>Before directing the resource owner back to the client with the
        authorization code, the authorization server authenticates the
        resource owner and obtains authorization.  Because the resource owner
        only authenticates with the authorization server, the resource
        owner's credentials are never shared with the client.</p>

        <p>The authorization code provides a few important security benefits,
        such as the ability to authenticate the client, as well as the
        transmission of the access token directly to the client without
        passing it through the resource owner's user-agent and potentially
        exposing it to others, including the resource owner.</p>
     */
    AUTHORIZATION_CODE,

    /**
     
   <p>The implicit grant is a simplified authorization code flow optimized
   for clients implemented in a browser using a scripting language such
   as JavaScript.  In the implicit flow, instead of issuing the client
   an authorization code, the client is issued an access token directly
   (as the result of the resource owner authorization).  The grant type
   is implicit, as no intermediate credentials (such as an authorization
   code) are issued (and later used to obtain an access token).</p>

   <p>When issuing an access token during the implicit grant flow, the
   authorization server does not authenticate the client.  In some
   cases, the client identity can be verified via the redirection URI
   used to deliver the access token to the client.  The access token may
   be exposed to the resource owner or other applications with access to
   the resource owner's user-agent.</p>

   <p>Implicit grants improve the responsiveness and efficiency of some
   clients (such as a client implemented as an in-browser application),
   since it reduces the number of round trips required to obtain an
   access token.  However, this convenience should be weighed against
   the security implications of using implicit grants, such as those
   described in Sections 10.3 and 10.16, especially when the
   authorization code grant type is available.</p>
     */
    IMPLICIT,

    /**
     <p>The resource owner password credentials (i.e., username and password)
   can be used directly as an authorization grant to obtain an access
   token.  The credentials should only be used when there is a high
   degree of trust between the resource owner and the client (e.g., the
   client is part of the device operating system or a highly privileged
   application), and when other authorization grant types are not
   available (such as an authorization code).</p>

   <p>Even though this grant type requires direct client access to the
   resource owner credentials, the resource owner credentials are used
   for a single request and are exchanged for an access token.  This
   grant type can eliminate the need for the client to store the
   resource owner credentials for future use, by exchanging the
   credentials with a long-lived access token or refresh token.</p>
     */
    RESOURCE_OWNER_PASSWORD_CREDENTIALS,

    /**
     * <p>The client credentials (or other forms of client authentication) can
   be used as an authorization grant when the authorization scope is
   limited to the protected resources under the control of the client,
   or to protected resources previously arranged with the authorization
   server.  Client credentials are used as an authorization grant
   typically when the client is acting on its own behalf (the client is
   also the resource owner) or is requesting access to protected
   resources based on an authorization previously arranged with the
   authorization server.</p>
     */
    CLIENT_CREDENTIALS,

    REFRESH_TOKEN
    
}