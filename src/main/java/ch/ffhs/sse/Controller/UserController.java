package ch.ffhs.sse.Controller;

import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.EventRepository;
import ch.ffhs.sse.Repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://65.108.88.203:8080", allowedHeaders = "*")
public class UserController {
    UserRepository userRepository;
    EventRepository eventRepository;

    public UserController(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @PostMapping
    public String saveUser(@RequestBody User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User (" + user.getUsername() + ", ID: " + user.getUserId() + ") was saved.";
    }

    @PutMapping(value = "/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updatedUser = userRepository.findById(id).get();
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        userRepository.save(updatedUser);
        return "User (" + user.getUsername() + ", ID: " + user.getUserId() + ") was updated.";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable long id) {
        User deleteUser = userRepository.findById(id).get();
        String username = deleteUser.getUsername();
        long userId = deleteUser.getUserId();
        userRepository.delete(deleteUser);
        return "User (" + username + ", ID: " + userId + ") was deleted.";
    }

    @GetMapping(value = "/mail/{mail}")
    public User getUserByEmail(@PathVariable String mail) {
        User user = userRepository.findByEmail(mail);
        return user;
    }

}