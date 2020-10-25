package mymovies.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import mymovies.core.AllUsers;
import mymovies.core.Film;
import mymovies.core.RW;
import mymovies.core.User;

public class MoviesPersistenceTest {

    private UsersPersistence userPersistence = new UsersPersistence();
    private RW rw = new RW();

    @Test
    public void testSerializersDeserializers() {
        User user1 = new User("username1","password1");
        User user2 = new User("username2","password2");

        AllUsers allUsers = new AllUsers();
        AllUsers allUsersComparison = new AllUsers();

        allUsers.addUser(user1);
        allUsers.addUser(user2);

        Film film1 = new Film("Test", "Horror", 3);
        Film film2 = new Film("Test2", "Action", 1);

        user1.addMovie(film1);
        user2.addMovie(film2);

        userPersistence.write(allUsers, rw.createWriter("./mymovies.json"));
        allUsersComparison = userPersistence.read(rw.createReader("./mymovies.json"));

        Iterator<User> it = allUsers.iterator();
        Iterator<User> it1 = allUsersComparison.iterator();
        assertTrue(it.hasNext());
        assertTrue(allUsersComparison.iterator().hasNext());
        User user3 = it.next();
        User user4 = it.next();
        Iterator<Film> it2 = user3.getMyMovies().iterator();
        Iterator<Film> it3 = user4.getMyMovies().iterator();
        
        assertTrue(it1.hasNext());
        assertEquals(user3.getUserName(), it1.next().getUserName());
        assertEquals(user4.getUserName(), "username2"); //allUsersComparison.getUser("username2", "password2").getUserName()

        Film film3 = it2.next();
        assertEquals(film3.getName(), "Test");
        assertEquals(film3.getGenre(), "Horror");
        assertEquals(film3.getRating(), 3);
        assertFalse(it2.hasNext());

        Film film4 = it3.next();
        assertEquals(film4.getName(), "Test2");
        assertEquals(film4.getGenre(), "Action");
        assertEquals(film4.getRating(), 1);
        assertFalse(it3.hasNext());





        //MyMovies myMovies = new MyMovies();
        //MyMovies myMovies2 = new MyMovies();
        //MyMovies myMovies3 = new MyMovies();
        //Film film1 = new Film("Test", "Horror", 3);
        //Film film2 = new Film("Test2", "Action", 1);
        //myMovies.addMovie(film1);
        //myMovies.addMovie(film2);
        //moviesPersistence.write(myMovies, rw.createWriter("./mymovies.json"));
        //myMovies2 = moviesPersistence.read(rw.createReader("./mymovies.json"));
        //assertTrue(myMovies.getFilmer().iterator().hasNext());
        //assertTrue(myMovies2.getFilmer().iterator().hasNext());
        //assertEquals(myMovies.getFilmer().iterator().next().getName(), myMovies2.getFilmer().iterator().next().getName());
        //assertEquals(myMovies.getFilmer().iterator().next().getGenre(), myMovies2.getFilmer().iterator().next().getGenre());
        //assertEquals(myMovies.getFilmer().iterator().next().getRating(), myMovies2.getFilmer().iterator().next().getRating());
            //moviesPersistence.write(myMovies3, writer);
            //InputStream inputStream2 = new FileInputStream("mymovies.json"); 				
            //Reader reader2 = new InputStreamReader(inputStream2, "UTF-8");
            //myMovies3 = moviesPersistence.read(reader2);
            //assertFalse(myMovies3.getFilmer().iterator().hasNext());
    }
}