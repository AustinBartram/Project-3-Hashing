import java.util.*;
/**
 * This is the main class for the hashtable experiment. It takes in the user input for the data source, 
 * load factor, and debug level. Then it generates a twin prime number for the hash table capacity. 
 * Then it creates two hash tables, one for linear probing and one for double hashing. Then it inserts 
 * the data into the hash tables and calculates the number of individual keys, duplicates, and average 
 * number of probes.
 * @author Austin Bartram
 */
public class HashtableExperiment {

    public static void main(String[] args) {

        /**
         * this is the main argument handling for the program as it checks to see if the user
         * input is valid. And the it checks to see if the number of arguments is correct. 
         */
        if (args.length < 2 || args.length > 3) {
            printUsage();
            return;
        }

        int dataSource = 0;
        double loadFactor = 0;
        int debugLevel = 0;

        /**
         * this is the error handling for the program. It checks to see if the user input is valid.
         *  If it is not, it will throw an exception and print the error message. Then it will also 
         * print the usage of the program.
         * @param args
         * @return args
         */
        try {
            dataSource = Integer.parseInt(args[0]);
            if (dataSource < 1 || dataSource > 3) {
                throw new IllegalArgumentException("dataSource must be 1, 2, or 3");
            }

            loadFactor = Double.parseDouble(args[1]);
            if (loadFactor < 0 || loadFactor > 1) {
                throw new IllegalArgumentException("loadFactor must be between 0 and 1");
            }

            if (args.length == 3) {
                debugLevel = Integer.parseInt(args[2]);
                if (debugLevel < 0 || debugLevel > 2) {
                    throw new IllegalArgumentException("debugLevel must be 0, 1, or 2");
                }
            }

        /**
         * this is the error handling for the program. If the user inputs an invalid argument, 
         * it will catch the exception and print the error message. Then it will also print the 
         * usage of the program.
         */
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            printUsage();
            return;
        }

        /**
         * this is the part where we generate the twin prime number for the hash table capacity. 
         * We use the TwinPrimeGenerator class to find a twin prime number between 95500 and 96000. 
         * This is because we want to have a hash table capacity that is a prime number to reduce 
         * the chances of collisions.
         * @param m
         * @return m
         */
        int m = TwinPrimeGenerator.generateTwinPrime(95500, 96000);
        System.out.println("HashtableExperiment: Found a twin prime table capacity: " + m);

        int n = (int) Math.ceil(loadFactor * m);

        LinearProbing linear = new LinearProbing(m, loadFactor);
        DoubleHashing doubling = new DoubleHashing(m, loadFactor);

        ArrayList<Object> data = new ArrayList<>();

        /**
         * this is the data source for the program. The first is random numbers, 
         * the second is dates, and the third is a word list.
         */
        if (dataSource == 1) {
            Random rand = new Random();
            while (data.size() < n) {
                data.add(rand.nextInt());
            }

        } else if (dataSource == 2) {
            long current = new Date().getTime();
            while (data.size() < n) {
                data.add(new Date(current));
                current += 1000;
            }

        } else {
            try {
                Scanner scan = new Scanner(new java.io.File("word-list.txt"));
                while (scan.hasNextLine()) {
                data.add(scan.nextLine());
            }
                scan.close();
            } catch (Exception e) {
                System.out.println("Error reading file.");
                return;
            }
        }

        int total = 0;

        for (Object key : data) {
            if (linear.getCount() >= n) break;

            linear.insert(key);
            doubling.insert(key);
            total++;
        }
        /**
         * this is the output for random numbers and dates. 
         * @param dataSource
         * @param loadFactor
         */
        String type;
        if (dataSource == 1) type = "Random Numbers";
        else if (dataSource == 2) type = "Dates";
        else type = "Word-List";

        System.out.println("HashtableExperiment: Input: " + type +
                "   Loadfactor: " + String.format("%.2f", loadFactor));

        /**
         * this is the output for linear problng. this calculate the number of the individual keys. 
         * Then the number of duplicates. 
         */
        System.out.println();
        System.out.println("\tUsing Linear Probing");

        int individualLinear = linear.getCount();
        int duplicateLinear = total - individualLinear;
        double avgLinear = (double) linear.getTotalProbeCount() / individualLinear;

        System.out.println("HashtableExperiment: size of hash table is " + n);
        System.out.println("\tInserted " + total + " elements, of which " + duplicateLinear + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + String.format("%.2f", avgLinear));

        /**
         * this is the output for double hashing. it calculates the number of individual keys
         * that are being inserted. Then the number of duplicates.
         */
        System.out.println();
        System.out.println("\tUsing Double Hashing");
        int individualDouble = doubling.getCount();
        int duplicateDouble = total - individualDouble;
        double avgDouble = (double) doubling.getTotalProbeCount() / individualDouble;

        System.out.println("HashtableExperiment: size of hash table is " + n);
        System.out.println("\tInserted " + total + " elements, of which " + duplicateDouble + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + String.format("%.2f", avgDouble));

        /**
         * this is the output for the debug levels. This is the first then in increments to the second. 
         * the first debug level dumps the contents of the hash tables to a file. Then the second is 
         * to print the inserts. 
         */
        if (debugLevel == 1) {
            linear.dumpToFile("linear-dump.txt");
            doubling.dumpToFile("double-dump.txt");
            System.out.println("HashtableExperiment: Saved dump of hash table");

        } else if (debugLevel == 2) {
            System.out.println();
            System.out.println("Debug level 2: printing inserts");

            for (Object key : data) {
                System.out.println("Inserted: " + key);
            }
        }
    }

    /**
     * this prints the usage of the whole program. Mainly used for error handling for when the user
     * chooses the wrong number of arguments. And then it also checks to see if the arguments are valid. 
     */
    private static void printUsage() {
        System.out.println("Usage: java HashtableExperiment <dataSource> <loadFactor> [debugLevel]");
        System.out.println("<dataSource>: 1=random, 2=dates, 3=word-list");
        System.out.println("<loadFactor>: between 0 and 1");
        System.out.println("<debugLevel>: 0=summary, 1=dump, 2=inserts");
    }
}