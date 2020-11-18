package mymovies.restserver;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import mymovies.core.AllUsers;
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

  //Uten parameter så skal den retunere et AllUser objekt som er tomt
  @Test
  public void AllUsersServiceWithoutParameterTest(){
    AllUsersService test = new AllUsersService();
    assertNotNull(test);
    assertEquals(test.getAllUsers().getAllUsers().size(), 0);
  }
}