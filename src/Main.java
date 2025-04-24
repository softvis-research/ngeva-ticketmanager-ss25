import event.Event;
import event.EventService;
import idgenerator.PrimeGenerator;

public class Main {
    public static void main(String[] args) {
        PrimeGenerator primeGenerator = new PrimeGenerator(1000000000, 9999999999L);

        for (int i = 0; i < 100; i++) {
            System.out.println(primeGenerator.getNextPrime());
        }
    }
}
