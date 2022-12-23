package ch.ffhs.sse.Controller;

import ch.ffhs.sse.Exception.NotFoundException;
import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * The LoginController represents the API endpoint for the login request.
 * This API can be consumed by any client application making http requests.
 *
 * The controller  makes use of the  user repository by utilizing the DTO
 * and performing all endpoint logic.
 * The controller generates an endpoint which is accessed via path /api/event
 * and accepts JSON objects as input parameters which then are converted to java objects.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    UserRepository userRepository;
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     *
     * The controller generates an endpoint which is accessed via path /api/event
     * and accepts JSON objects as input parameters which then are converted to java objects.
     * @param user object
     * @exception NotFoundException
     * @return object user
     */
    @PostMapping
    public Optional<User> findUserByUsernameAndPassword(@RequestBody Optional <User> user) throws NotFoundException {
        if(!user.isPresent()) throw new NotFoundException("User not found");
        String username = user.get().getUsername();
        String password = user.get().getPassword();
        User authUser = userRepository.findUserByUsernameAndPassword(username, password);
        return Optional.ofNullable(authUser);
    }
}
