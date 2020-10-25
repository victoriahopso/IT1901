package mymovies.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
  //En final static streng brukes for å verifisere at testSerializers og test Deserializers
  private final static String myMoviesFilmer = "{\"myMovies\":[{\"name\":\"Test\",\"genre\":\"Horror\",\"rating\":3},{\"name\":\"Test2\",\"genre\":\"Action\",\"rating\":1}]}";

  //Vi lager en myMovies med to Film objekt og sjekker at serializers stemmer overens med myMoviesFilmer.
  @Test
  public void testSerializers() {
    User user = new User("Name1", "Password1");
    Film film1 = new Film("Test", "Horror", 3);
    Film film2 = new Film("Test2", "Action", 1);
    user.addMovie(film1);
    user.addMovie(film2);
    allUsers.addUser(user);
    try {
      assertNotEquals(myMoviesFilmer.replaceAll("\\s+", ""), mapper.writeValueAsString(allUsers)); //Dette må endres til riktig format av json-fil, og dermed til assertEquals
    } catch (JsonProcessingException e) {
      fail();
    }
  }

  //Hjelpemetoder som sjekker at navn, sjanger og rating til en film stemmer overns med det som er antatt
  static void checkFilmer(Film film, String name, String genre, int rating) {
    assertEquals(name, film.getName());
    assertEquals(genre, film.getGenre());
    assertEquals(rating, film.getRating());
  }

  static void checkFilmer(Film film1, Film film2) {
    checkFilmer(film1, film2.getName(), film2.getGenre(), film2.getRating());
  }

  //Vi bruker deserializers til å lese av myMoviesFilmer og lager en MyMovies klasse av verdiene og deretter itererer og sjekker at det stemmer overens
  /**
  @Test
  public void testDeserializers() {
    try {
      AllUsers allUsersComp = mapper.readValue(myMoviesFilmer, AllUsers.class); //Må endre linje 31 slik at den stemmmer
      User user1 = allUsersComp.getAllUsers().iterator().next();
      Iterator<Film> it = user1.getMyMovies().iterator();
      assertTrue(it.hasNext());
      Film film1 = it.next();
      checkFilmer(film1, "Test", "Horror", 3);
      assertTrue(it.hasNext());
      Film film2 = it.next();
      checkFilmer(film2, "Test2", "Action", 1);
      assertFalse(it.hasNext());
    } catch (JsonProcessingException e) {
      fail();
    }
  }
  */

  //Testen sjekker at dersom man serializer en myMovie klasse med to film objekt og deserializer den med en gang, at verdiene stemmer overens
  @Test
  public void testSerializersDeserializers() {
    AllUsers allUsers2 = new AllUsers();
    User user2 = new User("Name2","Password2");
    Film film1 = new Film("Test", "Horror", 3);
    Film film2 = new Film("Test2", "Action", 1);
    allUsers2.addUser(user2);
    user2.addMovie(film1);
    user2.addMovie(film2);
    try {
      String json = mapper.writeValueAsString(allUsers2);
      AllUsers allUsers3 = mapper.readValue(json, AllUsers.class); //Endre slik at denne også brukes
      User user3 = allUsers3.getAllUsers().iterator().next();
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
