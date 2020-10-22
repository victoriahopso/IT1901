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
import mymovies.core.MyMovies;
/**
 * Used for all requests referring to MyMovies by name.
 */
public class MyMoviesResource {

  private static final Logger LOG = LoggerFactory.getLogger(MyMoviesResource.class);

  private final MyMovies mymovie;

  /**
   * Initializes this TodoListResource with appropriate context information.
   * Each method will check and use what it needs.
   *
   * @param mymovie the MyMovies, needed for 
   */
  public MyMoviesResource(MyMovies mymovie) {
    this.mymovie = mymovie;
  }

  private void checkMyMovies() {
    if (this.MyMovies == null) {
      throw new IllegalArgumentException("No MyMovies named \"" + name + "\"");
    }
  }

  /**
   * Gets the corresponding MyMovies.
   *
   * @return the corresponding MyMovies
   */

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public MyMovies getMyMovies() {
  checkMyMovies();
  LOG.debug("getMyMovies({})");
  return this.mymovie;
  }
  /**
   * Replaces or adds a MyMovies.
   *
   * @param MyMoviesArg the mymovie to add
   * @return true if it was added, false if it replaced
   */

   /** 
   *  @PUT
   *  @Consumes(MediaType.APPLICATION_JSON)
   *  @Produces(MediaType.APPLICATION_JSON)
   *  public boolean putMyMovies(AbstractTodoList todoListArg) {
   *  LOG.debug("putMyMovies({})", todoListArg);
   * return this.mymovie.putTodoList(todoListArg) == null;
  }
  */

  /**
   * Add a MyMovie with the given name, if it doesn't exist already.
   */
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  public boolean putMyMovies() {
    return putMyMovies(null);
  }