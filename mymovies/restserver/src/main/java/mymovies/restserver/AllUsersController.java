package mymovies.restserver;

import mymovies.core.AllUsers;
import mymovies.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restserver/mymovies")
public class AllUsersController {

  @Autowired
  private AllUsersService allUsersService;

  @GetMapping
  public AllUsers getAllUsers() {
    return allUsersService.getAllUsers();
  }

  private void checkUser(User user, String username) {
    if (user == null) {
      throw new IllegalArgumentException("Ingen bruker ved navn: \"" + username + "\"");
    }
  }

  /**
   * Henter ut en bruker med parameteret.

   * @param username brukernavn
   * @return User-objekt
   */
  @GetMapping(path = "/{username}")
  public User getUser(@PathVariable("username") String username) {
    User user = getAllUsers().getUser(username);
    checkUser(user, username);
    return user;
  }

  /**
   * Oppdaterer en allerede eksisterende bruker Brukes når 
   * User-objektets attributt myMovies er endret.

   * @param username brukernavn
   * @param user     user-objektet.
   * @return resopns
   */
  @PutMapping("/{username}")
  public ResponseEntity<Object> 
        updateUser(@PathVariable("username") String username, @RequestBody User user) {
    getAllUsers().getUser(username).updateUser(user);
    return new ResponseEntity<>("Bruker endret", HttpStatus.OK);
  }

  /**
   * Legger til en ny bruker.

   * @param username brukernavn
   * @param user     user-objekt
   * @return respons
   */
  @PostMapping(path = "/{username}")
  public ResponseEntity<Object> 
          addUser(@PathVariable("username") String username, @RequestBody User user) {
    getAllUsers().addUser(user);
    return new ResponseEntity<>("Bruker lagt til", HttpStatus.CREATED);
  }
}