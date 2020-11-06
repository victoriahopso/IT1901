package mymovies.restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mymovies.core.AllUsers;
import mymovies.core.User;

@RestController
@RequestMapping(AllUsersController.TODO_MODEL_SERVICE_PATH)
public class AllUsersController {

    public static final String TODO_MODEL_SERVICE_PATH = "restserver/movies";

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
    //@GetMapping(path = "/{username}")
    @RequestMapping(value = "/username", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@PathVariable("username") String username) {
        User user = getAllUsers().getUser(username);
        checkUser(user, username);
        return new ResponseEntity<>("Bruker hentet", HttpStatus.OK);
    }

    // Oppdaterer en allerede eksisterende bruker
    //@PutMapping(path = "/{username}")
    @RequestMapping(value = "/username", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUser(@PathVariable("username/update") String username, @RequestBody User user) {
        getAllUsers().getUser(username).updateUser(user);
        return new ResponseEntity<>("Bruker endret", HttpStatus.OK);
    }

    // Legger til en ny bruker
    //@PostMapping(path = "/{username}")
    @RequestMapping(value = "/username", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Object> addUser(@PathVariable("username") String username, @RequestBody User user) {
        getAllUsers().addUser(user);
        return new ResponseEntity<>("Bruker lagt til", HttpStatus.CREATED);
    }
}
