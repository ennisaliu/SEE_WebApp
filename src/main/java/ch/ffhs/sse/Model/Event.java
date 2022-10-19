package ch.ffhs.sse.Model;
import ch.ffhs.sse.EventType;
import com.sun.istack.NotNull;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Enumerated(EnumType.ORDINAL)
    private EventType eventType;

    @Column
    @NotNull
    private Timestamp start;

    @Column
    private Timestamp end;

    @Column
    private boolean allDay;

    @ManyToOne(targetEntity = User.class, optional = false)
    @JoinColumn(name="id",referencedColumnName="id", insertable=false, updatable=false)
    private long userId;
}
