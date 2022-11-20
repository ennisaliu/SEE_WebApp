package ch.ffhs.sse.Controller;

import ch.ffhs.sse.Repository.EventRepository;
import ch.ffhs.sse.Repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/home")
    public String home(){

        return "You logged in (USER).";
    }

    @GetMapping("/admin")
    public String admin() {
        return "You logged in (ADMIN).";
    }
}
