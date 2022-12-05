package ch.ffhs.sse.Service;

import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
        @Autowired
        private UserRepository userRepository;
        public User authenticate(String username, String password) {
            return userRepository.findUserByUsernameAndPassword(username, password);
        }


}
