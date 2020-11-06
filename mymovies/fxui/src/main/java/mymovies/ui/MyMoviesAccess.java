package mymovies.ui;

import mymovies.core.User;

public interface MyMoviesAccess {

    boolean isUser(String username, String password);
    // Metoden returnerer True dersom User(username, password) eksisterer på
    // serveren

    User getUser(String username);
    // Metoden returnerer User(name, password)-objektet fra serveren.

    void addUser(User user);
    // Metoden legger til denne brukeren på serveren

    void updateUser(User user);
    //Oppdaterer brukeren

    boolean usernameTaken(String username);
    //Sjekker om brukernavnet er i bruk
}
