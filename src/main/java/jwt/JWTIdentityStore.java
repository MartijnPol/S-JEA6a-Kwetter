package jwt;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

@ApplicationScoped
public class JWTIdentityStore implements IdentityStore {

    @Override
    public CredentialValidationResult validate(Credential credential) {

        if (credential instanceof JWTCredential) {
            JWTCredential jwtCredential = (JWTCredential) credential;
            return new CredentialValidationResult(jwtCredential.getCaller(), jwtCredential.getGroups());
        }

        return CredentialValidationResult.INVALID_RESULT;
    }

}