package ch.ffhs.sse.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long eventId;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private EventType eventType;

    @Column
    private LocalDateTime start;
    @Column
    private LocalDateTime end;

    @Column
    private boolean allDay;

    //define many to many relationship event <-> user and create a table for reference
    //users participation in the event get saved in a HashSet
    @ManyToMany
    @JoinTable(
            name = "event_participants",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<User> eventParticipants = new HashSet<>();

    /*
    public Event(long eventId, boolean allDay, Timestamp start, Timestamp end, EventType eventType) {
        this.eventId = eventId;
        this.allDay = allDay;
        this.start = start;
        this.end = end;
        this.eventType = eventType;
    }

    public Event(){

    }
    */
    public long getEventId() {
        return eventId;
    }

    public void setEventId(long id) {
        this.eventId = id;
    }

    public void setUserId(long id) {
        setUserId(id);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public Set<User> getEventParticipants() {
        return eventParticipants;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

}
