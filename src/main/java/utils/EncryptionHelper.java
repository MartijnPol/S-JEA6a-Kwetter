package utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Martijn van der Pol on 24-03-18
 **/
public final class EncryptionHelper {

    /**
     * Function to encrypt a password with SHA-256
     *
     * @param password is the password that needs to be encrypted
     * @return the encrypted password hash
     */
    public static String encryptPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }

}
