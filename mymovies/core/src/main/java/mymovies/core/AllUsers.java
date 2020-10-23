package mymovies.core;

import java.util.ArrayList;
import java.util.Collection;

public class AllUsers {

    /**
     * Det skal kun eksistere én av denne klassen. 
     * Den skal lagres i skyen.
     * Den holder styr på brukere.
     * En kan ikke opprette en bruker med samme brukernavn som en av 
     * brukerene i denne klassen. 
     */

     
    private Collection<User> users = new ArrayList<User>();

    public boolean containsUser(User user){
        return users.contains(user);
    }

    public boolean correctLogin(String name, String pass){
        for (User us : users) {
            if (us.getUserName() == name) {
                if (us.getPassword() == pass) {
                    return true;
                }
            }
        }
        return false;
    }

    public void add(User user){
        users.add(user);
    }

    public Collection<User> getUsers() {
        return this.users;
    }


}