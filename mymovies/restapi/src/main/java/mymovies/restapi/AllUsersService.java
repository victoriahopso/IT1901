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


    //Prøver å legge til/ bytte ut en collection med filmer som hører til en user
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void putMovies(Collection<Film> col) {
        LOG.debug("putMovies({})",col);
        this.user.setMyMovies(col);
    }

    @Path("/{username}")
    public UserResources getUsers(@PathParam("username") String username) {
        User user = getAllUsers().getUser(user);
        LOG.debug("Sub-resource for User " + username + ": " + user);
        return new UserResources(allUsers, username, user);
    }

}
