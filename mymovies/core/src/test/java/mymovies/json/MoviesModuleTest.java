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

import mymovies.core.Film;
import mymovies.core.MyMovies;


public class MoviesModuleTest {

  private static ObjectMapper mapper;

  @BeforeAll
  public static void setUp() {
    mapper = new ObjectMapper();
    mapper.registerModule(new MoviesModule());
  }

  private final static String myMoviesFilmer = "{\"myMovies\":[{\"name\":\"Test\",\"genre\":\"Horror\",\"rating\":3},{\"name\":\"Test2\",\"genre\":\"Action\",\"rating\":1}]}";

  @Test
  public void testSerializers() {
    Film film = new Film("Test", "Horror", 3);
    MyMovies myMovies = new MyMovies();
    myMovies.addMovie(film);
    try {
      assertEquals(myMoviesFilmer.replaceAll("\\s+", ""), mapper.writeValueAsString(film));
    } catch (JsonProcessingException e) {
      fail();
    }
  }

  static void checkFilmer(Film film, String name, String genre, int rating) {
    assertEquals(name, film.getName());
    assertEquals(genre, film.getGenre());
    assertEquals(rating, film.getRating());
  }

  static void checkFilmer(Film film1, Film film2) {
    checkFilmer(film1, film2.getName(), film2.getGenre(), film2.getRating());
  }

  @Test
  public void testDeserializers() {
    try {
      MyMovies myMovies = mapper.readValue(myMoviesFilmer, MyMovies.class);
      assertTrue(myMovies.iterator().hasNext());
      Film film = myMovies.iterator().next();
      assertEquals("Test", film.getName());
      Iterator<Film> it = film.iterator();
      assertTrue(it.hasNext());
      checkFilmer(it.next(), "Test", "Horror", 3);
      assertTrue(it.hasNext());
      checkFilmer(it.next(), "Test2", "Action", 1);
      assertFalse(it.hasNext());
    } catch (JsonProcessingException e) {
      fail();
    }
  }

  @Test
  public void testSerializersDeserializers() {
    MyMovies myMovies = new MyMovies();
    Film film1 = new Film("Test", "Horror", 3);
    Film film2 = new Film("Test2", "Action", 1);
    myMovies.addMovie(film1);
    myMovies.addMovie(film2);
    try {
      String json = mapper.writeValueAsString(myMovies);
      MyMovies model2 = mapper.readValue(json, MyMovies.class);
      assertTrue(model2.iterator().hasNext());
      assertEquals("Test", film1.getName());
      Iterator<Film> it = film2.iterator();
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
