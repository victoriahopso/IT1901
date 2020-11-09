package mymovies.restserver;

import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.stereotype.Service;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import mymovies.core.AllUsers;
import mymovies.json.AllUsersDeserializer;
import mymovies.json.UsersModule;
import mymovies.json.UsersPersistence;

@Service
public class AllUsersService {

    private AllUsers allUsers;
    private static ObjectMapper objectMapper = new ObjectMapper().registerModule(new UsersModule());

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
        //UsersPersistence usersPersistence = new UsersPersistence();
        URL url = AllUsersService.class.getResource("allusers.json");
        if (url != null) {
            try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
                return objectMapper.readValue(reader, AllUsers.class);
                //return new Gson().fromJson(reader, new TypeToken<AllUsers>(){}.getType());

                //return usersPersistence.read(reader);
            } catch (IOException e) {
                System.out.println("Klarte ikke å åpne allusers.json, dermed skjer dette manuelt(" + e + ")");
            }
        }
        //Lag fil
        AllUsers allUsers = new AllUsers();
        return allUsers;
    }
}