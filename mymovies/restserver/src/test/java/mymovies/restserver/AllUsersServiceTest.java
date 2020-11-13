package mymovies.restserver;

import java.beans.Transient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import mymovies.core.AllUsers;
import mymovies.core.User;

@RunWith(SpringRunner.class)
public class AllUsersServiceTest {

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

  @Before
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

}