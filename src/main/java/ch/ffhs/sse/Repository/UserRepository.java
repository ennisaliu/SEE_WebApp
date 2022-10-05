package ch.ffhs.sse.Repository;

import ch.ffhs.sse.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//The JPA repository needs the class and the type of the primary key (in this case User and Long
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // We can add methods in addition to the default methods provided by the JPA repository. For example findByEmail.
    User findByEmail (String email);

}
