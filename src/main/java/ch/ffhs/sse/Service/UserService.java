package ch.ffhs.sse.Service;

import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

//Might do everything in Controller, needs to be defined in architecture first
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User addUser(User user) {
        return userRepository.save(user);
    }
}
