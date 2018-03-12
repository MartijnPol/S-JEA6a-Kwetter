package rest;

import domain.UserProfile;
import service.UserProfileService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Martijn van der Pol on 07-03-18
 **/

@Path("profiles")
@Stateless
public class UserProfileResource {

    @Inject
    private UserProfileService userProfileService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUserProfiles() {
        List<UserProfile> userProfiles = userProfileService.getAll();
        return Response.ok(userProfiles).build();
    }

    @GET
    @Path("profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountById(@QueryParam("id") Long id) {
        return Response.ok(userProfileService.findById(id)).build();
    }

    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countAllAccounts() {
        return Response.ok(userProfileService.countAll()).build();
    }

}
