package ch.ffhs.sse.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "You logged in (USER).";
    }

    @GetMapping("/admin")
    public String admin() {
        return "You logged in (ADMIN).";
    }
}
