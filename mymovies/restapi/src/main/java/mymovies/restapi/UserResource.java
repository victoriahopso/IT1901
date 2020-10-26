package mymovies.restapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Used for all requests referring to MyMovies by name.
 */
public class UserResource {

    private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);

    public AllUsers allUsers;
    public String username;
    public User user;


    public MyMoviesResource(AllUsers allUsers, String username, User user) {
        this.allUsers = allUsers;
        this.username = username;
        this.user = user;
    }

    private void checkUser() {
        if (this.user == null) {
        throw new IllegalArgumentException("No User with username: \"" + this.username + "\"");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser {
        checkUser();
        LOG.debug("getUser({})");
        return this.user;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void putUser(User user) {
        LOG.debug("putUser({})", user);

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public boolean putUser() {
        return putUser(null);
    }
}