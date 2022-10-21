package ch.ffhs.sse.Model;
import ch.ffhs.sse.EventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long eventId;

    @Enumerated(EnumType.ORDINAL)
    private EventType eventType;

    @Column
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    //@JsonFormat(pattern="dd/MM/yyyy hh:mm")
    private Timestamp start;

    @Column
    private Timestamp end;

    @Column
    private boolean allDay;

    @ManyToOne//(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="userId",nullable = false)
    private User user;

    public Event(){


    }

    //@OneToOne(mappedBy = "event")
    //@JoinColumn(name ="eventId", insertable=false, updatable=false)
    //private User user;
}
