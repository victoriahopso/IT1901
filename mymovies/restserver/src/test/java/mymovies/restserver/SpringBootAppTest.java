package mymovies.restserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import mymovies.core.AllUsers;
import mymovies.core.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAppTest {

  @Autowired
  AllUsersService service;

  @MockBean
  AllUsers all;

  public void getAllUsersTest() {
    when(all.getAllUsers()).thenReturn(Stream.of(new User("username1", "password1")
        , new User("userName2", "password2"), new User("userName3", "password3")).collect(Collectors.toList()));
    assertEquals(3, service.getAllUsers().getAllUsers().size());
  }
}