package mymovies.restserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import mymovies.core.AllUsers;
import mymovies.core.User;
import mymovies.json.UsersModule;

//@RunWith(SpringRunner.class)
public class AllUsersServiceTest {

  /*

  @TestConfiguration
  static class AllUsersServiceTestContextConfiguration {

    @Bean
    public AllUsersService getService() {
      return new AllUsersService();
    }
  }

  @Autowired
  private AllUsersService service;

  @MockBean
  private AllUsers all;

  private ObjectMapper mapper;

  @BeforeAll
  public void setUp() {
    User user1 = new User("Kilperik", "password");
    all.addUser(user1);
    mapper = new ObjectMapper().registerModule(new UsersModule());
    service = new AllUsersService();

    Mockito.when(all.getUser("Kilperik")).thenReturn(user1);

    // Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
  }

  @Test
  public void firstAllUsers() {
  }
  */

}