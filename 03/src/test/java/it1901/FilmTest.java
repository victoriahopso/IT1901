package it1901;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FilmTest{
    
@Test
public void testFilm() {
    Film test = new Film("testname", "genre", 5 , "comment");
    assertEquals("testname" , test.getName());
    assertEquals("genre", test.getGenre());
    assertEquals(5, test.getRating());
    assertEquals("comment", test.getComment());
    assertEquals("testname genre 5 comment" , test.toString());
    Film test2 = new Film("test","genre",1,"");
    assertEquals("test genre 1 ", test2.toString());
}

}