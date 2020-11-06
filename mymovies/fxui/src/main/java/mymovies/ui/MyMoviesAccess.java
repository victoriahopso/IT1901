package mymovies.ui;

import mymovies.core.User;

public interface MyMoviesAccess {

    boolean isUser(String username, String password);
    // Metoden returnerer True dersom User(username, password) eksisterer på
    // serveren

    User getUser(String username);
    // Metoden returnerer User(name, password)-objektet fra serveren.

    void addNewUser(User user);
    // Metoden legger til denne brukeren på serveren

    void notify(User user);
    //Oppdaterer brukerne dersom allUsers er endret
}
