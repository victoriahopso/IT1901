package mymovies.restserver;

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

import mymovies.core.AllUsers;
import mymovies.core.User;

@RestController
@RequestMapping("/restserver/movies")
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

  // Hente ut bruker med "username"
  // @RequestMapping(value = "/username", produces =
  // MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
  @GetMapping(path = "/{username}")
  public User getUser(@PathVariable("username") String username) {
    User user = getAllUsers().getUser(username);
    checkUser(user, username);
    return user;
  }

  // Oppdaterer en allerede eksisterende bruker
  // @RequestMapping(value = "/username", produces =
  // MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT, consumes =
  // MediaType.APPLICATION_JSON_VALUE)
  @PutMapping("/{username}")
  public ResponseEntity<Object> updateUser(@PathVariable("username") String username, @RequestBody User user) {
    System.out.println("PUT: " + user);
    getAllUsers().getUser(username).updateUser(user);
    return new ResponseEntity<>("Bruker endret", HttpStatus.OK);
  }

  // Legger til en ny bruker
  // @RequestMapping(value = "/username", produces =
  // MediaType.APPLICATION_JSON_VALUE, consumes =
  // MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  @PostMapping(path = "/{username}")
  public ResponseEntity<Object> addUser(@PathVariable("username") String username, @RequestBody User user) {
    System.out.println("POST: " + user);
    getAllUsers().addUser(user);
    return new ResponseEntity<>("Bruker lagt til", HttpStatus.CREATED);
  }
}
