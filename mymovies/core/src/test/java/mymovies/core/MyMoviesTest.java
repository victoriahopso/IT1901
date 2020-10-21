package mymovies.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyMoviesTest {

    private static MyMovies mymovs;
    private static Film film1, film2;

    @BeforeAll
    public static void testDefaultConstructor(){
        mymovs = new MyMovies();
        assertNotNull(mymovs.getFilmer());
        film1 = new Film("film1", "genre1",1);
        film2 = new Film("film2","genre2",2);
    }

    @BeforeEach
    public void setUp(){
        mymovs.addMovie(film1);
        mymovs.addMovie(film2);
    }

    @Test
    public void testAddMovie(){
        assertEquals(2, mymovs.getFilmer().size());
        mymovs.addMovie(film2);
        assertEquals(2, mymovs.getFilmer().size());
    }

}