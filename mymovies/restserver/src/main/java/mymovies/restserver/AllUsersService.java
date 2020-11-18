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

  protected String getFileName() {
    return "allusers.json";
  }

  public AllUsersService(AllUsers allUsers) {
    this.allUsers = allUsers;
  }

  public AllUsersService() {
    this(null);
  }

  public AllUsers getAllUsers() {
    if (allUsers == null) {
      allUsers = firstAllUsers();
    }
    return allUsers;
  }

  public void setAllUsers(AllUsers allUsers) {
    this.allUsers = allUsers;
  }

  /**
   * Prøver å lese et allUsers-objekt fra eksisterende fil.
   * Hvis ikke dette går, opprettes et nytt allUsersobjekt.

   * @return    allusers-objekt
   */
  private AllUsers firstAllUsers() {
    URL url = AllUsersService.class.getResource(getFileName());
    if (url != null) {
      try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
        return objectMapper.readValue(reader, AllUsers.class);

      } catch (IOException e) {
        System.out.println("Klarte ikke å åpne " + getFileName() 
            + ", dermed skjer dette manuelt(" + e + ")");
      }
    }
    AllUsers allUsers = new AllUsers();
    return allUsers;
  }
}