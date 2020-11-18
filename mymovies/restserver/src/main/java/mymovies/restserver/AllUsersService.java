package mymovies.restserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

import mymovies.core.AllUsers;
import mymovies.json.UsersModule;

@Service
public class AllUsersService {

  private AllUsers allUsers;
  private static ObjectMapper objectMapper = new ObjectMapper().registerModule(new UsersModule());

  protected String getFileName(){
    return "allusers.json";
  }

  public AllUsersService(AllUsers allUsers) {
    this.allUsers = allUsers;
  }

  public AllUsersService() {
    this(null);
  }

  public AllUsers getAllUsers() {
    //return firstAllUsers();
    if (allUsers == null){
      allUsers = firstAllUsers();
    }
    return allUsers;
    
  }

  public void setAllUsers(AllUsers allUsers) {
    this.allUsers = allUsers;
  }

  private AllUsers firstAllUsers() {
    URL url = AllUsersService.class.getResource(getFileName());
    if (url != null) {
      try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
        return objectMapper.readValue(reader, AllUsers.class);

      } catch (IOException e) {
        System.out.println("Klarte ikke å åpne allusers.json, dermed skjer dette manuelt(" + e + ")");
      }
    }
    // Evt lag fil?
    AllUsers allUsers = new AllUsers();
    return allUsers;
  }
}