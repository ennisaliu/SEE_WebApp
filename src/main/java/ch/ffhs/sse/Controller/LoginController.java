package ch.ffhs.sse.Controller;

import ch.ffhs.sse.Exception.UserNotFound;
import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.EventRepository;
import ch.ffhs.sse.Repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    UserRepository userRepository;
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User findUserByUsernameAndPassword(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        User authUser = userRepository.findUserByUsernameAndPassword(username, password);
        return authUser;
    }
}
