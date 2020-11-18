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
    assertEquals(testUser.getUserName(), "name1");
    assertEquals(testUser.getPassword(), "password1");
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

    assertEquals(testUser.getUserName(), "name");
    assertEquals(testUser.getPassword(), "password2");
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

    assertEquals(testUser1.getUserName(), "name1");
    assertEquals(testUser1.getPassword(), "password1");
    assertEquals(testUser2.getUserName(), "name2");
    assertEquals(testUser2.getPassword(), "password2");
  }

  @Test
  public void testGetUserUsernameAndPassword() {
    AllUsers all = new AllUsers();
    User user1 = new User("name1", "password1");
    User user2 = new User("name2", "password2");
    all.addUser(user2);
    all.addUser(user1);
    assertEquals(all.getUser("name1", "password1"), user1);
    assertNull(all.getUser("name3", "password3"));
  }

  @Test
  public void testGetUserUsername() {

    AllUsers all = new AllUsers();
    User user1 = new User("name1", "password1");
    all.addUser(user1);

    assertEquals(all.getUser("name1"), user1);
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

    assertEquals(testUser1.getUserName(), "name1");
    assertEquals(testUser1.getPassword(), "password1");
    assertTrue(it.hasNext());
    User testUser2 = it.next();
    assertEquals(testUser2.getUserName(), "name2");
    assertEquals(testUser2.getPassword(), "password2");
  }
}
