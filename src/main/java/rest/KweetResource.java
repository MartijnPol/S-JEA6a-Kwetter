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
    @Path("findBySenderId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findKweetsBySenderId(@QueryParam("id") Long id) {
        return Response.ok(kweetService.findKweetsById(id)).build();
    }

    @GET
    @Path("findBySenderId/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countKweetsBySenderId(@QueryParam("id") Long id) {
        return Response.ok(kweetService.findKweetsById(id).size()).build();
    }
}