package rest;

import domain.UserAccount;
import jwt.JWTService;
import service.UserAccountService;
import service.UserGroupService;
import utils.EncryptionHelper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@Stateless
@Path("authentication")
public class AuthenticationResource {

    @Inject
    private JWTService jwtService;

    @Inject
    private UserAccountService userAccountService;

    @Inject
    private UserGroupService userGroupService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticate(JsonObject credentials) {

        String username = credentials.getString("username");
        String password = credentials.getString("password");

        UserAccount foundUser = userAccountService.findByCredentials(username, EncryptionHelper.encryptPassword(password));

        if (foundUser == null) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }

        String token = this.jwtService.generateToken(username, Arrays.asList("ADMIN", "MEMBER"));
        return Response.ok(token).header(AUTHORIZATION, "Bearer " + token).build();
    }
}