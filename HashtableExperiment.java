import java.util.*;

public class HashtableExperiment {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Usage: java HashtableExperiment <dataSource> <loadFactor> [debugLevel]");
            return;
        }

        int dataSource = Integer.parseInt(args[0]);
        double loadFactor = Double.parseDouble(args[1]);
        int debugLevel = (args.length >= 3) ? Integer.parseInt(args[2]) : 0;

        int m = TwinPrimeGenerator.generateTwinPrime(95500, 96000);
        System.out.println("HashtableExperiment: Found a twin prime table capacity: " + m);

        int n = (int) Math.ceil(loadFactor * m);

        LinearProbing linear = new LinearProbing(m, loadFactor);
        DoubleHashing doubling = new DoubleHashing(m, loadFactor);

        ArrayList<Object> data = new ArrayList<>();

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

        /**
         * Inserts the keys from the data source into both the linear probing and double hashing hash tables.
         * The loop continues until the number of unique keys inserted into the linear probing hash table reaches n.
         */
        int total = 0;

        for (Object key : data) {
            if (linear.getCount() >= n) break;

            linear.insert(key);
            doubling.insert(key);
            total++;
        }

        /**
         * Prints the results of the experiment, including the type of data source used, the load factor,
         * the size of the hash table, the number of elements inserted, the number of duplicates, and the average
         * number of probes for both linear probing and double hashing. The output is formatted to match the 
         * assignment specifications, with the data source type, and load factor.
         */
        String type;
        if (dataSource == 1) type = "Random Numbers";
        else if (dataSource == 2) type = "Dates";
        else type = "Word-List";

        System.out.println("HashtableExperiment: Input: " + type + "   Loadfactor: " + String.format("%.2f", loadFactor));

        /**
         * Prints the results for linear probing, including the size of the hash table, the number of elements inserted,
         * the number of duplicates, and the average number of probes. The output is formatted to match the assignment specifications.
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
         * Prints the results for double hashing, including the size of the hash table, the number of elements inserted,
         * the number of duplicates, and the average number of probes. The output is formatted to match the assignment specifications.
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
         * Prints debug information based on the specified debug level. If the debug level is 1, it indicates that 
         * the dump of the hash table is disabled. If the debug level is 2, it prints the keys that were inserted into the hash table. 
         * The output is formatted to match the assignment specifications.
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
}