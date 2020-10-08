package mymovies.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FilmTest{
    
@Test
public void testFilm() {
    Film test = new Film("testname", "genre", 5);
    assertEquals("testname" , test.getName());
    assertEquals("genre", test.getGenre());
    assertEquals(5, test.getRating());
    assertEquals("Film: testname, Genre: genre, Rating: 5", test.toString());
    Film test2 = new Film("test","genre",1);
    assertEquals("Film: test, Genre: genre, Rating: 1", test2.toString());
}

}