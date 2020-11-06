package mymovies.restserver;

import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.stereotype.Service;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import mymovies.core.AllUsers;
import mymovies.json.UsersPersistence;

@Service
public class AllUsersService {

    private AllUsers allUsers;

    public AllUsersService(AllUsers allUsers) {
        this.allUsers = allUsers;
    }

    public AllUsersService() {
        this(firstAllUsers());
    }

    public AllUsers getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(AllUsers allUsers) {
        this.allUsers = allUsers;
    }

    private static AllUsers firstAllUsers() {
        UsersPersistence usersPersistence = new UsersPersistence();
        URL url = AllUsersService.class.getResource("first.json");
        if (url != null) {
            try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
                return usersPersistence.read(reader);
            } catch (IOException e) {
                System.out.println("Klarte ikke å åpne first.json, dermed skjer dette manuelt(" + e + ")");
            }
        }
        AllUsers allUsers = new AllUsers();
        return allUsers;
    }
}