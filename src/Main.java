import event.Event;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Event buchmesse = new Event("Buchmesse", "Leipzig", LocalDate.parse("2025-03-29"), 1000);
        Event laternenfest = new Event("Laternenfest", "Halle", LocalDate.parse("2025-08-29"), 2000);
        Event pokalfinale = new Event("DFB-Pokalfinale", "Berlin", LocalDate.parse("2025-05-24"), 74475);

        System.out.println(buchmesse.getLocation());
        System.out.println(laternenfest.getDate());
        System.out.println(pokalfinale);
    }
}