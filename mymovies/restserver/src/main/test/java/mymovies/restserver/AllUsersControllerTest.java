package mymovies.restserver;

import mymovies.core.AllUsers;
import mymovies.core.User;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {AllUsersController.class, AllUsersService.class})
@WebMvcTest
public class AllUsersControllerTest {

  @Autowired
  private MockMvc mvc;

  AllUsers all = new AllUsers();
  User user1 = new User("username1", "password1");
  User user2 = new User("username2", "password2");
  User user3 = new User("username3", "password3");
  User user4 = new User("username4", "password4");
  User user5 = new User("username5", "password5");

  @Test
  public void testGetUser() {
    all.addUser(user1);
    all.addUser(user2);
    all.addUser(user3);
    testUser(user1.getUserName(), user1);
    testUser(user2.getUserName(), user2);
    testUser(user3.getUserName(), user3);
  }

  public void testPostUser() {

  }


  private void testUser(String username, User user) throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/username")
            .content(username)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    String resultUser = result.getResponse().getContentAsString();
    assertNotNull(resultUser);
    assertEquals(user, resultUser);
  }
}