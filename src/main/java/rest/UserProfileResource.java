package rest;

import domain.UserProfile;
import service.UserProfileService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
        return Response.ok((userProfileService.convertAllToJson(userProfiles))).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserProfileById(@PathParam("id") Long id) {
        UserProfile userProfile = userProfileService.findById(id);
        if (userProfile != null) {
            return Response.ok(userProfile.toJson()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("find/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserProfileByUsername(@PathParam("username") String username) {
        UserProfile userProfile = userProfileService.findByUsername(username);
        if (userProfile != null) {
            return Response.ok(userProfile.toJson()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countAllUserProfiles() {
        return Response.ok(userProfileService.countAll()).build();
    }

}
