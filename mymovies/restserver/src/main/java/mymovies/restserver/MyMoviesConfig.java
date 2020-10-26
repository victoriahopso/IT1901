package restserver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import todolist.core.TodoList;
import todolist.core.TodoModel;
import todolist.json.TodoPersistence;
import todolist.restapi.TodoModelService;

public class MyMoviesConfig extends ResourceConfig {

  private AllUsers allUsers;

  /**
   * Initialize this TodoConfig.
   *
   * @param todoModel todoModel instance to serve
   */
  public MyMoviesConfig(AllUsers allUsers) {
    setAllUsers(AllUsers);
    register(MyMoviesService.class);
    register(MyMoviesObjectMapperProvider.class);
    register(JacksonFeature.class);
    register(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(MyMoviesConfig.this.AllUsers);
      }
    });
  }

  /**
   * Initialize this MyMoviesConfig with a default TodoModel.
   */
  public MyMoviesConfig() {
    this(createDefaultAllUsers());
  }
//model
  public TodoModel getAllUsers() {
    return this.AllUsers;
  }

  public void setAllUsers(AllUsers allUsers) {
    this.allUsers = allUsers;
  }

  private static AllUsers createDefaultAllUsers() {
    MyMoviesPersistence MyMoviesPersistence = new MyMoviesPersistence();
    URL url = MyMoviesConfig.class.getResource("default-AllUsers.json");
    if (url != null) {
      try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
        return MyMoviesPersistence.readAllUsers(reader);
      } catch (IOException e) {
        System.out.println("Couldn't read default-AllUsers.json, so rigging AllUsers manually ("
            + e + ")");
      }
    }
    AllUsers allUsers = new AllUsers();
    allUsers.addAllUsers(new AllUsers("user1"));
    allUsers.addAllUsers(new AllUsers("user2"));
    return allUsers;
  }
}
