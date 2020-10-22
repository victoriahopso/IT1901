package mymovies.restapi
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import todolist.core.AbstractTodoList;
import todolist.core.TodoModel;

@Path(MyMoviesService.MY_MOVIES_SERVICE_PATH)
public class MyMoviesService {

  public static final String MY_MOVIES_SERVICE_PATH = "my";

  private static final Logger LOG = LoggerFactory.getLogger(MyMoviesService.class);

  @Inject
  private MyMovies mymovies;

  /**
   * The root resource, i.e. /todo
   *
   * @return the TodoModel
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public MyMovies getMyMovies() {
    return todoModel;
  }

  /**
   * Returns the TodoList with the provided name
   * (as a resource to support chaining path elements).
   * This supports all requests referring to TodoLists by name.
   * Note that the TodoList needn't exist, since it can be a PUT.
   *
   * @param name the name of the todo list
   */
  @Path("/{name}")
  public TodoListResource getTodoList(@PathParam("name") String name) {
    AbstractTodoList todoList = getTodoModel().getTodoList(name);
    LOG.debug("Sub-resource for TodoList " + name + ": " + todoList);
    return new TodoListResource(todoModel, name, todoList);
  }
}
