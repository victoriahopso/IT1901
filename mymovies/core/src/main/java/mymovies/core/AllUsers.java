package mymovies.core;

import java.util.ArrayList;
import java.util.Collection;

public class AllUsers{

    /**
     * Det skal kun eksistere én av denne klassen. 
     * Den skal lagres i skyen.
     * Den holder styr på brukere.
     * En kan ikke opprette en bruker med samme brukernavn som en av 
     * brukerene i denne klassen. 
     */

     
    private Collection<User> users = new ArrayList<>();

    public void addUser(User user){
        if (!users.contains(user)){
            int counter = 0;
            for (User us : users){
                if (!us.equals(user)){
                    counter++;
                }
            }
            if (counter == users.size()){
                users.add(user);
            }
        }
    }

    public Collection<User> getAllUsers(){
        return users; 
    }

    public User getUser(String name, String pass){
        for (User user : users){
            if (user.getUserName().equals(name) && user.getPassword().equals(pass)){
                return user;
            }
        }
        return null;
    }

}