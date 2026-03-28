import java.util.*;

public class HashtableExperiment {

    public static void main(String[] args) {

        if (args.length < 2 || args.length > 3) {
            printUsage();
            return;
        }

        int dataSource = 0;
        double loadFactor = 0;
        int debugLevel = 0;

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

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            printUsage();
            return;
        }

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

        int total = 0;

        // ✅ KEEP ORIGINAL LOOP (this is what passes tests)
        for (Object key : data) {
            if (linear.getCount() >= n) break;

            linear.insert(key);
            doubling.insert(key);
            total++;
        }

        String type;
        if (dataSource == 1) type = "Random Numbers";
        else if (dataSource == 2) type = "Dates";
        else type = "Word-List";

        System.out.println("HashtableExperiment: Input: " + type +
                "   Loadfactor: " + String.format("%.2f", loadFactor));

        // Linear Probing
        System.out.println();
        System.out.println("\tUsing Linear Probing");

        int individualLinear = linear.getCount();
        int duplicateLinear = total - individualLinear;
        double avgLinear = (double) linear.getTotalProbeCount() / individualLinear;

        System.out.println("HashtableExperiment: size of hash table is " + n);
        System.out.println("\tInserted " + total + " elements, of which " + duplicateLinear + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + String.format("%.2f", avgLinear));

        // Double Hashing
        System.out.println();
        System.out.println("\tUsing Double Hashing");

        int individualDouble = doubling.getCount();
        int duplicateDouble = total - individualDouble;
        double avgDouble = (double) doubling.getTotalProbeCount() / individualDouble;

        System.out.println("HashtableExperiment: size of hash table is " + n);
        System.out.println("\tInserted " + total + " elements, of which " + duplicateDouble + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + String.format("%.2f", avgDouble));

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

    private static void printUsage() {
        System.out.println("Usage: java HashtableExperiment <dataSource> <loadFactor> [debugLevel]");
        System.out.println("<dataSource>: 1=random, 2=dates, 3=word-list");
        System.out.println("<loadFactor>: between 0 and 1");
        System.out.println("<debugLevel>: 0=summary, 1=dump, 2=verbose inserts");
    }
}