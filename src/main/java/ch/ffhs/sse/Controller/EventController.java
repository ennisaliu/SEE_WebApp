package ch.ffhs.sse.Controller;

import ch.ffhs.sse.Exception.NotFoundException;
import ch.ffhs.sse.Model.Event;
import ch.ffhs.sse.Model.EventType;
import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.EventRepository;
import ch.ffhs.sse.Repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The EventController represents the API endpoint for all event requests.
 * This API can be consumed by any client application making http requests.
 *
 * The controller  makes use of the event and user repository by utilizing the DTO
 * and performing all endpoint logic.
 * The controller generates an endpoint which is accessed via path /api/event
 * and accepts JSON objects as input parameters which then are converted to java objects.
 */

@RestController
@RequestMapping("/api/event")
public class EventController {

    EventRepository eventRepository;
    UserRepository userRepository;

    private EventController(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    /**
     * Save Event
     *
     * saveEvent can be consumed via POST-request to api/event
     * uses eventRepository.save() for storing the event object in the database.
     * @return Event object
     * @param  event object
     */
    @PostMapping
    public Event saveEvent(@RequestBody Event event) {
        event.getEventType();
        return eventRepository.save(event);
    }

    /**
     * Get List of Events
     *
     * getEvents can be consumed via GET-request to api/event
     * The method uses eventRepository.findALL() for finding and returning all events
     * @return list of events
     */
    @GetMapping
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    /**
     * Delete Event by ID
     *
     * deleteEvent can be consumed via DELETE-request to api/event
     * The method uses eventRepository.delete() to delete the event object in the database
     * @param Id long
     * @return String message for api consumer
     */
    @DeleteMapping
    public String deleteEvent(@RequestParam long Id) {
        Event deleteEvent = eventRepository.findById(Id).get();
        eventRepository.delete(deleteEvent);
        return "Event " + deleteEvent + " was deleted successfully.";
    }

    /**
     * Update Event
     *
     * updateEvent can be consumed via PUT-request to api/event
     * The method uses eventRepository.findByID() to get the event object from the database
     * and updates the stored object event by utilizing the event setters
     * and saves it with the repository method save to the database
     * @param event Object
     * @return String message for api consumer
     */
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

    /**
     * Assign existing user to existing event
     *
     * assignUserToEvent can be consumed via PUT-request to api/event
     * In order to save an event with the user both objects have to be instantiated
     * Both objects are found by id and then the user is assigned to the event object
     * using setter methods from the object
     * and saving it to the database with the repository method
     *
     * @param eventId long
     * @param userId long
     * @return String message for api consumer
     * @exception NotFoundException
     * @exception RuntimeException
     */

    @PutMapping("/{eventId}/user/{userId}")
    public String assignUserToEvent(
            @PathVariable Long eventId,
            @PathVariable Long userId
    ) {
        try {
            // Get the event and user by ID
            Event event = eventRepository.findById(eventId).get();
            User user = userRepository.findById(userId).get();
            //check if  any of the two objects are empty
            if (!user.equals(null) || !event.equals(null)) {
                // Add user object to the event object and save it by using the save method from event repo..
                event.getEventParticipants().add(user);
                eventRepository.save(event);
                // Return the saved event
                return user.getFirstName() + " " + user.getLastName() + " was added to the Event ID:  " + event.getEventId();
            //throw error if any user or object is empty
            } else {
                throw new NotFoundException("User or Event not found");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Assign existing user to existing event
     *
     * saveUserEvent can be consumed via PUT-request to api/event
     * This POST event method can save an event directly for the defined user (by param)
     *
     * In order to save an event with the user both objects have to be instantiated
     * The event object created from the passed JSON object from the frontend client
     * The user object is retrieved by the user repository method findByID (with the param Id)
     * The user object is then added to the eventParticipants (table which maps userid to eventId)
     * by getting the event participants Set using the event getter method and then
     * assigning the Set method to add the user
     * and saving the event with the event repository method.
     *
     * @param event object
     * @param userId long
     * @return event object
     */
    @PostMapping("/{userId}")
    public Event saveUserEvent(@RequestBody Event event, @PathVariable Long userId) {
        User user = userRepository.findById(userId).get();
        // Add user object to the event object and save it by using the save method from event repo..
        event.getEventParticipants().add(user);
        return eventRepository.save(event);
    }


    /**
     * Gets list of events for a user
     *
     * getUserEvents can be consumed via PUT-request to api/event
     * This GET event method can save an event directly for the defined user (by param)
     * @param userId long
     * @return list of event
     */
    @GetMapping("/{userId}")
    public List<Event> getUserEvents(@PathVariable Long userId) {
        return eventRepository.findEventsByUserId(userId);
    }


    /**
     * Delete an event assigned to a user
     *
     * Get the user object by using the repository method findByID()
     * Store the username and Id to local variables for the return message
     * delete the user from the database using the repository method delete()
     *
     * @param event object
     * @param userId long
     * @return String message for api consumer
     */
    @DeleteMapping("/{userId}")
    public String deleteUserEvent(@RequestBody Event event, @PathVariable Long userId) {
        User user = userRepository.findById(userId).get();
        Event deleteEvent = eventRepository.findById(event.getEventId()).get();
        // Remove user from relation table first
        event.getEventParticipants().remove(user);
        eventRepository.delete(deleteEvent);
        return "Event " + deleteEvent + " was deleted successfully.";
    }

}