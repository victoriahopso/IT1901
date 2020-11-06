package mymovies.restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    private void checkUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Ingen bruker ved navn: \"" + user.getUserName() + "\"");
        }
    }

    // Hente ut bruker med "username"
    @GetMapping(path = "/{username}")
    public User getTodoList(@PathVariable("username") String username) {
        User user = getAllUsers().getUser(username);
        checkUser(user);
        return user;
    }

    // Legger til eller skriver over en user
    @PutMapping(path = "/{username}")
    public boolean addUser(@PathVariable("username") String username, @RequestBody User user) {
        boolean replaced = getAllUsers().getUser(username) != null;
        return replaced;
    }
}
