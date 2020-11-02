package mymovies.ui;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

import mymovies.core.AllUsers;
import mymovies.core.User;
import mymovies.json.UsersModule;

public class RemoteUserAccess implements MyMoviesAccess {

    private AllUsers allUsers;
    private final URI uri;
    private ObjectMapper objectMapper;

    public RemoteUserAccess(URI uri) {
        this.uri = uri;
        this.objectMapper = new ObjectMapper().registerModule(new UsersModule());
    }

    @Override
    public boolean isUser(String username, String password) {
        return getAllUsers().isUser(username, password);
    }

    private String param(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    private URI uri(String name) {
        return uri.resolve(param(name));
    }

    private AllUsers getAllUsers() {
        if (allUsers == null) {
            HttpRequest request = HttpRequest.newBuilder(this.uri).header("Accept", "application/json").GET().build();
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
        if (user == null || (!(user instanceof User))) {
            HttpRequest request = HttpRequest.newBuilder(uri(username)).header("Accept", "application/json").GET()
                    .build();
            try {
                final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                        HttpResponse.BodyHandlers.ofString());
                String responseString = response.body();
                System.out.println("getUser(" + username + ") response: " + responseString);
                User secondUser = objectMapper.readValue(responseString, User.class);
                if (!(secondUser instanceof User)) {
                    User thirdUser = new User(secondUser.getUserName(), secondUser.getPassword());
                    thirdUser.setMyMovies(secondUser.getMyMovies());
                    secondUser = thirdUser;
                }
                this.allUsers.addUser(secondUser);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return user;
    }

    @Override
    public void addNewUser(User user) {
        try {
            String string = objectMapper.writeValueAsString(user);
            HttpRequest request = HttpRequest.newBuilder(uri(user.getUserName()))
                    .header("Accept", "application/json").header("Content-Type", "application/json")
                    .PUT(BodyPublishers.ofString(string)).build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            Boolean added = objectMapper.readValue(responseString, Boolean.class);
            if (added != null) {
                allUsers.addUser(user);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void notify(User user) {
        addNewUser(user);
    }

}
