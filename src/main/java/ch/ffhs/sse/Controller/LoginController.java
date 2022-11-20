package ch.ffhs.sse.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class LoginController {
    @GetMapping
    public String login() {
        return "Login";
    }
}
