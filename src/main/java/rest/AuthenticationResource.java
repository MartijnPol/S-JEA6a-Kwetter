package rest;

import domain.UserAccount;
import domain.UserGroup;
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
import java.util.ArrayList;
import java.util.List;

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

        // User not found? Throw an unauthorized status
        if (foundUser == null) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        // Get UserGroups the User belongs to
        List<UserGroup> userGroupList = userGroupService.findByUsername(username);
        ArrayList<String> groupNames = new ArrayList<>();

        for (UserGroup userGroup : userGroupList) {
            groupNames.add(userGroup.getGroupName());
        }

        // Generate token and return it in the authorization header
        String token = this.jwtService.generateToken(username, groupNames);
        return Response.ok(token).header(AUTHORIZATION, "Bearer " + token).build();
    }
}