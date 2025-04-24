package idgenerator;

import java.util.ArrayList;

public class IDService {

    public IDService() {
        usedIds = new ArrayList<>();
        primeGenerator = new PrimeGenerator(ID_LOWER_BOUND, ID_UPPER_BOUND);
    }

    public long generateId() {
        long nextId = primeGenerator.getNextPrime();

        usedIds.add(nextId);
        return nextId;
    }

    public void releaseId(long id) {
        usedIds.remove(Long.valueOf(id));
    }

    private ArrayList<Long> usedIds;
    private PrimeGenerator primeGenerator;

    private final static long ID_LOWER_BOUND = 1000000000;
    private final static long ID_UPPER_BOUND = 9999999999L;
}