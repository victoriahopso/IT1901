package mymovies.core;

import java.util.ArrayList;
import java.util.Collection;

public class User {

  protected String userName;
  protected String password;
  protected Collection<Film> myMovies = new ArrayList<>();

  public User(String userName, String password) {
    if (validUserName(userName) && validPassword(password)) {
      this.userName = userName;
      this.password = password;
      myMovies = new ArrayList<>();
    }
  }

  @Override
  public String toString() {
    return ("User: " + userName + ", password: " + password);
  }

  public void updateUser(User user) {
    this.userName = user.getUserName();
    this.password = user.getPassword();
    this.myMovies = user.getMyMovies();
  }

  public Collection<Film> getMyMovies() {
    Collection<Film> copy = new ArrayList<>();
    myMovies.forEach(p -> copy.add(p));
    return copy;
  }

  public void setMyMovies(Collection<Film> col) {
    this.myMovies = col;
  }

  public void addMovie(Film film) {
    if (!myMovies.contains(film)) {
      myMovies.add(film);
    }
  }

  private boolean validUserName(String userName) {
    return userName.length() >= 2;
  }
  
  private boolean validPassword(String password) {
    return password.length() >= 8;
  }

  public String getUserName() {
    return this.userName;
  }

  public String getPassword() {
    return this.password;
  }

  public void removeMovie(Film film) {
    if (myMovies.contains(film)) {
      myMovies.remove(film);
    }
  }
}