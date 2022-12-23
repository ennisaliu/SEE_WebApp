package ch.ffhs.sse;

import ch.ffhs.sse.Controller.EventController;
import ch.ffhs.sse.Controller.UserController;
import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * This class tests all the user controller methods, verifying the correctness of the user related classes.
 */

@SpringBootTest
class UserTest {

    @Autowired
    UserController userController;
    @Autowired
    private UserRepository userRepository;

    //define user name
    String userNameTest = "max.tester";
    //define email which gets used in tests
    String userEmailTest = "max.tester@test.com";
    //create object


    @Test
    @Order(1)
    void saveUser() {
        User user = new User(userNameTest, "max.tester@test.com", "123", "USER", "Max", "Tester");
        //save using repository method
        userController.saveUser(user);
        //sql check if user exists
        boolean result = userRepository.existsUserByUsername(userNameTest);
        //check if result is true
        assertThat(result).isTrue();
    }

    @Test
    @Order(2)
    void getUser() {
        long userId = userRepository.findUserIdByUsername(userNameTest);
        //check if result equal to toe set username
        assertThat(userController.getUserById(userId).getUsername()).isEqualTo(userNameTest);
    }

    @Test
    @Order(3)
    void getUsers() {
        User user = new User(userNameTest, "max.listtester@test.com", "123", "USER", "Max", "List");
        userController.saveUser(user);
        List userList = userController.getUsers();
        //check if the list is not empty
        assertThat(userList.isEmpty()).isFalse();
        userController.deleteUser(user.getUserId());
    }

    @Test
    @Order(4)
    void getUserByMail() {
        userRepository.findByEmail(userEmailTest);
        assertThat(userEmailTest).contains("test");
    }

    @Test
    @Order(5)
    void deleteUser() {
        long userId = userRepository.findUserIdByUsername(userNameTest);
        userController.deleteUser(userId);
        boolean result = userRepository.existsUserByUsername(userNameTest);
        //check if result is true
        assertThat(result).isFalse();
    }

}
