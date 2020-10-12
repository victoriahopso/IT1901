package mymovies.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import org.junit.jupiter.api.Test;
import mymovies.core.Film;
import mymovies.core.MyMovies;
public class MoviesPersistenceTest {
    private MoviesPersistence moviesPersistence = new MoviesPersistence();
    @Test
    public void testSerializersDeserializers() {
        MyMovies myMovies = new MyMovies();
        MyMovies myMovies2 = new MyMovies();
        //MyMovies myMovies3 = new MyMovies();
        Film film1 = new Film("Test", "Horror", 3);
        Film film2 = new Film("Test2", "Action", 1);
        myMovies.addMovie(film1);
        myMovies.addMovie(film2);
        try {
            FileOutputStream fileStream = new FileOutputStream("mymovies.json");
            OutputStreamWriter writer = new OutputStreamWriter(fileStream, "UTF-8");
            moviesPersistence.write(myMovies, writer);
            InputStream inputStream = new FileInputStream("mymovies.json"); 				
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            myMovies2 = moviesPersistence.read(reader);
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
        catch (IOException e) {
            fail();
        }
    }
}