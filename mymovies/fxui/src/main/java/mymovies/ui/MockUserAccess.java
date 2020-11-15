package mymovies.ui;

import mymovies.core.AllUsers;
import mymovies.core.User;

/*
 * Denne klassen er en slags "mock" av serveren som brukes 
 * under testing av GUI. 
 */

public class MockUserAccess implements UserAccess {

  AllUsers allUsers = new AllUsers();

  @Override
  public boolean isUser(String username, String password) {
    return allUsers.getUser(username, password) != null;
  }

  @Override
    public User getUser(String username) {
    return allUsers.getUser(username);
  }

  @Override
  public void addUser(User user) {
    allUsers.addUser(user);
  }

  @Override
    public void updateUser(User user) {
    allUsers.addUser(user);
  }

  @Override
    public boolean usernameTaken(String username) {
    for (User user : allUsers) {
      if (user.getUserName().equals(username)) {
        return true;
      }
    }
    return false;
  }
}