package ch.ffhs.sse.Repository;

import ch.ffhs.sse.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


//The JPA repository needs the class and the type of the primary key (in this case User and Long
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // We can add methods in addition to the default methods provided by the JPA repository. For example findByEmail.

    User findByEmail(String email);

    /** Checks if username and password matches a user in database **/


    @Query(value = "SELECT * FROM user WHERE user.username = :username AND user.password = :password" , nativeQuery=true)
    List<User> findUserWithParams(@Param("username") String username, @Param("password") String password);

    User findByUsername(String username);
}
