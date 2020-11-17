package mymovies.restserver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertIsEmpty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import mymovies.core.AllUsers;
import mymovies.restserver.AllUsersService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AllUsersServiceTest{

  @MockBean
  private AllUsers allUsers;

  private AllUsersService allUsersService;

  @BeforeEach
  void setUp() {
    this.allUsersService = new AllUsersService(this.allUsers);
  }
  //Tester getter og setter med parametere
  @Test
  public void AllUsersServiceWithParameterTest(){
    assertEquals(allUsersService.getAllUsers(), this.allUsers);
    AllUsers test = new AllUsers();
    allUsersService.setAllUsers(test);
    assertEquals(allUsersService.getAllUsers(), test);
  }

  //Uten parameter så skal den retunere en AllUser objekt som er tomt
  @Test
  public void AllUsersServiceWithoutParameterTest(){
    AllUsersService test = new AllUsersService();
    assertNotNull(test);
    //assertIsEmpty(test);
  }
}