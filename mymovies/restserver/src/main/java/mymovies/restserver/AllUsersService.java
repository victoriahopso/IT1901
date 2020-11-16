package mymovies.restserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import mymovies.core.AllUsers;
import mymovies.json.UsersModule;
import org.springframework.stereotype.Service;

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
    URL url = AllUsersService.class.getResource("allusers.json");
    if (url != null) {
      try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
        return objectMapper.readValue(reader, AllUsers.class);

      } catch (IOException e) {
        System.out.println(
        "Klarte ikke å åpne allusers.json, dermed skjer dette manuelt(" + e + ")");
      }
    }
    AllUsers allUsers = new AllUsers();
    return allUsers;
  }
}