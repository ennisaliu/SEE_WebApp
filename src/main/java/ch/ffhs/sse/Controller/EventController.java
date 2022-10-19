package ch.ffhs.sse.Controller;

import ch.ffhs.sse.Model.Event;
import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.EventRepository;
import org.apache.coyote.Request;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;

    private EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @PutMapping(value = "getEvent/{event_id}")
    public Event getUserById(@PathVariable Long id, @RequestBody Event event) {
        event = eventRepository.findById(id).get();
        return event;
    }

    @GetMapping(value = "/listAllEvents")
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @GetMapping(value = "/getUserEvents")
    public List<Event> getEvents(@RequestBody Event event) {
        List eventList = eventRepository.findEventsByUserId();
        return eventList;
    }

    @PostMapping(value = "/save")
    public String saveEvent(@RequestBody Event event) {
        eventRepository.save(event);
        return "Event was saved successfully.";
    }
}
