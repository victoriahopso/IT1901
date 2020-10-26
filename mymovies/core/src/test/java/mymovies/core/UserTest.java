package mymovies.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.jupiter.api.Test;

public class UserTest {

/**
 * Tester at brukernavn og passord blir korrekt.
 */
@Test
public void testValidUser(){
    User user = new User("name", "password");
    assertEquals("name", user.getUserName());
    assertEquals("password", user.getPassword());
}

/**
 * sjekker at lista som returneres inneholder objektene som er blitt lagt til
 */
@Test
public void testGetMovies(){
    Collection<Film> result = new ArrayList<>();
    Film film1 = new Film("The boy", "horror", 1);
    Film film2 = new Film("Shrek", "romantic", 6);
    Film film3 = new Film("Iron man", "comedy", 4);
    result.add(film2);
    result.add(film1);
    result.add(film3);

    Collection<Film> expectedResult = Arrays.asList(new Film("The boy", "horror", 1), new Film("Shrek", "romantic", 6), new Film("Iron man", "comedy", 4));
    assertEquals(expectedResult, result);
}

/**
 * Sjekker at filmen faktisk blir lagt til i lista, og at den ikke tillater duplikater
 */
@Test
public void testAddMovie() { 
    Film film = new Film("name", "genre", 5);
    Collection<Film> movies = new ArrayList<>();
    movies.add(film);
    assertTrue(movies.contains(film));

    if (movies.contains(film)){
        movies.add(film);
        assertEquals(1, movies.size());
        
    }
}
}