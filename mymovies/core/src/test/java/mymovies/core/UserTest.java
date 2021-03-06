package mymovies.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class UserTest {

  // Tester at brukernavn og passord blir korrekt.

  // Sjekker at filmen faktisk blir lagt til i lista, og at den ikke tillater
  // duplikater
  @Test
  public void testAddMovie() {
    User user = new User("name2", "password2");
    Film film = new Film("Shrek", "romantic", 6);
    user.addMovie(film);
    assertEquals("Shrek", user.getMyMovies().iterator().next().getName());
    assertEquals("romantic", user.getMyMovies().iterator().next().getGenre());
    assertEquals(6, user.getMyMovies().iterator().next().getRating());

    user.addMovie(film);
    assertEquals(1, user.getMyMovies().size());
  }

  @Test
  public void testRemoveMovie() {
    User user = new User("name2", "password2");
    Film film = new Film("Shrek", "romantic", 6);
    user.addMovie(film);

    assertEquals("Shrek", user.getMyMovies().iterator().next().getName());
    assertEquals("romantic", user.getMyMovies().iterator().next().getGenre());
    assertEquals(6, user.getMyMovies().iterator().next().getRating());

    user.removeMovie(film);
    assertTrue(user.getMyMovies().isEmpty());
  }

  @Test
  public void testUpdateUser() {
    User user = new User("name", "password");
    User user2 = new User("name2", "password2");

    Film film1 = new Film("Shrek", "romantic", 6);

    user.addMovie(film1);

    user.updateUser(user2);
    assertEquals("name2", user.getUserName());
    assertEquals("password2", user.getPassword());
    assertTrue(user.getMyMovies().isEmpty());
  }

  /**
   * sjekker at lista som returneres inneholder objektene som er blitt lagt til
   */

  @Test
  public void testGetMyMovies() {
    Collection<Film> result = new ArrayList<>();
    User user = new User("username", "password");
    Film film1 = new Film("The boy", "horror", 1);
    Film film2 = new Film("Shrek", "romantic", 6);
    Film film3 = new Film("Iron man", "comedy", 4);
    result.add(film1);
    result.add(film2);
    result.add(film3);
    user.addMovie(film1);
    user.addMovie(film2);
    user.addMovie(film3);
    Iterator<Film> it1 = result.iterator();
    Iterator<Film> it2 = user.getMyMovies().iterator();

    assertEquals(it2.next().getName(), it1.next().getName());
    assertEquals(it2.next().getGenre(), it1.next().getGenre());
    assertEquals(it2.next().getRating(), it1.next().getRating());
  }
}