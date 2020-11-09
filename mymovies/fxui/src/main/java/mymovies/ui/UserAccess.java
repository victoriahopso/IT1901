package mymovies.ui;

import mymovies.core.User;

/*
 * Grensesnitt som deklarerer metodene/tjenestene som
 * frontend behøver fra backend. 
 */

public interface UserAccess {

    public boolean isUser(String username, String password);
    public User getUser(String username);
    public void addUser(User user);
    public void updateUser(User user);
    public boolean usernameTaken(String username);  
}