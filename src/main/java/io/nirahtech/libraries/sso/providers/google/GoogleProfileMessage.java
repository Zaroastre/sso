package io.nirahtech.libraries.sso.providers.google;

import java.util.Map;
import java.util.function.Function;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class GoogleProfileMessage {
    private static final Logger LOGGER = LogManager.getLogManager().getLogger("null");

    private enum ProfileProperty {
        SUB,
        GIVEN_NAME,
        NAME,
        FAMILY_NAME,
        EMAIL,
        PICTURE,
        TOKEN;
    }

    public static final boolean isGoogleProfile(final Map<String, Object> json) {
        boolean isValidProfile = true;
        for (ProfileProperty property : ProfileProperty.values()) {
            if (!json.containsKey(property.name().toLowerCase())) {
                LOGGER.info(String.format("Google property profile not found: %s", property.name().toLowerCase()));
                isValidProfile = false;
                break;
            }
        }
        LOGGER.info(String.format("Is Google profile: %s", isValidProfile));
        return isValidProfile;
    }
    
    public static final <T> T parse(final Map<String, Object> json, final Function<Map<String, Object>, T> function) {
        return function.apply(json);
    }

}