package mymovies.restapi

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import mymovies.core.AllUsers;

@Path(MyMoviesService.MY_MOVIES_SERVICE_PATH)
public class AllUsersService {

    public static final String MY_MOVIES_SERVICE_PATH = "allUsers";

    private static final Logger LOG = LoggerFactory.getLogger(AllUsersService.class);

    @Inject
    private AllUsers allUsers;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AllUsers getAllUsers() {
    return this.allUsers;
    }

    //Litt usikker på denne, mulig man må ha et annet argument
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean isUser(String username) {
        for (User user : allUsers.getAllUsers()) {
            if (user.getUserName() == username) {
                return true;
            }
        }
        return false;
    }

    @Path("/{username}")
    public UserResources getUsers(@PathParam("username") String username) {
        User user = getAllUsers().getUser(user);
        LOG.debug("Sub-resource for User " + username + ": " + user);
        return new UserResources(allUsers, username, user);
    }

}
