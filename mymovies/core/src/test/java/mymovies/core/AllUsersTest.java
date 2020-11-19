package mymovies.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Iterator;
import org.junit.jupiter.api.Test;

public class AllUsersTest {

  @Test
  public void testAddUser() {
    AllUsers all = new AllUsers();
    User user1 = new User("name1", "password1");
    User user2 = new User("name1", "password2");
    all.addUser(user1);
    assertNull(all.addUser(user2));
    Iterator<User> it = all.getAllUsers().iterator();

    assertTrue(it.hasNext());
    User testUser = it.next();
    assertEquals("name1", testUser.getUserName());
    assertEquals("password1", testUser.getPassword());
  }

  @Test
  public void testUpdateUser() {
    AllUsers all = new AllUsers();
    User user1 = new User("name", "password1");
    User user3 = new User("name1", "password3");
    User user2 = new User("name", "password2");
    all.addUser(user3);
    all.addUser(user1);
    all.updateUser(user2);
    Iterator<User> it = all.getAllUsers().iterator();
    it.next();
    User testUser = it.next();

    assertEquals("name", testUser.getUserName());
    assertEquals("password2", testUser.getPassword());
  }

  @Test
  public void testGetAllUsers() {
    AllUsers all = new AllUsers();
    User user1 = new User("name1", "password1");
    User user2 = new User("name2", "password2");
    all.addUser(user1);
    all.addUser(user2);

    Iterator<User> it = all.getAllUsers().iterator();
    assertTrue(it.hasNext());
    User testUser1 = it.next();
    assertTrue(it.hasNext());
    User testUser2 = it.next();

    assertEquals("name1", testUser1.getUserName());
    assertEquals("password1", testUser1.getPassword());
    assertEquals("name2", testUser2.getUserName());
    assertEquals("password2", testUser2.getPassword());
  }

  @Test
  public void testGetUserUsernameAndPassword() {
    AllUsers all = new AllUsers();
    User user1 = new User("name1", "password1");
    User user2 = new User("name2", "password2");
    all.addUser(user2);
    all.addUser(user1);
    assertEquals(user1, all.getUser("name1", "password1"));
    assertNull(all.getUser("name3", "password3"));
  }

  @Test
  public void testGetUserUsername() {

    AllUsers all = new AllUsers();
    User user1 = new User("name1", "password1");
    all.addUser(user1);

    assertEquals(user1, all.getUser("name1"));
    assertNull(all.getUser("name2"));
  }

  @Test
  public void testIsUser() {
    AllUsers all = new AllUsers();
    User user1 = new User("name1", "password1");
    User user2 = new User("name2", "password2");
    all.addUser(user1);
    all.addUser(user2);
    User user3 = new User("name2", "password2");
    User user4 = new User("name4", "password4");

    assertTrue(all.isUser(user3.getUserName(), user3.getPassword()));
    assertFalse(all.isUser(user4.getUserName(), user4.getPassword()));
  }

  @Test
  public void testIterator() {
    AllUsers all = new AllUsers();
    User user1 = new User("name1", "password1");
    User user2 = new User("name2", "password2");
    all.addUser(user1);
    all.addUser(user2);
    Iterator<User> it = all.iterator();
    assertTrue(it.hasNext());
    User testUser1 = it.next();

    assertEquals("name1", testUser1.getUserName());
    assertEquals("password1", testUser1.getPassword());
    assertTrue(it.hasNext());
    User testUser2 = it.next();
    assertEquals("name2", testUser2.getUserName());
    assertEquals("password2", testUser2.getPassword());
  }
}
