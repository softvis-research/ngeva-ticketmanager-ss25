package client;

import event.Event;
import event.EventService;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ConsoleClient {
    public ConsoleClient() {
        this.eventService = new EventService();
    }

    public void start() {
        String input = "";

        while (!input.equals("x")) {
            printMainMenu();
            input = readInput();
            process(input);
        }
    }

    private void process(String input) {
        switch (input) {
            case "a":
                createNewEvent();
                break;
            case "b":
                readEventById();
                break;
            case "c":
                updateEvent();
                break;
            case "d":
                deleteEvent();
                break;
            case "e":
                readAllEvents();
                break;
            case "f":
                deleteAllEvents();
                break;
            case "x":
                printGoodBye();
                break;
            default:
                printIllegalInput();
                break;
        }
    }

    private void printIllegalInput() {
        System.out.println("\nYour input was illegal. Only type in letters from the main menu!");
    }

    private void printGoodBye() {
        System.out.println("\nThank you for using the console client. Have a nice day!");
    }

    private void deleteAllEvents() {
        eventService.getAllEvents().forEach(e -> eventService.deleteEvent(e.getId()));
        System.out.println("\nAll events were deleted.");
    }

    private void readAllEvents() {
        var events = eventService.getAllEvents();
        if (events.isEmpty()) {
            System.out.println("\nCurrently there are no events available.");
        } else {
            System.out.println("\nList of all events:");
            eventService.getAllEvents().forEach(this::print);
        }
    }

    private void deleteEvent() {
        try {
            System.out.println("\nPlease enter the id of the event which should be deleted:");
            long id = Long.valueOf(readInput());
            eventService.deleteEvent(id);
            System.out.println("\nThe event with the id " + id + " was deleted.");
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            System.err.println("Please enter a number as id!");
        }
    }

    private void updateEvent() {
        try {
            System.out.println("\nPlease enter the id of the event which should be modified:");
            long id = Long.valueOf(readInput());
            Event event = eventService.getEventById(id);
            System.out.println("\nThe current values of the event are:");
            print(event);

            System.out.println("\nPlease enter the new values of the event:");
            System.out.print("Title: ");
            event.setTitle(readInput());

            System.out.print("Location: ");
            event.setLocation(readInput());

            System.out.print("Date (yyyy-mm-dd): ");
            event.setDate(LocalDate.parse(readInput()));

            System.out.print("Quota: ");
            event.setQuota(Integer.valueOf(readInput()));

            eventService.updateEvent(event);

            System.out.println("\nThe event with the id " + id + " was successfully updated.");
        } catch (NumberFormatException illegalNumber) {
            System.err.println(illegalNumber.getMessage());
            System.err.println("Please enter a valid number!");
        } catch (DateTimeParseException illegalDate) {
            System.err.println(illegalDate.getMessage());
            System.err.println("Please enter the date in the format: yyyy-mm-dd!");
        } catch (IllegalArgumentException invalidID) {
            System.err.println(invalidID.getMessage());
            System.err.println("Please enter an actual id of an event!");
        }
    }

    private void readEventById() {
        try {
            System.out.println("\nPlease enter the id of the event which should be read:");
            long id = Long.valueOf(readInput());
            print(eventService.getEventById(id));
        } catch (NumberFormatException illegalID) {
            System.err.println(illegalID.getMessage());
            System.err.println("Please enter a number as id!");
        } catch (IllegalArgumentException invalidID) {
            System.err.println(invalidID.getMessage());
            System.err.println("Please enter an actual id of an event!");
        }
    }

    private void createNewEvent() {
        try {
            System.out.println("\nPlease enter the characteristics of the event which should be created:");

            System.out.print("Title: ");
            String title = readInput();

            System.out.print("Location: ");
            String location = readInput();

            System.out.print("Date (yyyy-mm-dd): ");
            LocalDate date = LocalDate.parse(readInput());

            System.out.print("Quota: ");
            int quota = Integer.valueOf(readInput());

            long id = eventService.createEvent(title, location, date, quota);
            System.out.println("\nSuccessfully created event with the id = " + id);
        } catch (DateTimeParseException illegalDate) {
            System.err.println(illegalDate.getMessage());
            System.err.println("Please enter the date in the format: yyyy-mm-dd!");
        } catch (NumberFormatException illegalQuota) {
            System.err.println(illegalQuota.getMessage());
            System.err.println("Please enter a number for the quota!");
        } catch (IllegalArgumentException illegalEventParameter) {
            System.err.println(illegalEventParameter.getMessage());
        }
    }

    private void print(Event e) {
        System.out.println();
        System.out.println(e);
    }

    private String readInput() {
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            return inputReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printMainMenu() {
        System.out.println("\nPlease choose one of the following options by entering the corresponding letter:");
        System.out.println("(a) Create a new Event");
        System.out.println("(b) Read an event");
        System.out.println("(c) Change an event");
        System.out.println("(d) Delete an event");
        System.out.println("(e) Read all events");
        System.out.println("(f) Delete all events");
        System.out.println("(x) Exit\n");
    }

    private EventService eventService;
}