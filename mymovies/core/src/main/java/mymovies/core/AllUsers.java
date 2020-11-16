package mymovies.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class AllUsers implements Iterable<User> {

  private Collection<User> users = new ArrayList<>();

  /**
   * Legger til en bruker i objektet så fremt den 
   * ikke allerede er inneholdt i objektet, eller 
   * at det finnes en annen bruker med samme brukernavn. 

   * @param user    brukeren som skal legges til
   * @return    null
   */
  public User addUser(User user) {
    if (!users.contains(user)) {
      int counter = 0;
      for (User us : users) {
        if (!us.getUserName().equals(user.getUserName())) {
          counter++;
        }
      }
      if (counter == users.size()) {
        users.add(user);
        return user;
      }
    }
    return null;
  }

  /**
   * Oppdaterer brukerens mymovies attributt. 

   * @param user    brukeren som skal oppdateres
   */
  public void updateUser(User user) {
    for (User user2 : getAllUsers()) {
      if (user2.getUserName().equals(user.getUserName())) {
        user2.updateUser(user);
      }
    }
  }

  /**
   * Henter ut alle brukere inneholdt i dette objektet.

   * @return    en kopi av objektets users
   */
  public Collection<User> getAllUsers() {
    Collection<User> copy = new ArrayList<>();
    for (User u : users) {
      copy.add(u);
    }
    return copy;
  }

  /**
   * Henter ut en bruker på parameterene.

   * @param name    brukernavn
   * @param pass    brukernavn
   * @return    brukeren, hvis den eksisterer. Null hvis ikke. 
   */
  public User getUser(String name, String pass) {
    for (User user : users) {
      if (user.getUserName().equals(name) && user.getPassword().equals(pass)) {
        return user;
      }
    }
    return null;
  }


  /**
   * Vil hente ut en spesifik bruker basert på brukernavn.

   * @param username    brukernavn
   * @return    brukeren, hvis den eksisterer. Null hvis ikke. 
   */
  public User getUser(String username) {
    for (User user : users) {
      if (user.getUserName().equals(username)) {
        return user;
      }
    }
    return null;
  }

  /**
   * Sjekker om bruker med følgende parameter er 
   * en eksisterende bruker.

   * @param username    brukernavn 
   * @param password    passord
   * @return    True hvis brukeren er inneholdt i dette objektet.
   */
  public boolean isUser(String username, String password) {
    for (User user : users) {
      if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }

  public void setUsers(Collection<User> users) {
    this.users = users;
  }

  @Override
  public Iterator<User> iterator() {
    return users.iterator();
  }

}