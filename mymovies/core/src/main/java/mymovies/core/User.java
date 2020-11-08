package mymovies.core;

import java.util.ArrayList;
import java.util.Collection;

public class User {

    protected String userName;
    protected String password;
    protected Collection<Film> myMovies = new ArrayList<>();
    
    public User() {

    }

    public User(String userName, String password) {
        if (validUserName(userName) && validPassword(password)) {
            this.userName = userName;
            this.password = password;
            myMovies = new ArrayList<>();
        }
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
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    private boolean validPassword(String password) { //Kan ikke sjekke dette her, uten å sende feilmelding?!
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