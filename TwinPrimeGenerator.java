/**
 * This is the TwinPrimeGenerator class that generates twin primes within a specified range.
 * It contains a method to generate the larger twin prime in the first pair found between min and
 * max. It also contains a helper method to check if a number is prime.
 * @author Austin Bartram
 */
public class TwinPrimeGenerator {

    private TwinPrimeGenerator() {}

    /**
     * Generates the larger twin prime in the first pair found between min and max.
     * @param min the minimum value to start searching for twin primes (inclusive)
     * @param max the maximum value to search for twin primes (inclusive)
     * @return the larger twin prime found in the range
     */
    public static int generateTwinPrime(int min, int max) {

        /**
         * This method checks if a number is prime. It uses an efficient algorithm that checks 
         * for divisibility.
         */
        if (min % 2 == 0) {
            min++;
        }

        for (int i = min; i <= max - 2; i += 2) {
            if (isPrime(i) && isPrime(i + 2)) {
                return i + 2; // return the larger twin prime
            }
        }

        throw new RuntimeException("No twin prime found in range");
    }

    /**
     * Checks if a number is prime. A number is prime if it is greater than 1 and has no positive 
     * divisors other than 1 and itself.
     * @param num the number to check for primality
     * @return true if the number is prime, false otherwise
     */
    private static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;

        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}