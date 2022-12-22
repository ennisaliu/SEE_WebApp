package ch.ffhs.sse.Controller;

import ch.ffhs.sse.Exception.NotFoundException;
import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://65.108.88.203:8080", allowedHeaders = "*")
public class LoginController {

    UserRepository userRepository;
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public Optional<User> findUserByUsernameAndPassword(@RequestBody Optional <User> user) throws NotFoundException {
        if(!user.isPresent()) throw new NotFoundException("User not found");
        String username = user.get().getUsername();
        String password = user.get().getPassword();
        User authUser = userRepository.findUserByUsernameAndPassword(username, password);
        return Optional.ofNullable(authUser);
    }
}
