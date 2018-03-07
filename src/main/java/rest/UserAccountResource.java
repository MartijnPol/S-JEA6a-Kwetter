package rest;

import domain.UserAccount;
import service.UserAccountService;

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

@Path("accounts")
@Stateless
public class UserAccountResource {

    @Inject
    private UserAccountService userAccountService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<UserAccount> userAccounts = userAccountService.getAll();
        return Response.status(200).entity(userAccounts).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("id") long id){
        return Response.status(200).entity(userAccountService.findById(id)).build();
    }

}
