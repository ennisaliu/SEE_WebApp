package ch.ffhs.sse.Repository;

import ch.ffhs.sse.Model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


//The JPA repository needs the class and the type of the primary key (in this case User and Long
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /** Return a user if the username and password match in the database **/
    User findUserByUsernameAndPassword(String username, String password);
    /** Return a user which matches the email parameter (String) **/
    User findByEmail(@Param("email") String email);

}
