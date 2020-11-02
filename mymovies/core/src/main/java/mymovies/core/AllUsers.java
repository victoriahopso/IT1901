package mymovies.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class AllUsers implements Iterable<User> {

    /**
     * Det skal kun eksistere én av denne klassen. Den skal lagres i skyen. Den
     * holder styr på brukere. En kan ikke opprette en bruker med samme brukernavn
     * som en av brukerene i denne klassen.
     */

    private Collection<User> users = new ArrayList<>();

    public void addUser(User user) {
        if (!users.contains(user)) {
            int counter = 0;
            for (User us : users) {
                if (!us.getUserName().equals(user.getUserName())) {
                    counter++;
                }
            }
            if (counter == users.size()) {
                users.add(user);
            }
        }
    }

    public Collection<User> getAllUsers() {
        Collection<User> copy = new ArrayList<>();
        for (User u : users) {
            copy.add(u);
        }
        return copy;
    }

    public User getUser(String name, String pass) {
        for (User user : users) {
            if (user.getUserName().equals(name) && user.getPassword().equals(pass)) {
                return user;
            }
        }
        return null;
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }

    public boolean isUser(String username, String password) {
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}