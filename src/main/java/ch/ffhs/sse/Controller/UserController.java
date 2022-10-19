package ch.ffhs.sse.Controller;

import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.UserRepository;
import org.apache.coyote.Request;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Inside the Controller we insert our business logic
@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PutMapping(value = "getUser/{id}")
    public User getUserById(@PathVariable Long id, @RequestBody User user) {
        user = userRepository.findById(id).get();
        return user;
    }

    @GetMapping(value = "/getUserMail/{email}")
    public User getUserByEmail(@PathVariable String email, @RequestBody User user) {
        user = userRepository.findByEmail(email);
        return user;
    }

    @PostMapping(value ="/save")
    public String saveUser(@RequestBody User user) {
        userRepository.save(user);
        return "User saved successfully.";
    }

    @PutMapping(value = "update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updatedUser = userRepository.findById(id).get();
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        userRepository.save(updatedUser);
        return "User: " + user.getFirstName() + " " + user.getLastName() + " was updated successfully.";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        User deleteUser = userRepository.findById(id).get();
        // Store name of user before deleting for String return
        String deletedFirstName = deleteUser.getFirstName();
        String deletedLastName = deleteUser.getLastName();
        userRepository.delete(deleteUser);
        return "User " + deletedFirstName + " " + deletedLastName + " was deleted successfully.";
    }
}
