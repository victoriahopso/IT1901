package mymovies.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import mymovies.core.AllUsers;
import mymovies.core.Film;
import mymovies.core.User;

public class UsersModuleTest {

  private static ObjectMapper mapper;
  private AllUsers allUsers = new AllUsers();

  @BeforeAll
  public static void setUp() {
    mapper = new ObjectMapper();
    mapper.registerModule(new UsersModule());
  }

  // En final static streng brukes for å verifisere at testSerializers og test
  // Deserializers
  private final static String myMoviesFilmer = 
  "{\"allUsers\":[{\"username\":\"Name1\",\"password\":\"Password1\",\"myMovies\":[{\"name\":\"Test\",\"genre\":\"Horror\",\"rating\":3},{\"name\":\"Test2\",\"genre\":\"Action\",\"rating\":1}]}]}";

  @Test
  public void testSerializers() {
    User user = new User("Name1", "Password1");
    Film film1 = new Film("Test", "Horror", 3);
    Film film2 = new Film("Test2", "Action", 1);
    user.addMovie(film1);
    user.addMovie(film2);
    allUsers.addUser(user);
    try {
      assertEquals(myMoviesFilmer.replaceAll("\\s+", ""), mapper.writeValueAsString(allUsers));
    } catch (JsonProcessingException e) {
      fail();
    }
  }

  // Hjelpemetoder som sjekker at navn, sjanger og rating til en film stemmer
  // overens med det som er antatt
  static void checkFilmer(Film film, String name, String genre, int rating) {
    assertEquals(name, film.getName());
    assertEquals(genre, film.getGenre());
    assertEquals(rating, film.getRating());
  }

  static void checkFilmer(Film film1, Film film2) {
    checkFilmer(film1, film2.getName(), film2.getGenre(), film2.getRating());
  }

  //Hjelpemetoder for å sjekke at brukernavn og passord stemmer overens med det som er antatt
  static void checkCredentials(User user, String userName, String password){
    assertEquals(userName, user.getUserName());
    assertEquals(password, user.getPassword());
  }

  static void checkCredentials(User user1, User user2){
    checkCredentials(user1, user2.getUserName(), user2.getPassword());
  }
  
  @Test
   public void testDeserializers() { 
    try { 
    AllUsers allUsersComp = mapper.readValue(myMoviesFilmer, AllUsers.class);
    User user1 = allUsersComp.getAllUsers().iterator().next();
    checkCredentials(user1, "Name1", "Password1");
    Iterator<Film> it = user1.getMyMovies().iterator(); 
    assertTrue(it.hasNext());
    Film film1 = it.next();
    checkFilmer(film1, "Test", "Horror", 3);
    assertTrue(it.hasNext()); 
    Film film2 = it.next();
    checkFilmer(film2, "Test2", "Action", 1);
    assertFalse(it.hasNext());
   } catch
          (JsonProcessingException e) { 
          fail();
     }
   }

  @Test
  public void testSerializersDeserializers() {
    AllUsers allUsers2 = new AllUsers();
    User user2 = new User("Name2", "Password2");
    Film film1 = new Film("Test", "Horror", 3);
    Film film2 = new Film("Test2", "Action", 1);
    allUsers2.addUser(user2);
    user2.addMovie(film1);
    user2.addMovie(film2);
    try {
      String json = mapper.writeValueAsString(allUsers2);
      AllUsers allUsers3 = mapper.readValue(json, AllUsers.class);
      User user3 = allUsers3.getAllUsers().iterator().next();
      checkCredentials(user3, user2);
      Iterator<Film> it = user3.getMyMovies().iterator();
      assertTrue(it.hasNext());
      checkFilmer(it.next(), film1);
      assertTrue(it.hasNext());
      checkFilmer(it.next(), film2);
      assertFalse(it.hasNext());
    } catch (JsonProcessingException e) {
      fail();
    }
  }
}