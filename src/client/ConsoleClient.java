package client;

import customer.Customer;
import customer.CustomerService;
import event.Event;
import event.EventService;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ConsoleClient {
    public ConsoleClient() {
        this.eventService = new EventService();
        this.customerService = new CustomerService();
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
            case "1":
                createNewCustomer();
                break;
            case "2":
                readCustomerById();
                break;
            case "3":
                updateCustomer();
                break;
            case "4":
                deleteCustomer();
                break;
            case "5":
                readAllCustomers();
                break;
            case "6":
                deleteAllCustomers();
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
            events.forEach(this::print);
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

    private void deleteAllCustomers() {
        customerService.deleteAllCustomers();
        System.out.println("\nAll customers were deleted.");
    }

    private void readAllCustomers() {
        var customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("\nCurrently there are no customers available.");
        } else {
            System.out.println("\nList of all customers:");
            customers.forEach(this::print);
        }
    }

    private void deleteCustomer() {
        try {
            System.out.println("\nPlease enter the id of the customer who should be deleted:");
            long id = Long.valueOf(readInput());
            customerService.deleteCustomer(id);
            System.out.println("\nThe customer with the id " + id + " was deleted.");
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            System.err.println("Please enter a number as id!");
        }
    }

    private void updateCustomer() {
        try {
            System.out.println("\nPlease enter the id of the customer who should be modified:");
            long id = Long.valueOf(readInput());
            Customer customer = customerService.getCustomerById(id);
            System.out.println("\nThe current values of the customer are:");
            print(customer);

            System.out.println("\nPlease enter the new values of the event:");
            System.out.print("Username: ");
            customer.setUserName(readInput());

            System.out.print("Email address: ");
            customer.setEmailAddress(readInput());

            System.out.print("Birth date (yyyy-mm-dd): ");
            customer.setBirthDate(LocalDate.parse(readInput()));

            customerService.updateCustomer(customer);

            System.out.println("\nThe customer with the id " + id + " was successfully updated.");
        } catch (NumberFormatException illegalNumber) {
            System.err.println(illegalNumber.getMessage());
            System.err.println("Please enter a valid number!");
        } catch (DateTimeParseException illegalDate) {
            System.err.println(illegalDate.getMessage());
            System.err.println("Please enter the date in the format: yyyy-mm-dd!");
        } catch (IllegalArgumentException invalidID) {
            System.err.println(invalidID.getMessage());
            System.err.println("Please enter an actual id of a customer!");
        }
    }

    private void readCustomerById() {
        try {
            System.out.println("\nPlease enter the id of the event which should be read:");
            long id = Long.valueOf(readInput());
            print(customerService.getCustomerById(id));
        } catch (NumberFormatException illegalID) {
            System.err.println(illegalID.getMessage());
            System.err.println("Please enter a number as id!");
        } catch (IllegalArgumentException invalidID) {
            System.err.println(invalidID.getMessage());
            System.err.println("Please enter an actual id of a customer!");
        }
    }

    private void createNewCustomer() {
        try {
            System.out.println("\nPlease enter the characteristics of the customer which should be created:");

            System.out.print("Username: ");
            String userName = readInput();

            System.out.print("Email address: ");
            String emailAddress = readInput();

            System.out.print("Birth date (yyyy-mm-dd): ");
            LocalDate birthDate = LocalDate.parse(readInput());

            long id = customerService.createCustomer(userName, emailAddress, birthDate);
            System.out.println("\nSuccessfully created customer with the id = " + id);
        } catch (DateTimeParseException illegalDate) {
            System.err.println(illegalDate.getMessage());
            System.err.println("Please enter the date in the format: yyyy-mm-dd!");
        } catch (IllegalArgumentException illegalCustomerParameter) {
            System.err.println(illegalCustomerParameter.getMessage());
        }
    }

    private void print(Event e) {
        System.out.println();
        System.out.println(e);
    }

    private void print(Customer c) {
        System.out.println();
        System.out.println(c);
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
        System.out.println("\nPlease choose one of the following options by entering the corresponding letter or digit:");
        System.out.println("\nEvent Processing");
        System.out.println("(a) Create a new event");
        System.out.println("(b) Read an event");
        System.out.println("(c) Change an event");
        System.out.println("(d) Delete an event");
        System.out.println("(e) Read all events");
        System.out.println("(f) Delete all events");
        System.out.println("\nCustomer Processing");
        System.out.println("(1) Create a new customer");
        System.out.println("(2) Read a customer");
        System.out.println("(3) Change a customer");
        System.out.println("(4) Delete a customer");
        System.out.println("(5) Read all customers");
        System.out.println("(6) Delete all customers");
        System.out.println("\n(x) Exit\n");
    }

    private EventService eventService;
    private CustomerService customerService;
}