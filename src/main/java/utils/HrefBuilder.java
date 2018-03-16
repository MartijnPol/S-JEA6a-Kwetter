package utils;

import domain.Kweet;
import domain.UserProfile;

/**
 * Created by Martijn van der Pol on 16-03-18
 **/
public class HrefBuilder {

    public static String build(UserProfile userProfile) {
        return "/profiles/" + userProfile.getId();
    }

    public static String build(Kweet kweet) {
        return "/kweets/" + kweet.getId();
    }

}
