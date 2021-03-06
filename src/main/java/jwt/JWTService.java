package jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Stateless
public class JWTService {

    private static final Instant CURRENT_TIME = Instant.now();
    private static final Instant EXPIRED_TIME = CURRENT_TIME.plus(30, ChronoUnit.MINUTES);

    @Inject
    private KeyGenerator keyGenerator;

    protected static boolean isTokenTimeValid(final Date creation, final Date expiration) {
        Date now = new Date();
        return creation.before(now) && now.before(expiration);
    }

    public String generateToken(final String username, final List<String> groupNames) {
        try {
            String secretKey = this.keyGenerator.generateKey();

            JWSSigner signer = new MACSigner(secretKey);

            JWTClaimsSet.Builder claimSet = new JWTClaimsSet.Builder();

            claimSet.issuer("Kwetter");
            claimSet.subject(username);
            claimSet.audience("Kwetter");
            claimSet.issueTime(Date.from(CURRENT_TIME));
            claimSet.expirationTime(Date.from(EXPIRED_TIME));

            JSONArray groupValues = new JSONArray();
            groupValues.addAll(groupNames);

            Map<String, Object> groups = new HashMap<>();
            groups.put("groups", groupValues);

            claimSet.claim("realm_access", groups);

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimSet.build());
            signedJWT.sign(signer);
            return signedJWT.serialize();

        } catch (JOSEException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public JWTCredential getCredential(String token) {
        try {
            String secretKey = keyGenerator.generateKey();

            SignedJWT signedJWT = SignedJWT.parse(token);

            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

            JWSVerifier verifier = new MACVerifier(secretKey);

            if (!signedJWT.verify(verifier)) {
                throw new RuntimeException("Token is not verified.");
            }

            if (!isTokenTimeValid(claimsSet.getIssueTime(), claimsSet.getExpirationTime())) {
                throw new RuntimeException("Token is expired.");
            }

            JSONObject realmAccess = (JSONObject) claimsSet.getClaim("realm_access");
            JSONArray groupArray = (JSONArray) realmAccess.get("groups");

            Set<String> groups = new HashSet<>();
            groupArray.forEach(g -> groups.add(g.toString()));

            return new JWTCredential(claimsSet.getSubject(), groups);

        } catch (ParseException | JOSEException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

}
