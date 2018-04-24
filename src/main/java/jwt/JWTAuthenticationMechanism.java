package jwt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@ApplicationScoped
public class JWTAuthenticationMechanism implements HttpAuthenticationMechanism {

    private static final String BEARER = "Bearer ";

    @Inject
    private IdentityStore identityStore;

    @Inject
    private JWTService jwtService;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest req, HttpServletResponse res, HttpMessageContext context) {

        String authorizationHeader = req.getHeader(AUTHORIZATION);
        Credential credential = null;

        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            String token = authorizationHeader.substring(BEARER.length());
            credential = this.jwtService.getCredential(token);
        }

        if (credential != null) {
            System.out.println("Authorized");
            return context.notifyContainerAboutLogin(this.identityStore.validate(credential));
        } else {
            System.out.println("Unauthorized");
            return context.doNothing();
        }
    }

}