package rest;

import service.KweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("kweets")
@Stateless
public class KweetResource {

    @Inject
    private KweetService kweetService;

    @GET
    @Path("findByMessage")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllKweetsByMessage(@QueryParam("message") String message) {
        return Response.ok(kweetService.findAllKweetsByMessage(message)).build();
    }

    @GET
    @Path("kweet")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@QueryParam("id") Long id) {
        return Response.ok(kweetService.findById(id)).build();
    }

    @GET
    @Path("kweet/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@QueryParam("id") Long id) {
        kweetService.deleteById(id);
        return Response.ok().build();
    }
}