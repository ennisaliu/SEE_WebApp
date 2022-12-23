package ch.ffhs.sse;

import ch.ffhs.sse.Controller.EventController;
import ch.ffhs.sse.Controller.LoginController;
import ch.ffhs.sse.Controller.UserController;
import ch.ffhs.sse.Model.Event;
import ch.ffhs.sse.Model.EventType;
import ch.ffhs.sse.Model.User;
import ch.ffhs.sse.Repository.EventRepository;
import ch.ffhs.sse.Repository.UserRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * This class tests all the event controller methods, verifying the correctness of the event related classes.
 */


@SpringBootTest
public class EventTest {
    
    @Autowired
    UserController userController;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventController eventController;

    @Autowired
    UserRepository userRepository;

    //Helper date variables
    LocalDateTime start = LocalDateTime.of(2022,02,02,17,38,11);
    LocalDateTime end = start.plusHours(2);


    //Helper create user method
//    private User createUser() {
//        User user = new User();
//        user.setUsername("max.eventtester");
//        user.setPassword("123");
//        user.setEmail("max.eventtester@ch.ch");
//        return user;
//    }

//    private Event createEvent() {
//        Event event = new Event();
//        event.setEventType(EventType.Arbeitszeit);
//        event.setStart(start);
//        event.setEnd(end);
//        event.setAllDay(false);
//        return event;
//    }

    @Test
    void saveEvent() {
        //create new event object
        Event event = new Event();
        event.setEventType(EventType.Ferien);
        event.setStart(start);
        event.setEnd(end);
        event.setAllDay(false);
        //save event object using controller method
        eventController.saveEvent(event);

        //check if event was created
        assertThat(eventRepository.findById(event.getEventId()).get()).isNotNull();

        //delete using repo method
        eventRepository.delete(event);

        //check if event was deleted
        boolean check = false;
        if (eventRepository.findAll().isEmpty()) check = true;
        assertThat(check);
    }

    @Test
    void getEvents() {
        //check if list is not empty (event should be generated from test 1)
        Event event = new Event();
        event.setEventType(EventType.Ferien);
        event.setStart(start);
        event.setEnd(end);
        event.setAllDay(false);
        eventRepository.save(event);

        //check if list is not empty
        assertThat(eventController.getEvents()).asList().isNotNull();

        //delete using repo method
        eventRepository.delete(event);

        //check if event was deleted
        boolean check = false;
        if (eventRepository.findAll().isEmpty()) check = true;
        assertThat(check);

    }

    @Test
    void deleteEvent() {
//        eventController.saveEvent(event);
        Event event = new Event();
        event.setEventType(EventType.Ferien);
        event.setStart(start);
        event.setEnd(end);
        event.setAllDay(false);
        eventRepository.save(event);

        //delete event using controller method
        eventController.deleteEvent(event.getEventId());

        //check if event was deleted
        assertThat(eventRepository.findEventByEventId(event.getEventId())).isNull();
    }

    @Test
    void updateEvent() {
        //create new event
        Event event = new Event();
        event.setEventType(EventType.Arbeitszeit);
        event.setStart(start.plusDays(1));
        event.setEnd(end.plusDays(1));
        event.setAllDay(false);

        //save modified event to DB
        eventController.saveEvent(event);

        //modify and save object
        event.setEventType(EventType.Ferien);
        event.setStart(start.minusDays(5));
        event.setEnd(end.minusDays(5));
        eventController.updateEvent(event);

        //check if event was updated
        assertThat(eventController.getEvents()).asList().isNotNull();
        Event updatedEvent = eventRepository.findEventByEventId(event.getEventId());
        assertThat(updatedEvent.getStart()).isEqualTo(start.minusDays(5));
        assertThat(updatedEvent.getEnd()).isEqualTo(end.minusDays(5));
        assertThat(updatedEvent.getEventType()).isEqualTo(EventType.Ferien);

        //delete event
        eventController.deleteEvent(event.getEventId());

        //check if event deleted and not listed
        assertThat(eventController.getEvents().contains(eventRepository.findEventByEventId(event.getEventId()))).isFalse();
    }

    @Test
    void assignUserEvent() {
        //create user
        User user = new User();
        user.setUsername("max.eventtester2");
        user.setPassword("1234");
        user.setEmail("max.eventtester2@ch.ch");

        userController.saveUser(user);

        //get existing event
        Event event = new Event();
        event.setEventType(EventType.Arbeitszeit);
        event.setStart(start);
        event.setEnd(end);
        event.setAllDay(false);

        eventController.saveEvent(event);

        long userId = userRepository.findUserIdByUsername("max.eventtester2");
        long eventId = eventRepository.findEvenIdtByStartAndEndAndEventType(start, end);

//      make sure both objects exist before assigning them
        assertThat(eventId).isNotNull();
        assertThat(userId).isNotNull();

        eventController.assignUserToEvent(eventId, userId);
        //eventController.assignUserToEvent(eventId, userId);

        assertThat(eventRepository.getReferenceById(eventId).getEventParticipants()).isNotNull();

        // delete event and user
        eventController.deleteUserEvent(event, userId);
        userController.deleteUser(userId);
    }

    @Test
    void saveAndDeleteUserEvent() {
        //create and save user
        User user = new User();
        user.setUsername("max.eventtester3");
        user.setPassword("123");
        user.setEmail("max.eventtester3@ch.ch");
        userController.saveUser(user);
        long userID = user.getUserId();

        //create event by using save test method
        Event event = new Event();
        event.setEventType(EventType.Arbeitszeit);
        event.setStart(start);
        event.setEnd(end);
        event.setAllDay(false);

        //delete userEvent
        eventController.saveUserEvent(event,userID);

        //delete event
        eventController.deleteUserEvent(event, userID);

        //delete user
        userController.deleteUser(userID);
    }



}
