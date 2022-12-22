package ch.ffhs.sse.Repository;

import ch.ffhs.sse.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /** Checks if username and password matches a user in database **/
    User findUserByUsernameAndPassword(String username, String password);

    /** Test methods: these are used only for testing purposes **/
    @Query(value = "SELECT user_id, username FROM user WHERE user.username = :username AND user.password = :password" , nativeQuery=true)
    User authenticate(String username, String password);

    User findByEmail(@Param("email") String email);
    @Query("SELECT CASE WHEN COUNT(user) > 0 THEN TRUE ELSE FALSE END FROM User user WHERE user.username = ?1")
    Boolean existsUserByUsername(String username);
    @Query(value = "SELECT user_id FROM user WHERE user.username = :username" , nativeQuery=true)
    long findUserIdByUsername(String username);
}
