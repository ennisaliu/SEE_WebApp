package ch.ffhs.sse.Repository;

import ch.ffhs.sse.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Checks if username and password matches a user in database
     * This method is used in the login process
     */
    User findUserByUsernameAndPassword(String username, String password);

    /**
     * findByEmail returns a user by finding it by an email parameter
     *
     * @param email
     * @return user object
     */
    User findByEmail(@Param("email") String email);

    /**
     *
     * Test method: This method is only for testing purposes
     *
     * @param username
     * @param password
     * @return {@return }
     */
    @Query(value = "SELECT user_id, username FROM user WHERE user.username = :username AND user.password = :password" , nativeQuery=true)
    User authenticate(String username, String password);

    /**
     * Test method: This method is only for testing purposes
     * Check if a user exists by finding it by username
     *
     * @param username String
     * @return boolean validation
     */
    @Query("SELECT CASE WHEN COUNT(user) > 0 THEN TRUE ELSE FALSE END FROM User user WHERE user.username = ?1")
    Boolean existsUserByUsername(String username);

    /**
     * Test method: This method is only for testing purposes
     *
     * @param username
     * @return long userId
     */
    @Query(value = "SELECT user_id FROM user WHERE user.username = :username" , nativeQuery=true)
    long findUserIdByUsername(String username);
}
