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
 * Used for all requests referring to TodoLists by name.
 */
public class MyMoviesResource {

  private static final Logger LOG = LoggerFactory.getLogger(MyMoviesResource.class);

  private final MyMovies mymovie;
  private final String name;

  /**
   * Initializes this TodoListResource with appropriate context information.
   * Each method will check and use what it needs.
   *
   * @param mymovie the TodoModel, needed for DELETE and rename
   * @param name the todo list name, needed for most requests
   *  the TodoList, or null, needed for PUT
   */
  public MyMoviesResource(MyMovies mymovie, String name) {
    this.mymovie = mymovie;
    this.name = name;
  }

  private void checkMyMovies() {
    if (this.MyMovies == null) {
      throw new IllegalArgumentException("No MyMovies named \"" + name + "\"");
    }
  }

  /**
   * Gets the corresponding MyMovie.
   *
   * @return the corresponding MyMovie
   */

   /** 
  * @GET
  * @Produces(MediaType.APPLICATION_JSON)
  * public AbstractTodoList getTodoList() {
  * checkMyMovies();
  * LOG.debug("getMyMovies({})", name);
  * return this.mymovie;
  }
 */


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

  /**
   * Renames MyMovies.
   *
   * @param newName the newName
   */
  @POST
  @Path("/rename")
  @Produces(MediaType.APPLICATION_JSON)
  public boolean renameTodoList(@QueryParam("newName") String newName) {
    checkMyMovies();
    if (this.mymovie.getTodoList(newName) != null) {
      throw new IllegalArgumentException("A TodoList named \"" + newName + "\" already exists");
    }
    this.mymovie.setName(newName);
    return true;
  }

  /**
   * Removes the TodoList.
   */
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  public boolean removeTodoList() {
    checkTodoList();
    this.todoModel.removeTodoList(this.todoList);
    return true;
  }
}
