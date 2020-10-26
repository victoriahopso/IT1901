package mymovies.ui;

import mymovies.core.User;

/**
 * Class that centralizes access to a Mymovies. Makes it easier to support
 * transparent use of a REST API.
 */
public class DirectUserAccess implements MyMoviesAccess {

    @Override
    public boolean isUser(String username, String password) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean userNameTaken(String username) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public User getUser(String name, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addNewUser(User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateUsersMovies(User user) {
        // TODO Auto-generated method stub

    }
    

}
