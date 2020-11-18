package mymovies.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import mymovies.core.ReadWrite;
import java.nio.file.Paths;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import mymovies.core.AllUsers;
import mymovies.core.Film;
import mymovies.core.User;

public class MoviesPersistenceTest {

  private UsersPersistence userPersistence = new UsersPersistence();
  private ReadWrite rw = new ReadWrite();

  private final static String pathStarter = "/workspace/gr2003/mymovies/core/src/test/resources/mymovies/json/";
  private final String userPath = Paths.get(pathStarter + "persistence-test.json").toString();

  @Test
  public void testSerializersDeserializers() {
    User user1 = new User("username1", "password1");
    User user2 = new User("username2", "password2");

    AllUsers allUsers = new AllUsers();
    AllUsers allUsersComparison = new AllUsers();

    allUsers.addUser(user1);
    allUsers.addUser(user2);

    Film film1 = new Film("Test", "Horror", 3);
    Film film2 = new Film("Test2", "Action", 1);

    user1.addMovie(film1);
    user2.addMovie(film2);

    userPersistence.write(allUsers, rw.createWriter(userPath));
    allUsersComparison = userPersistence.read(rw.createReader(userPath));

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
    assertEquals(user4.getUserName(), "username2"); 

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
    }
}