package ch.ffhs.sse.Repository;

import ch.ffhs.sse.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    //Event findEventsByUserId(Long userId);

    @Query("select b from Event b join User d on b.id=d.id where b.userId = ?1")
    List<Event> findEventsByUserId(long userId);
}
