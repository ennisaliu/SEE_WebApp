package ch.ffhs.sse.Model;
import ch.ffhs.sse.EventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long eventId;

    /*
    @ManyToOne //(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="userId",nullable = false)
    private User user;
    */

    @Enumerated(EnumType.ORDINAL)
    private EventType eventType;

    @Column
    //@NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    //@JsonFormat(pattern="dd/MM/yyyy hh:mm")
    private Timestamp start;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Timestamp end;

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
        public Timestamp getEnd() {
            return end;

    }
    public void setEnd(Timestamp end) {
        this.end = end;
    }
    public boolean getAllDay() {
        return allDay;

    }
    public Timestamp getStart() {
        return start;

    }
    public void setStart(Timestamp start) {
        this.start = start;
    }
    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public Set<User> getEventParticipants() {
        return eventParticipants;
    }

}
