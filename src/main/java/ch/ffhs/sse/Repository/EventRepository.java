package ch.ffhs.sse.Repository;

import ch.ffhs.sse.Model.Event;
import ch.ffhs.sse.Model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


/**
 * The EventRepository handles all the JDBC operations
 * extends the JPA re
 * This API can be consumed by any client application making http requests.
 *
 * The controller  makes use of the event and user repository by utilizing the DTO
 * and performing all endpoint logic.
 * The controller generates an endpoint which is accessed via path /api/event
 * and accepts JSON objects as input parameters which then are converted to java objects.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long>, PagingAndSortingRepository<Event, Long> {


    /**
     * Return a list of events which of which a specific user was assigned to
     *
     * @param userId long
     * @return list of events
     */
    @Query(value = "SELECT event.*, ep.event_id from event " +
            "left join event_participants ep on event.event_id = ep.event_id " +
            "where ep.user_id = :userId" , nativeQuery=true)
    List<Event> findEventsByUserId(@Param("userId") long userId);

    /** ## Not yet implemented methods ##
     * Return a list of events which of which a specific user and event type was assigned to
     * @param userId
     * @param eventType
     * @return list of events
     */
    @Query(value = "SELECT * from event " +
            "left join event_participants ep on event.event_id = ep.event_id " +
            "where ep.user_id = :userId and event_type = :eventType" , nativeQuery=true)
    List<Event> findEventsByTypeAndByUserId(@Param("userId") long userId, @Param("eventType") int eventType);

    /** Test methods: these are used only for testing purposes **/
    Event findEventByEventId(long eventId);
    @Query(value = "SELECT * from event " +
            "where event.end = :end and event.start = :start and event.event_type = :eventType", nativeQuery = true)
    Event findEventByStartAndEndAndEventType(LocalDateTime start, LocalDateTime end, EventType eventType);

    @Query(value = "SELECT event.event_id from event " +
            "where event.end = :end and event.start = :start LIMIT 1", nativeQuery = true)
    long findEvenIdtByStartAndEndAndEventType(LocalDateTime start, LocalDateTime end);


}
