package mymovies.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class AllUsers implements Iterable<User> {

    /**
     * Det skal kun eksistere én av denne klassen. 
     * Den skal lagres i skyen.
     * Den holder styr på brukere.
     * En kan ikke opprette en bruker med samme brukernavn som en av 
     * brukerene i denne klassen. 
     */

     
    private Collection<User> users = new ArrayList<User>();

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

    public boolean contains(User user){
        return users.contains(user);
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }


}