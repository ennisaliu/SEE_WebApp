package ch.ffhs.sse.Controller;

import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.EventRepository;
import ch.ffhs.sse.Repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The UserController represents the API endpoint for all user requests.
 * This API can be consumed by any client application making http requests.
 *
 * The controller  makes use of the user repository by utilizing the DTO
 * and performing all endpoint logic.
 * The controller generates an endpoint which is accessed via path /api/user
 * and accepts JSON objects as input parameters which then are converted to java objects.
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://65.108.88.203:8080", allowedHeaders = "*")
public class UserController {
    UserRepository userRepository;

    public UserController(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get List of Users
     *
     * getUsers can be consumed via GET-request to api/event
     * The method uses userRepository.findALL() for finding and returning all users
     *
     * @return list of users
     */
    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Get user
     *
     * getUserById can be consumed via GET-request to api/event
     * The method uses userRepository.findALL() for finding and returning all users
     *
     * @param id long
     * @return user object
     */
    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    /**
     * Save user
     *
     * saveUser can be consumed via GET-request to api/event
     * The method uses userRepository.findALL() for saving the user to the database
     *
     * @param user object
     * @return String message for api consumer
     */
    @PostMapping
    public String saveUser(@RequestBody User user) {
        userRepository.save(user);
        return "User (" + user.getUsername() + ", ID: " + user.getUserId() + ") was saved.";
    }

    /**
     * Update User
     *
     * updateUser can be consumed via PUT-request to api/event
     * The method uses userRepository.findByID() to get the user object from the database
     * updates the stored object by utilizing the user setters
     * and saves it with the repository method save() to the database
     *
     * @param user object
     * @return String message for api consumer
     */
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

    /**
     * Delete user
     *
     * deleteUser can be consumed via DELETE-request to api/event
     * Get the user object by using the repository method findByID()
     * Store the username and Id to local variables for the return message
     * delete the user from the database using the repository method delete()
     *
     * @param id long
     * @return String message for api consumer
     */
    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable long id) {
        User deleteUser = userRepository.findById(id).get();
        String username = deleteUser.getUsername();
        long userId = deleteUser.getUserId();
        userRepository.delete(deleteUser);
        return "User (" + username + ", ID: " + userId + ") was deleted.";
    }

    /**
     * Get user by email
     *
     * getUserByEmail can be consumed via GET-request to api/event
     * The method uses userRepository.findByEmail() for finding and returning a user object
     * @param mail
     * @return user object
     */
    @GetMapping(value = "/mail/{mail}")
    public User getUserByEmail(@PathVariable String mail) {
        User user = userRepository.findByEmail(mail);
        return user;
    }

}