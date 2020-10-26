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
public class MyMoviesResource {

  private static final Logger LOG = LoggerFactory.getLogger(MyMoviesResource.class);

  private Film film;
  private Collection<Film> movies = new ArrayList<Film>();

  public MyMoviesResource(AllUsers allUsers, String name, User user) {
    this.allUsers = allUsers;
    this.name = name;
    this.user = user;
  }

  private void checkAllUsers() {
    if (this.allUsers == null) {
      throw new IllegalArgumentException("No User named \"" + this.name + "\"");
    }
  }

  /**
   * Gets the corresponding MyMovies.
   *
   * @return the corresponding MyMovies
   */

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Film getMovies() {
  checkAllUsers();
  LOG.debug("getAllUsers({})");
  return this.film;
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
  public boolean putUser() {
    return putUser(null);
  }