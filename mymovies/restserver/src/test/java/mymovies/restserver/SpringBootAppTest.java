package mymovies.restserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import mymovies.core.AllUsers;
import mymovies.core.User;


@SpringBootTest
public class SpringBootAppTest {
/*
  @Autowired
  AllUsersService service;

  @MockBean
  AllUsers all;

  @BeforeEach
  void setUp() {
    this.all = new AllUsers();
    this.service = new AllUsersService(this.all);
  }

  @Test
  public void getAllUsersTest() {
    when(all.getAllUsers()).thenReturn(Stream
        .of(new User("username1", "password1"), new User("userName2", "password2"), new User("userName3", "password3"))
        .collect(Collectors.toList()));
    assertEquals(3, service.getAllUsers().getAllUsers().size());
  }

  @Test
  public void getUserTest() {
    User user = new User("username2", "password2");
    when(all.getUser("username2")).thenReturn(user);
    assertEquals(user, service.getAllUsers().getUser("username2"));
  }

  @Test
  public void updateUserTest() {
    User user1 = new User("username1", "password1");
    all.addUser(user1);
    when(all.getUser("username1").updateUser(user1));
  }
*/
}
