package mymovies.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import mymovies.core.AllUsers;
import mymovies.core.User;
import mymovies.json.UsersModule;

public class RemoteUserAccess implements UserAccess {

  private AllUsers allUsers;
  private static final URI uri = URI.create("http://localhost:8080/restserver/movies/");
  private ObjectMapper objectMapper;
  // RW rw = new RW();

  public RemoteUserAccess() {
    this.objectMapper = new ObjectMapper().registerModule(new UsersModule());
  }

  @Override
    public boolean isUser(String username, String password) {
    if (getAllUsers().getUser(username, password) != null) {
      return true;
    }
    return false;
  }

  private String param(String s) {
    return URLEncoder.encode(s, StandardCharsets.UTF_8);
  }

  private URI uri(String name) {
    return uri.resolve(param(name));
  }

  private AllUsers getAllUsers() {
    if (allUsers == null) {
      HttpRequest request = HttpRequest.newBuilder(uri).header("Accept", "application/json").GET().build();
      try {
        final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                HttpResponse.BodyHandlers.ofString());
        final String responseString = response.body();
        this.allUsers = objectMapper.readValue(responseString, AllUsers.class);
        System.out.println("AllUsers: " + this.allUsers);
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    return allUsers;
  }

  @Override
    public User getUser(String username) {
    User user = this.allUsers.getUser(username);
    if (user == null) {
      HttpRequest request = HttpRequest.newBuilder(uri(username)).header("Accept", "application/json").GET()
              .build();
      try {
        final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                HttpResponse.BodyHandlers.ofString());
        String responseString = response.body();
        System.out.println("getUser(" + username + ") response: " + responseString);
        User secondUser = objectMapper.readValue(responseString, User.class);
        User thirdUser = new User(secondUser.getUserName(), secondUser.getPassword());
        thirdUser.setMyMovies(secondUser.getMyMovies());
        secondUser = thirdUser;
        this.allUsers.addUser(secondUser);

      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    return user;
  }

  @Override
    public void addUser(User user) {
    try {
            // Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // String string = gson.toJson(user);
      String string = objectMapper.writeValueAsString(user);
      System.out.println("User: " + string);
      HttpRequest request = HttpRequest.newBuilder(uri(user.getUserName())).header("Accept", "application/json")
              .header("Content-Type", "application/json").POST(BodyPublishers.ofString(string)).build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
              HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      allUsers.addUser(user);
      // objectMapper.writeValue(rw.createWriter("allusers.json"), allUsers);
      // Boolean added = objectMapper.readValue(responseString, Boolean.class);
      // if (added != null) {
      // allUsers.addUser(user);
      // }
      System.out.println(getAllUsers().getUser(user.getUserName()));
      System.out.println(responseString);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
    public void updateUser(User user) {
    try {
      String string = objectMapper.writeValueAsString(user);
      System.out.println("User: " + string);
      HttpRequest request = HttpRequest.newBuilder(uri(user.getUserName())).header("Accept", "application/json")
                .header("Content-Type", "application/json").PUT(BodyPublishers.ofString(string)).build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      allUsers.updateUser(user);
      // allUsers.updateUser(user);
      // objectMapper.writeValue(rw.createWriter("allusers.json"), allUsers);
      // Boolean added = objectMapper.readValue(responseString, Boolean.class);
      // if (added != null) {
      // allUsers.addUser(user);
      // }
      System.out.println(responseString);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
    public boolean usernameTaken(String username) {
    if (getAllUsers().getUser(username) == null) {
      return false;
    } 
    else {
      return true;
    }
  }

}
