package io.nirahtech;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.nirahtech.libraries.sso.exceptions.OAuth2Exception;
import io.nirahtech.libraries.sso.providers.IdentityProvider;
import io.nirahtech.libraries.sso.providers.IdentityProviderConfiguration;
import io.nirahtech.libraries.sso.providers.IdentityProviderFactory;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        final IdentityProviderConfiguration configuration = new IdentityProviderConfiguration.Builder()
                .build();
        final IdentityProvider idp = IdentityProviderFactory.google(configuration);
        try {
            idp.signIn();
        } catch (OAuth2Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertTrue( true );
    }
}
