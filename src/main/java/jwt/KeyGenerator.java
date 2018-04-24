package jwt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class KeyGenerator {

    private static final String SECRET_KEY = "secret";
    private static final String SHA_256 = "SHA-256";

    String generateKey() {
        return getHashText(SECRET_KEY);
    }

    private String getHashText(final String text) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_256);
            byte[] hash = messageDigest.digest(text.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

}
