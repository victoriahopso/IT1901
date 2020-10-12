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
  //En final static streng brukes for å verifisere at testSerializers og test Deserializers
  private final static String myMoviesFilmer = "{\"myMovies\":[{\"name\":\"Test\",\"genre\":\"Horror\",\"rating\":3},{\"name\":\"Test2\",\"genre\":\"Action\",\"rating\":1}]}";

  //Vi lager en myMovies med to Film objekt og sjekker at serializers stemmer overens med myMoviesFilmer.
  @Test
  public void testSerializers() {
    MyMovies myMovies = new MyMovies();
    Film film1 = new Film("Test", "Horror", 3);
    Film film2 = new Film("Test2", "Action", 1);
    myMovies.addMovie(film1);
    myMovies.addMovie(film2);
    try {
      assertEquals(myMoviesFilmer.replaceAll("\\s+", ""), mapper.writeValueAsString(myMovies));
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
  @Test
  public void testDeserializers() {
    try {
      MyMovies myMovies = mapper.readValue(myMoviesFilmer, MyMovies.class);
      Iterator<Film> it = myMovies.iterator();
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

  //Testen sjekker at dersom man serializer en myMovie klasse med to film objekt og deserializer den med en gang, at verdiene stemmer overens
  @Test
  public void testSerializersDeserializers() {
    MyMovies myMovies1 = new MyMovies();
    Film film1 = new Film("Test", "Horror", 3);
    Film film2 = new Film("Test2", "Action", 1);
    myMovies1.addMovie(film1);
    myMovies1.addMovie(film2);
    try {
      String json = mapper.writeValueAsString(myMovies1);
      MyMovies myMovies2 = mapper.readValue(json, MyMovies.class);
      Iterator<Film> it = myMovies2.iterator();
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
