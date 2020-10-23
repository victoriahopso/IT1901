package mymovies.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import mymovies.core.Film;
import mymovies.core.MyMovies;
import mymovies.core.RW;

public class MoviesPersistenceTest {

    private UsersPersistence moviesPersistence = new UsersPersistence();
    private RW rw = new RW();

    @Test
    public void testSerializersDeserializers() {
        MyMovies myMovies = new MyMovies();
        MyMovies myMovies2 = new MyMovies();
        //MyMovies myMovies3 = new MyMovies();
        Film film1 = new Film("Test", "Horror", 3);
        Film film2 = new Film("Test2", "Action", 1);
        myMovies.addMovie(film1);
        myMovies.addMovie(film2);
        moviesPersistence.write(myMovies, rw.createWriter("./mymovies.json"));
        myMovies2 = moviesPersistence.read(rw.createReader("./mymovies.json"));
        assertTrue(myMovies.getFilmer().iterator().hasNext());
        assertTrue(myMovies2.getFilmer().iterator().hasNext());
        assertEquals(myMovies.getFilmer().iterator().next().getName(), myMovies2.getFilmer().iterator().next().getName());
        assertEquals(myMovies.getFilmer().iterator().next().getGenre(), myMovies2.getFilmer().iterator().next().getGenre());
        assertEquals(myMovies.getFilmer().iterator().next().getRating(), myMovies2.getFilmer().iterator().next().getRating());
            //moviesPersistence.write(myMovies3, writer);
            //InputStream inputStream2 = new FileInputStream("mymovies.json"); 				
            //Reader reader2 = new InputStreamReader(inputStream2, "UTF-8");
            //myMovies3 = moviesPersistence.read(reader2);
            //assertFalse(myMovies3.getFilmer().iterator().hasNext());
    }
}