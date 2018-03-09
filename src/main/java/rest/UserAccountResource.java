package rest;

import domain.UserAccount;
import service.UserAccountService;

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

@Path("accounts")
@Stateless
public class UserAccountResource {

    @Inject
    private UserAccountService userAccountService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccounts() {
        List<UserAccount> userAccounts = userAccountService.getAll();
        return Response.ok(userAccounts).build();
    }

    @GET
    @Path("findById")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountById(@QueryParam("id") Long id) {
        return Response.ok(userAccountService.findById(id)).build();
    }

    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countAllAccounts() {
        return Response.ok(userAccountService.countAll()).build();
    }

}
