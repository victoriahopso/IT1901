package mymovies.restapi

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path(MyMoviesService.MY_MOVIES_SERVICE_PATH)
public class MyMoviesService {

    public static final String MY_MOVIES_SERVICE_PATH = "movies";

    private static final Logger LOG = LoggerFactory.getLogger(MyMoviesService.class);

    @Inject
    private User user;

    /**
    * The root resource,
    *
    * @return the AllUsers
    */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
    return this.user;
    }


    //Prøver å legge til/ bytte ut en collection med filmer som hører til en user
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void putMovies(Collection<Film> col) {
        LOG.debug("putMovies({})",col);
        this.user.setMyMovies(col);
    }
}
