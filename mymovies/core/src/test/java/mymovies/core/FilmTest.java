package mymovies.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FilmTest {

  @Test
  public void testContructor() {
    Film film = new Film("testname", "genre", 5);
    assertEquals("testname", film.getName());
    assertEquals("genre", film.getGenre());
    assertEquals(5, film.getRating());
  }

  @Test
  public void testToString() {
    Film film = new Film("testname", "genre", 5);
    assertEquals("Film: testname, Genre: genre, Rating: 5", film.toString());
  }

}