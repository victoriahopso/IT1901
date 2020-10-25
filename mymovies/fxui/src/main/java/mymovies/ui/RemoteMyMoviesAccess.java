package mymovies.ui;

import com.fasterxml.jackson.databind.ObjectMapper;

import mymovies.core.MyMovies;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class that centralizes access to a MyMovies. Makes it easier to support transparent use of a
 * REST API.
 */
public class RemoteMyMoviesAccess implements MyMoviesAccess {

  private final URI endpointBaseUri;

  private ObjectMapper objectMapper;

  private MyMovies myMovies;

  public RemoteMyMoviesAccess(URI endpointBaseUri) {
    this.endpointBaseUri = endpointBaseUri;
    objectMapper = new ObjectMapper().registerModule(new MoviesModule());
    //Hvorfor finner ikke denne MoviesModule i json?
  }

  private MyMovies getMyMovies() {
    if (myMovies == null) {
      HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
          .header("Accept", "application/json")
          .GET()
          .build();
      try {
        final HttpResponse<String> response =
            HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        final String responseString = response.body();
        this.myMovies = objectMapper.readValue(responseString, MyMovies.class);
        System.out.println("MyMovies: " + this.myMovies);
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    return myMovies;
  }

  /**
   * Checks that name is valid for a (new) TodoList
   *
   * @param name the (new) name
   * @return true if the name is value, false otherwise
   */
  /**
   * Gets the TodoList with the given name.
   *
   * @param name the TodoList's name
   * @return the TodoList with the given name
   */


 @Override
  public boolean isMyMovies(String name) {
      return getUser().validUserName(name);
      
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
      return getUser().hasMyMovies(name);
      //Mistenker at denne gjør det samme som forrige oppgave i praksis
  }

  /**
   * Gets the names of the Movies.
   *
   * @return the names of the Movies.
   */

  @Override
  public Collection<String> getAllMyMovies() {
      Collection<String> allMovies = new ArrayList<>();
      getUser().forEach(myMovies -> allMovies.add(myMovies.getName()));
    return allMovies;
  }

  private String uriParam(String s) {
    return URLEncoder.encode(s, StandardCharsets.UTF_8);
  }

  private URI myMoviesUri(String name) {
    return endpointBaseUri.resolve(uriParam(name));
  }

}
