package mymovies.restserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.IOException;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import mymovies.core.AllUsers;
import mymovies.core.Film;
import mymovies.core.RW;
import mymovies.core.User;
import mymovies.json.UsersModule;
import mymovies.json.UsersPersistence;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {AllUsersController.class, AllUserServiceForTesting.class, AllUsersApplication.class})
@WebMvcTest
public class AllUsersIntegrationTest {

  @Autowired
  private MockMvc mvc;

  AllUsers all = new AllUsers();
  User user1 = new User("testUser", "passord");
  User user2 = new User("testUser2", "testPassword2");
  ObjectMapper mapper = new ObjectMapper().registerModule(new UsersModule());
  UsersPersistence persistence = new UsersPersistence();
  RW rw = new RW();

  private final static String pathStarter = "/workspace/gr2003/mymovies/restserver/src/test/resources/mymovies/restserver/";
  private final String userPath = Paths.get(pathStarter + "it-allusers.json").toString();
  private final String uri = "http://localhost:8080/restserver/mymovies/";

  @BeforeEach
  public void setUp() throws IOException {
    all.addUser(user1);
    all.addUser(user2);
    persistence.write(all, rw.createWriter(userPath));
  }

  @Test
  public void postUser() throws IOException {
    try {
      testPostUser(user2);
    } catch (Exception e) {
      fail("Couldn't post user");
      e.printStackTrace();
    }
  }

  @Test
  public void getCorrectUserTest() {
    all.addUser(user1);
    all.addUser(user2);
    persistence.write(all, rw.createWriter(userPath));
    try {
      testGetUser(user1);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Couldn't recieve user: testUser");
    }
  }

  @Test
  public void postUserTest() {
    try {
      testPostUser(user1);
    } catch (Exception e) {
      fail("Could not change user: testUser");
      e.printStackTrace();
    } 
  }
  
  @Test
  public void putUserTest() {
    Film film = new Film("Title", "genre", 1);
    user1.addMovie(film);
    try {
      testPutUser(user1);
    } catch (Exception e) {
      fail("Couldn't read allUsers from file");
      e.printStackTrace();
    }
  }

  private void testGetUser(User user) throws Exception {
    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.get(uri + user.getUserName()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();
    System.out.println("Result: " + result.toString());

    System.out.println(result.getResponse().getContentAsString());
    User us = mapper.readValue(result.getResponse().getContentAsString(), User.class);
    System.out.println(us);
    assertNotNull(us);
    assertEquals("testUser", us.getUserName());
  }

  private void testPostUser(User user) throws Exception {
    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.post("http://localhost:8080/restserver/mymovies/" + user.getUserName()).content(mapper.writeValueAsString(user))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated()).andReturn();
    System.out.println("Result: " + result.toString());
  }

  private void testPutUser(User user) throws Exception {
    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.put("http://localhost:8080/restserver/mymovies/" + user.getUserName()).content(mapper.writeValueAsString(user))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();
    System.out.println("Result: " + result.toString());
  }
}
