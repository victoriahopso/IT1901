package mymovies.ui;

import java.util.Collection;

/**
 * Class that centralizes access to a TodoModel.
 * Makes it easier to support transparent use of a REST API.
 */
public interface MyMoviesAccess {


  /**
   * Checks if there exists a MyMovies object
   *
   * @param mymovies the (new) name
   * @return true if it exists, false otherwise
   */
  public boolean isMyMovies(String mymovies);

  /**
   * Gets MyMovies
   *
   * @return every MyMovies
   */

   public boolean hasMovies(String name);

  /**
   * Gets the names of the movies.
   *
   * @return the names of the movies.
   */

  Collection<String> getAllMyMovies();

  /**
   * Adds a MyMovies or Film object to the underlying Model.
   */
}
