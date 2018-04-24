package rest;

import domain.Hashtag;
import service.HashtagService;

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
 * Created by Martijn van der Pol on 16-03-18
 **/

@Path("hashtags")
@Stateless
public class HashtagResource {

    @Inject
    private HashtagService hashtagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Hashtag> hashtags = hashtagService.getAll();
        return Response.ok(hashtagService.convertAllToJson(hashtags)).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Hashtag hashtag = hashtagService.findById(id);
        if (hashtag != null) {
            return Response.ok(hashtag.toJson()).header("Access-Control-Allow-Origin", "*").build();
        }
        return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("find/{subject}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findBySubject(@PathParam("subject") String subject) {
        Hashtag hashtag = hashtagService.findBySubject(subject);
        if (hashtag != null) {
            return Response.ok(hashtag.toJson()).header("Access-Control-Allow-Origin", "*").build();
        }
        return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
    }

}
