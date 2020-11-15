package mymovies.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Collection;
import mymovies.core.AllUsers;
import mymovies.core.RW;
import mymovies.core.User;
import mymovies.json.UsersModule;
import mymovies.restserver.AllUsersController;
import mymovies.restserver.AllUsersService;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {AllUsersController.class, AllUsersService.class})
@WebMvcTest
public class AllUsersIT {

  @Autowired
  private MockMvc mvc;

  AllUsers all = new AllUsers();
  User user1 = new User("testUser", "testPassword");
  User user2 = new User("testUser2", "testPassword2");
  ObjectMapper mapper = new ObjectMapper().registerModule(new UsersModule());
  RW rw = new RW();

  private final static String pathStarter = "../mymovies/integrationtests/src/test/resources/mymovies/ui/";
  private final String userPath = Paths.get(pathStarter + "it-allusers.json").toString();

  @BeforeEach
  public void setUp() {
    all = new AllUsers();
    user1 = new User("testUser", "testPassword");
    all.addUser(user1);
    try {
      mapper.writeValue(rw.createWriter(userPath), AllUsers.class);
    } catch (IOException e) {
      fail("Couldn't write user");
      e.printStackTrace();
    }
  }

  // Skriver over allUsers-objektet med ett nytt tomt
  @AfterEach
  public void deleteTestUser() {
    all.getAllUsers().stream().filter(user -> user.getUserName().equals(user1.getUserName())).findAny().orElse(null);
    try {
      mapper.writeValue(rw.createWriter(userPath), AllUsers.class);
    } catch (IOException e) {
      fail("Couldn't write to file in deleteTestuser");
      e.printStackTrace();
    }
  }

  @Test
  public void postUser() {
    try {
      testPostUser(user2, user2.getUserName());
      all = mapper.readValue(rw.createReader(userPath), AllUsers.class);
    } catch (Exception e) {
      fail("Couldn't post user");
      e.printStackTrace();
    }

    Collection<User> users = all.getAllUsers();
    users.remove(users.stream().filter(user -> user.equals(user2)).findAny().orElse(null));
    try {
      mapper.writeValue(rw.createWriter(userPath), AllUsers.class);
    } catch (IOException e) {
      fail("Couldn't remove user");
      e.printStackTrace();
    }
  }

  @Test
  public void getCorrectUserTest() {
    try {
      testGetUser(user1, "testUser");
    } catch (Exception e) {
      e.printStackTrace();
      fail("Couldn't recieve user: testUser");
    }
  }

  @Test
  public void putUserTest() {
    try {
      testPutUser(user1, "testUser");
    } catch (Exception e) {
      fail("Could not change user: testUser");
    }
  }

  @Test
  public void getAllUsers() {
    try {
      assertEquals(testGetAllUsers(), mapper.readValue(rw.createReader(userPath), AllUsers.class));
    } catch (Exception e) {
      fail("Couldn't read allUsers from file");
    }
  }

  private AllUsers testGetAllUsers() throws Exception {
    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.get("/api/user/users").content(mapper.writeValueAsString(all))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    byte[] resultUserByte = result.getResponse().getContentAsByteArray();
    String resultUser = new String(resultUserByte, StandardCharsets.UTF_8);
    assertNotNull(resultUser);
    return mapper.readValue(resultUser, AllUsers.class);
  }

  private void testGetUser(User user, String username) throws Exception {
    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.get("/username").content(mapper.writeValueAsString(user))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();
    System.out.println("Result: " + result.toString());

    byte[] resultUserByte = result.getResponse().getContentAsByteArray();
    String resultUser = new String(resultUserByte, StandardCharsets.UTF_8);
    assertNotNull(resultUser);
    assertEquals(user, mapper.readValue(rw.createReader(userPath), AllUsers.class));
  }

  private void testPostUser(User user, String username) throws Exception {
    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.post("/username").content(mapper.writeValueAsString(user))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();
    System.out.println("Result: " + result.toString());
  }

  private void testPutUser(User user, String username) throws Exception {
    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.put("/username").content(mapper.writeValueAsString(user))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();
    System.out.println("Result: " + result.toString());
  }
}
