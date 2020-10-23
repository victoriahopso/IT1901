package mymovies.core;

import java.util.ArrayList;
import java.util.Collection;

public class User {

    protected String userName; 
    protected String password;
    protected Collection<Film> myMovies; 

    public User(String userName, String password){
        if (validUserName(userName) && validPassword(password)){
            this.userName = userName;
            this.password = password; 
            myMovies = new ArrayList<>();
        }
    }
    
    public Collection<Film> getMyMovies(){ 
        Collection<Film> copy = new ArrayList<>();
        myMovies.forEach(p -> copy.add(p));
        return copy;
    }

    public void addMovie(Film film){
        if (!myMovies.contains(film)){
            myMovies.add(film);
        }
    }

    private boolean validUserName(String userName){
       return userName.length()>=2;
    }

    private boolean validPassword(String password){
        return password.length()>=8;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getPassword(){
        return this.password;
    }













}