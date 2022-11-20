package ch.ffhs.sse.Repository;

import ch.ffhs.sse.Model.Event;
import ch.ffhs.sse.Model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, PagingAndSortingRepository<Event, Long> {
    //Event findEventsByUserId(Long userId);

    //@Query("select Event from Event where Event.user.id = ?1")
   /* @Query(
            value = "SELECT * FROM Event e WHERE e.user.id =?1",
            nativeQuery = true)
    )

    @Query(value = "select * from event e where e.user.id =?1",
    nativeQuery = true)

    @Query(value="select * from author a where a.first_name= :firstName", nativeQuery=true)
    List<Author> getAuthorsByFirstName(String firstName);
*/

    //Test if e.user or e.user.id is correct to assigned the foreign key userId
    @Query(value = "select * from event e where e.user = :userId", nativeQuery = true)
    List<Event> getEventsByUserId(long userId);

    //Get all events based on event type --> example: show only 'Ferien'.
    EventType findByEventType(EventType eventType);


    // select * from Event where user.id = 2;
    //List<Event> findEventsByUserId(Event event);
}
