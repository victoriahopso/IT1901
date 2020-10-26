package mymovies.ui;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import mymovies.core.MyMovies;


/**
 * Class that centralizes access to a Mymovies.
 * Makes it easier to support transparent use of a REST API.
 */
public class DirectMyMoviesAccess implements MyMoviesAccess {

    private final MyMovies myMovies;

    public DirectMyMoviesAccess(MyMovies myMovies) {
    this.myMovies=myMovies;
  }

   /**
   * Checks that name is valid for a (new) Mymovies
   *
   * @param name the (new) name
   * @return true if the name is value, false otherwise
   */

  @Override
  public boolean isMyMovies(String name) {
      //return user.validUserName(name);
      return false;
      
      //Kommenterte ut riktig kode for å fjerne rød strekker, retter opp snart.
  }

  /**
   * Checks if there (already) exists a MyMovies with the provided name
   *
   * @param name the (new) name
   * @return true if there exists a MyMovies with the provided name, false otherwise
   */

  @Override
  public boolean hasMovies(String name) {
      //return user.hasMyMovies(name);
      return false;
      //Kommenterte ut riktig kode for å fjerne rød strekker, retter opp snart. Mistenker at denne gjør det samme som forrige oppgave i praksis
  }

  /**
   * Gets the names of the Movies.
   *
   * @return the names of the Movies.
   */

  @Override
  public Collection<String> getAllMyMovies() {
      Collection<String> allMovies = new ArrayList<>();
      myMovies.forEach(myMovies -> allMovies.add(myMovies.getName()));
    return allMovies;
  }

}
