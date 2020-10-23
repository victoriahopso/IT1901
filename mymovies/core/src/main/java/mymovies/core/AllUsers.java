package mymovies.core;

import java.util.HashMap;

public class AllUsers {

    /**
     * Det skal kun eksistere én av denne klassen. 
     * Den skal lagres i skyen.
     * Den holder styr på brukere.
     * En kan ikke opprette en bruker med samme brukernavn som en av 
     * brukerene i denne klassen. 
     */

     
    private HashMap<String, String> users = new HashMap<>();

    public boolean containsUser(User user){
        return users.containsKey(user.getUserName());
    }

    public boolean correctLogin(String name, String pass){
        if (users.containsKey(name)){
            return users.get(name).equals(pass);
        }
        return false; 
    }

    public void add(User user){
        users.put(user.getUserName(), user.getPassword());
    }




}