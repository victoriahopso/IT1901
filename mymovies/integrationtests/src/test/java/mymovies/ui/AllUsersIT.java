package mymovies.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.Test;
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
import mymovies.core.User;
import mymovies.restserver.AllUsersController;
import mymovies.restserver.AllUsersService;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {AllUsersController.class, AllUsersService.class, User.class
                      , AllUsers.class, Film.class, MyMoveisController.class, LogInController.class})
@WebMvcTest
public class AllUsersIT {

  @Autowired
  private MockMvc mvc;

  AllUsers all = new AllUsers();
  User user1 = new User("username1", "password1");
  User user2 = new User("username2", "password2");
  User user3 = new User("username3", "password3");
  User user4 = new User("username4", "password4");
  User user5 = new User("username5", "password5");

  @Test
  public void testGetUser() throws Exception {
    all.addUser(user1);
    all.addUser(user2);
    all.addUser(user3);
    testGetUser(user1.getUserName(), user1);
    testGetUser(user2.getUserName(), user2);
    testGetUser(user3.getUserName(), user3);
  }

  public void testPostUser() {

  }


  private void testGetUser(String username, User user) throws Exception {
    MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/username").content(username)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andReturn();

    User finalUser = result.getResponse();
    assertNotNull(finalUser);
    assertEquals(user, finalUser);
  }

  private void testPostUser(String username, User user) throws Exception {
    MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/username").content(username)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andReturn();

    User finalUser = result.getResponse();
    assertNotNull(finalUser);
    assertEquals(user, finalUser);
  }

  private void testPutUser(String username, User user) throws Exception {
    MvcResult result = mvc.perform(MockMvcRequestBuilders.put("/username").content(username)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andReturn();

    User finalUser = result.getResponse();
    assertNotNull(finalUser);
    assertEquals(user, finalUser);
  }
}