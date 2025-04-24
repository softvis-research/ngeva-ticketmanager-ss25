import event.Event;
import event.EventService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            EventService eventService = new EventService();

            long buchmesseId = eventService.createEvent("Buchmesse", "Leipzig", LocalDate.parse("2026-03-29"), 1000);
            long laternenfestId = eventService.createEvent("Laternenfest", "Halle", LocalDate.parse("2025-08-29"), 2000);
            long pokalfinaleId = eventService.createEvent("DFB-Pokalfinale", "Berlin", LocalDate.parse("2025-05-24"), 74475);

            if (isIdPrime(buchmesseId)) {
                System.out.println("ID " + buchmesseId + " of buchmesse is a prime.");
            } else {
                System.err.println("ID " + buchmesseId + " of buchmesse is a prime!");
            }

            if (isIdPrime(laternenfestId)) {
                System.out.println("ID " + laternenfestId + " of laternenfest is a prime.");
            } else {
                System.err.println("ID " + laternenfestId + " of laternenfest is not a prime!");
            }

            if (isIdPrime(pokalfinaleId)) {
                System.out.println("ID " + pokalfinaleId + " of pokalfinale is a prime.");
            } else {
                System.err.println("ID " + pokalfinaleId + " of pokalfinale is not a prime!");
            }

            System.out.println(eventService.getEventById(pokalfinaleId));

            Event laternenfest = eventService.getEventById(laternenfestId);

            laternenfest.setDate(LocalDate.parse("2025-08-30"));
            laternenfest.setQuota(500);
            eventService.updateEvent(laternenfest);
            System.out.println(eventService.getEventById(laternenfestId));

            eventService.deleteEvent(buchmesseId);

            for (Event e : eventService.getAllEvents())
                System.out.println(e);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public static boolean isIdPrime(long id) {
        if (id % 2 == 0)
            return false;

        for (long i = 3; i * i <= id; i+= 2) {
            if (id % i == 0)
                return false;
        }
        return true;
    }
}