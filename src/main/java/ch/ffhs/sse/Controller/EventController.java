package ch.ffhs.sse.Controller;

import ch.ffhs.sse.Model.Event;
import ch.ffhs.sse.Model.EventType;
import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.EventRepository;
import ch.ffhs.sse.Repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("/api/event")
public class EventController {

    EventRepository eventRepository;
    UserRepository userRepository;

    EventType eventType;

    private EventController(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    Event saveEvent(@RequestBody Event event) {
        event.getEventType();
        return eventRepository.save(event);
    }

    @GetMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @DeleteMapping
    public String deleteEvent(@RequestParam long Id) {
        Event deleteEvent = eventRepository.findById(Id).get();
        eventRepository.delete(deleteEvent);
        return "Event " + deleteEvent + " was deleted successfully.";
    }

    @PutMapping
    public String updateEvent(@RequestBody Event event) {
        Event updatedEvent = eventRepository.findById(event.getEventId()).get();
        updatedEvent.setStart(event.getStart());
        updatedEvent.setEnd(event.getEnd());
        updatedEvent.setEventType(event.getEventType());;
        updatedEvent.setAllDay(event.getAllDay());
        //updatedEvent.setUserId();
        eventRepository.save(updatedEvent);
        return "Event: " + updatedEvent + " was updated successfully.";
    }

    // In order to save an event with the user we need to instantiate both objects.
    // We then find both objects by id and assign the user to event.
    @PutMapping("/{eventId}/users/{userId}")
    String assignUserToEvent(
            @PathVariable Long eventId,
            @PathVariable Long userId
    ) {
        try {
            // Get the event and user by ID

            Event event = eventRepository.findById(eventId).get();
            User user = userRepository.findById(userId).get();
            if (!user.equals(null) || !event.equals(null)) {
                // Add user object to the event object and save it by using the save method from event repo..
                event.getEventParticipants().add(user);
                // Return the saved event
                return user.getFirstName() + " " + user.getLastName() + "was added to the Event ID:  " + event.getEventId();
            } else {
                throw new Exception("User or Event not found");
            }

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

    /** Gets all events for a single user selected through the userId in the URL path **/
    @GetMapping("/{userId}")
    public List<Event> getUserEvents(@PathVariable Long userId) {
        return eventRepository.findEventsByUserId(userId);
    }

}
