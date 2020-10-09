package mymovies.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import org.junit.jupiter.api.Test;

import mymovies.core.Film;
import mymovies.core.MyMovies;

public class MoviesPersistenceTest {

  private MoviesPersistence moviePersistence = new MoviesPersistence();

  @Test
  public void testSerializersDeserializers() {
    MyMovies myMovies = new MyMovies();
    Film film1 = new Film("Test", "Horror", 3);
    Film film2 = new Film("Test2", "Action", 1);
    myMovies.addMovie(film1);
    myMovies.addMovie(film2);
    try {
      StringWriter writer = new StringWriter();
      moviePersistence.write(myMovies, writer);
      String json = writer.toString();
      MyMovies model2 = moviePersistence.read(new StringReader(json));
      assertTrue(model2.iterator().hasNext());
      assertEquals("test", film1.getName());
      Iterator<Film> it = film2.iterator();
      assertTrue(it.hasNext());
      MoviesModuleTest.checkFilmer(it.next(), film1);
      assertTrue(it.hasNext());
      MoviesModuleTest.checkFilmer(it.next(), film2);
      assertFalse(it.hasNext());
    } catch (IOException e) {
      fail();
    }
  }
}
