package mymovies.restserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import mymovies.core.AllUsers;
import mymovies.core.Film;
import mymovies.core.ReadWrite;
import mymovies.core.User;
import mymovies.json.UsersModule;
import mymovies.json.UsersPersistence;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

@AutoConfigureMockMvc
@ContextConfiguration(classes = {AllUsersController.class, AllUserServiceForTesting.class, AllUsersApplication.class})
@WebMvcTest
public class AllUsersIntegrationTest {

  @Autowired
  private MockMvc mvc;

  private AllUsers all = new AllUsers();
  private User user1 = new User("testUser", "password");
  private User user2 = new User("testUser2", "testPassword2");
  private ObjectMapper mapper = new ObjectMapper().registerModule(new UsersModule());
  private UsersPersistence persistence = new UsersPersistence();
  private ReadWrite rw = new ReadWrite();

  private final static String pathStarter = "/workspace/gr2003/mymovies/restserver/src/test/resources/mymovies/restserver/";
  private final String userPath = Paths.get(pathStarter + "it-allusers.json").toString();
  private final String uri = "http://localhost:8080/restserver/mymovies/";

  @BeforeEach
  public void setUp() {
    all.addUser(user1);
    all.addUser(user2);
    OutputStreamWriter writer = rw.createWriter(userPath);
    persistence.write(all, writer);
    try {
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void getCorrectUserTest() {
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
      fail("Couldn't add User");
      e.printStackTrace();
    } 
  }
  
  @Test
  public void putUserTest() {
    Film film = new Film("title", "genre", 4);
    user1.addMovie(film);
    try {
      testPutUser(user1);
    } catch (Exception e) {
      fail("Couldn't update user");
      e.printStackTrace();
    }
  }

  private void testGetUser(User user) throws Exception {
    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.get(uri + user.getUserName()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();
    System.out.println("Result: " + result.toString());
    
    User us = mapper.readValue(result.getResponse().getContentAsString(), User.class);
    assertNotNull(us);
    assertEquals("testUser", us.getUserName());
    assertEquals("password", us.getPassword());
  }

  private void testPostUser(User user) throws Exception {
    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.post(uri + user.getUserName()).content(mapper.writeValueAsString(user))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated()).andReturn();
    System.out.println("Result: " + result.toString());

    assertEquals("Bruker lagt til", result.getResponse().getContentAsString());
  }

  private void testPutUser(User user) throws Exception {
    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.put(uri + user.getUserName()).content(mapper.writeValueAsString(user))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();
    System.out.println("Result: " + result.toString());

    assertEquals("Bruker endret", result.getResponse().getContentAsString());
  }
}