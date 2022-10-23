package ch.ffhs.sse.Controller;

import ch.ffhs.sse.Model.Event;
import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.EventRepository;
import ch.ffhs.sse.Repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/event")
public class EventController {

    EventRepository eventRepository;
    UserRepository userRepository;

    private EventController(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    Event saveEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }
    @GetMapping
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    // In order to save an event with the user we need to instantiate both objects.
    // We then find both objects by id and assign the user to event.
    @PutMapping("/{eventId}/users/{userId}")
    Event addUserToEvent (
            @PathVariable Long eventId,
            @PathVariable Long userId
    ) {
        try {
            // Get the event and user by ID
            Event event = eventRepository.findById(eventId).get();
            User user = userRepository.findById(userId).get();
            // Add user object to the event object and save it by using the save method from event repo..
            event.getEventParticipants().add(user);
            // Return the saved event
            return eventRepository.save(event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Post event method requiring the userId Parameter (Can save Event directly for the user)
    @PostMapping("/{userId}")
    Event saveEventWithUser(@RequestBody Event event, @PathVariable Long userId) {
        User user = userRepository.findById(userId).get();
        // Add user object to the event object and save it by using the save method from event repo..
        event.getEventParticipants().add(user);
        return eventRepository.save(event);
    }

    /*
   @PutMapping(value = "getEvent/{id}")
    public Event getEventById(@PathVariable Long id, @RequestBody Event event) {
        event = eventRepository.findById(id).get();
        return event;
    }

    @GetMapping(value = "/getUserEvents")
    public List<Event> getEvents(@RequestBody Event event) {
        List eventList = eventRepository.getEventsByUserId();
          return eventList;
    }

    */

}
