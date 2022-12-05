package ch.ffhs.sse.Repository;

import ch.ffhs.sse.Model.Event;
import ch.ffhs.sse.Model.EventType;
import ch.ffhs.sse.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, PagingAndSortingRepository<Event, Long> {

    /** Return a list of events which of which a specific user was assigned to **/
    @Query(value = "SELECT * from event " +
            "left join event_participants ep on event.event_id = ep.event_id " +
            "where ep.user_id = :userId" , nativeQuery=true)
    List<Event> findEventsByUserId(@Param("userId") long userId);

    /** Return a list of events which of which a specific user and event type was assigned to **/
    @Query(value = "SELECT * from event " +
            "left join event_participants ep on event.event_id = ep.event_id " +
            "where ep.user_id = :userId and event_type = :eventType" , nativeQuery=true)
    List<Event> findEventsByTypeAndByUserId(@Param("userId") long userId, @Param("eventType") int eventType);

    @Query(value = "SELECT * from event " +
            "left join event_participants ep on event.event_id = ep.event_id " +
            "where ep.user_id = :userId and event_type = :eventType " +
            "and event.start >= start and (event.end <= end OR event.end is null)" , nativeQuery=true)
    List<Event> findEventsByTypeAndByUserIdWithTime(@Param("userId") long userId, @Param("eventType") int eventType);

}
