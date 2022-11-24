package ch.ffhs.sse.Controller;

import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.EventRepository;
import ch.ffhs.sse.Repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    UserRepository userRepository;
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping
    public boolean loginUser(@RequestBody User user) {
        System.out.println(user.toString());
        String username = user.getUsername();
        System.out.println(username);
        String password = user.getPassword();
        System.out.println(password);
        List list = userRepository.findUserWithParams(username, password);
        if(!list.isEmpty()) return true;
        return false;
    }

}
