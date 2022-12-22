package ch.ffhs.sse;

import ch.ffhs.sse.Controller.LoginController;
import ch.ffhs.sse.Controller.UserController;
import ch.ffhs.sse.Exception.NotFoundException;
import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.UserRepository;
import com.mysql.cj.log.Log;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class LoginTest {

    @Autowired
    UserController userController;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginController loginController;

    //create object
    User user = new User("max.login", "max.login@test.com", "123", "USER", "Max", "Tester");

    @Test
    @Order(6)
    void testRepository() {
        //save user in DB
        userController.saveUser(user);
        //check if login returns correct user for methods in repo and controller
        User repoLogin = userRepository.findUserByUsernameAndPassword("max.login", "123");
        assertThat(repoLogin.getUsername()).isEqualTo("max.login");
        //delete user and clean DB
        userController.deleteUser(user.getUserId());
    }
    @Test
    @Order(7)
    void testController() throws NotFoundException {
        //save user in DB
        userController.saveUser(user);
        //check if login returns correct user
        Optional<User> controllerLogin = loginController.findUserByUsernameAndPassword(Optional.ofNullable(user));
        assertThat(controllerLogin.get().getUsername()).isEqualTo("max.login");
        //delete user and clean DB
        userController.deleteUser(user.getUserId());
    }
}
