package idgenerator;

import java.util.ArrayList;

public class PrimeGenerator {
    public PrimeGenerator(long lowerBound, long upperBound) {
        if (lowerBound < 1 || lowerBound == Long.MAX_VALUE)
            throw new IllegalArgumentException("The lower boundary must be a natural number!");

        if (upperBound <= 1 || upperBound == Long.MAX_VALUE)
            throw new IllegalArgumentException("The upper boundary must be a natural number!");

        if (lowerBound >= upperBound)
            throw new IllegalArgumentException("The lower boundary must be lower than the upper boundary!");

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.primeFactors = new ArrayList<>();

        initialize();
    }

    private void initialize() {
        primeFactors.add(2L);
        for (long i = 3; i <= (Math.sqrt(upperBound) + 1); ++i) {
            if (isPrime(i))
                primeFactors.add(i);
        }
    }

    public long getNextPrime() {
        long start = Math.max(lastGeneratedPrime, lowerBound - 1);

        if (start == 0) {
            lastGeneratedPrime = 2;
            return lastGeneratedPrime;
        }

        if (start % 2 == 0) {
            start += 1;
        } else {
            start += 2;
        }

        return getNextPrimeFrom(start);
    }

    private long getNextPrimeFrom(long number) {
        while (true) {
            if (number > upperBound)
                throw new IndexOutOfBoundsException("All primes in the interval are generated already!");

            if (isPrime(number)) {
                lastGeneratedPrime = number;
                return lastGeneratedPrime;
            }
            number += 2;
        }
    }

    private boolean isPrime(long number) {
        double checkLimit = Math.sqrt(number);

        for (long prime : primeFactors) {
            if (prime == number)
                return true;

            if (prime > checkLimit)
                return true;

            if (number % prime == 0) {
                return false;
            }
        }
        return true;
    }

    private long lowerBound;
    private long upperBound;

    private ArrayList<Long> primeFactors;
    private long lastGeneratedPrime;
}
