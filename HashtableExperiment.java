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

        // ✅ FIX: updated constructors
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
                while (scan.hasNextLine() && data.size() < n) {
                    data.add(scan.nextLine());
                }
                scan.close();
            } catch (Exception e) {
                System.out.println("Error reading file.");
                return;
            }
        }

        // insert into both tables
        for (Object key : data) {
            linear.insert(key);
            doubling.insert(key);
        }

        // input label
        String type;
        if (dataSource == 1) type = "Random Numbers";
        else if (dataSource == 2) type = "Dates";
        else type = "Word-List";

        System.out.println("HashtableExperiment: Input: " + type +
                "   Loadfactor: " + String.format("%.2f", loadFactor));

        int total = data.size();

        // ----- Linear Probing -----
        System.out.println();
        System.out.println("\tUsing Linear Probing");

        int individualLinear = linear.getCount();
        int duplicateLinear = total - individualLinear;
        double avgLinear = (double) linear.getTotalProbeCount() / individualLinear;

        System.out.println("HashtableExperiment: size of hash table is " + n);
        System.out.println("\tInserted " + total + " elements, of which " + duplicateLinear + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + String.format("%.2f", avgLinear));

        // ----- Double Hashing -----
        System.out.println();
        System.out.println("\tUsing Double Hashing");

        int individualDouble = doubling.getCount();
        int duplicateDouble = total - individualDouble;
        double avgDouble = (double) doubling.getTotalProbeCount() / individualDouble;

        System.out.println("HashtableExperiment: size of hash table is " + n);
        System.out.println("\tInserted " + total + " elements, of which " + duplicateDouble + " were duplicates");
        System.out.println("\tAvg. no. of probes = " + String.format("%.2f", avgDouble));

        // debug levels
        if (debugLevel == 1) {
            System.out.println("Debug level 1: dump disabled");

        } else if (debugLevel == 2) {
            System.out.println();
            System.out.println("Debug level 2: printing inserts");

            for (Object key : data) {
                System.out.println("Inserted: " + key);
            }
        }
    }
}