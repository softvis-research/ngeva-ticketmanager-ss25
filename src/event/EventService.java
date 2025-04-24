package event;

import idgenerator.IDService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class EventService {

    public EventService() {
        this.events = new ArrayList<>();
        this.idService = new IDService();
    }

    public long createEvent(String title, String location, LocalDate date, int quota) {
        if (quota < 0) {
            throw new IllegalArgumentException("The quota must not be negative!");
        }

        if (!date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("The event must take place in the future!");
        }

        long eventId = idService.generateId();
        events.add(new Event(eventId, title, location, date, quota));

        return eventId;
    }

    public void updateEvent(Event event) {
        int i = 0;
        for (Event e : events) {
            if (e.getId() == event.getId()) {
                events.set(i, event);
                return;
            }
            i++;
        }
        throw new IllegalArgumentException("Event with the ID = " + event.getId() + " does not exist!");
    }

    public void deleteEvent(long id) {
        for (Event e : events ) {
            if (e.getId() == id) {
                events.remove(e);
                idService.releaseId(id);
                return;
            }
        }
    }

    public Event getEventById(long id) {
        for (Event e : events ) {
            if (e.getId() == id)
                return e;
        }

        throw new IllegalArgumentException("Event with the ID = " + id + " does not exist!");
    }

    public Collection<Event> getAllEvents() {
        return new ArrayList<>(events);
    }

    private ArrayList<Event> events;
    private IDService idService;
}