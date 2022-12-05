package ch.ffhs.sse.Controller;

import ch.ffhs.sse.Exception.NotFoundException;
import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    UserService userService;
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User authenticate(String username, String password) {
        User user = userService.authenticate(username, password);
        System.out.println("JETZT KOMMT DER " + user);
        if(user == null)
            throw new NotFoundException();
        return user;
    }

}
