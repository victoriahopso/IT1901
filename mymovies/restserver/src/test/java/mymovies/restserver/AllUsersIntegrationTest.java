package mymovies.restserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Collection;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
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
import mymovies.core.RW;
import mymovies.core.User;
import mymovies.json.UsersModule;
import mymovies.json.UsersPersistence;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {AllUsersController.class, AllUsersService.class})
@WebMvcTest
public class AllUsersIntegrationTest {

  @Autowired
  private MockMvc mvc;

  AllUsers all = new AllUsers();
  User user1 = new User("testUser", "testPassword");
  User user2 = new User("testUser2", "testPassword2");
  ObjectMapper mapper = new ObjectMapper().registerModule(new UsersModule());
  UsersPersistence persistence = new UsersPersistence();
  RW rw = new RW();

  private final static String pathStarter = "/workspace/gr2003/mymovies/restserver/src/test/resources/mymovies/restserver/";
  private final String userPath = Paths.get(pathStarter + "it-allusers.json").toString();

  //Kjører før hver test
  @BeforeEach
  public void setUp() throws IOException {
    all = new AllUsers();
    user1 = new User("testUser", "testPassword");
    all.addUser(user1);
    persistence.write(all, rw.createWriter(userPath));
  }

  // Skriver over allUsers-objektet med ett nytt tomt
  @AfterEach
  public void deleteTestUser() throws IOException {
    all.getAllUsers().stream().filter(user -> user.getUserName().equals(user1.getUserName())).findAny().orElse(null);
    persistence.write(new AllUsers(), rw.createWriter(userPath));
  }

  @Test
  public void postUser() throws IOException {
    try {
      testPostUser(user2);
      all = persistence.read(rw.createReader(userPath));
    } catch (Exception e) {
      fail("Couldn't post user");
      e.printStackTrace();
    }

    Collection<User> users = all.getAllUsers();
    users.remove(users.stream().filter(user -> user.equals(user2)).findAny().orElse(null));
    persistence.write(all, rw.createWriter(userPath));
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
  public void putUserTest() {
    try {
      testPutUser(user1);
    } catch (Exception e) {
      fail("Could not change user: testUser");
    }
  }

  /*
  @Test
  public void getAllUsers() {
    try {
      assertEquals(all, persistence.read(rw.createReader(userPath)));
    } catch (Exception e) {
      fail("Couldn't read allUsers from file");
    }
  }
  */

  private void testGetUser(User user) throws Exception {
    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.get("restserver/mymovies/" + user.getUserName()).content(mapper.writeValueAsString(user))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();
    System.out.println("Result: " + result.toString());

    byte[] resultUserByte = result.getResponse().getContentAsByteArray();
    String resultUser = new String(resultUserByte, StandardCharsets.UTF_8);
    assertNotNull(resultUser);
    assertEquals(user, mapper.readValue(rw.createReader(userPath), AllUsers.class));
  }

  private void testPostUser(User user) throws Exception {
    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.post("restserver/mymovies/" + user.getUserName()).content(mapper.writeValueAsString(user))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();
    System.out.println("Result: " + result.toString());
  }

  private void testPutUser(User user) throws Exception {
    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.put("restserver/mymovies/" + user.getUserName()).content(mapper.writeValueAsString(user))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();
    System.out.println("Result: " + result.toString());
  }
}
