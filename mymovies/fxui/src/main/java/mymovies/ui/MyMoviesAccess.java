package mymovies.ui;

import mymovies.core.User;

/**
 * Class that centralizes access to a User. Makes it easier to support
 * transparent use of a REST API.
 */
public interface MyMoviesAccess {

    boolean isUser(String username, String password);
    // Metoden returnerer True dersom User(username, password) eksisterer på
    // serveren

    boolean userNameTaken(String username);
    // Metoden returnerer True dersom brukernavnet eksisterer på serveren

    User getUser(String name, String password);
    // Metoden returnerer User(name, password)-objektet fra serveren.

    void addNewUser(User user);
    // Metoden legger til denne brukeren på serveren

    void updateUsersMovies(User user);
    // Når bruker registrerer en film må skyen oppdateres. Kalles ved #onAction
    // handle submit i hoved-GUI.
}
