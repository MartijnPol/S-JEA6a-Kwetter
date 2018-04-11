package rest;

import domain.Kweet;
import service.KweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("kweets")
@Stateless
public class KweetResource {

    @Inject
    private KweetService kweetService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllKweets() {
        List<Kweet> kweetList = kweetService.getAll();
        return Response.ok(kweetService.convertAllToJson(kweetList)).build();
    }

    @GET
    @Path("find/{message}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllKweetsByMessage(@PathParam("message") String message) {
        List<Kweet> kweetList = kweetService.findAllKweetsByMessage(message);
        if (kweetList != null) {
            return Response.ok(kweetService.convertAllToJson(kweetList)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Kweet kweet = kweetService.findById(id);
        if (kweet != null) {
            return Response.ok(kweet.toJson()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id) {
        kweetService.deleteById(id);
        return Response.ok().build();
    }
}