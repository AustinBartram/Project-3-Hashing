public class TwinPrimeGenerator {

    private TwinPrimeGenerator() {
        // prevents instantiation
    }

    public static int generateTwinPrime(int min, int max) {

        if (min % 2 == 0) {
            min++;
        }

        for (int i = min; i <= max; i += 2) {
            if (isPrime(i) && isPrime(i - 2)) {
                return i;
            }
        }

        throw new RuntimeException("No twin prime found in range");
    }

    private static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0)
                return false;
        }

        return true;
    }
}