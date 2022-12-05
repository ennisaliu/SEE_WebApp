package ch.ffhs.sse.Controller;

import ch.ffhs.sse.Exception.NotFoundException;
import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.UserRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
