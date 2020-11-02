package mymovies.ui;

import mymovies.core.AllUsers;
import mymovies.core.RW;
import mymovies.core.User;
import mymovies.json.UsersPersistence;

/**
 * Class that centralizes access to a Mymovies. Makes it easier to support
 * transparent use of a REST API.
 */
public class DirectUserAccess implements MyMoviesAccess {

    private final AllUsers allUsers;
    private boolean update = false;
    private String userPath;
    private UsersPersistence persistence = null;
    private RW rw = new RW();

    public DirectUserAccess(AllUsers allUsers) {
        this.allUsers = allUsers;
    }

    @Override
    public boolean isUser(String username, String password) {
        return allUsers.isUser(username, password);
    }

    @Override
    public User getUser(String username) {
        return allUsers.getUser(username);
    }

    @Override
    public void addNewUser(User user) {
        allUsers.addUser(user);

    }

    @Override
    public void notify(User user) {
        if (update) {
            updateAllUsers();
        }
    }

    public void setUserPath(String path) {
        this.userPath = path;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public void updateAllUsers() {
        if (userPath != null) {
            if (persistence == null) {
                persistence = new UsersPersistence();
            }
            persistence.write(allUsers, rw.createWriter(userPath));
        }
    }
}
