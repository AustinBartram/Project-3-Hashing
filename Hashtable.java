import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * This is the abstract class for the hash table. It contains the common methods and 
 * variables for both linear probing and double hashing. It also contains the insert method
 * that is used to insert keys into the hash table. The insert method also keeps track of the 
 * number of probes and duplicates for each key. The search method is used for debugging.
 *  The dumpToFile method is used to dump the contents of the hash table to a file for 
 * debugging.
 * @author Austin Bartram
 */
public abstract class Hashtable {

    protected HashObject[] table;
    protected int capacity;
    protected double loadFactor;
    protected int count;
    protected int totalProbeCount;
    protected int totalDuplicates;

    /**
     * Constructor for hash table that initializes the table and sets the capacity and load factor.
     * @param capacity initial capacity of the hash table (should be a prime number)
     * @param loadFactor maximum load factor before insertions fail
     */
    public Hashtable(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = new HashObject[capacity];

        this.count = 0;
        this.totalProbeCount = 0;
        this.totalDuplicates = 0;
    }

    /**
     * Ensures positive modulus (handles negative hash codes)
     */
    protected int positiveMod(int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0)
            quotient += divisor;
        return quotient;
    }

    /**
     * Abstract hash function to be implemented by subclasses. Takes in key and probe number.
     */
    protected abstract int h(Object key, int i);

    /**
     * Insert method for hash table. Returns index of inserted key or -1 if insertion fails
     * (should not happen if load factor is respected).
     */
    public int insert(Object key) {
        int i = 0;
        int probeCount = 0;

        while (i < capacity) {
            int index = h(key, i);
            probeCount++;

            if (table[index] == null) {
                HashObject obj = new HashObject(key);
                obj.setProbeCount(probeCount);

                table[index] = obj;
                count++;
                totalProbeCount += probeCount;

                return index;

            } else if (table[index].getKey().equals(key)) {
                table[index].incrementFrequency();
                totalDuplicates++;
                return index;
            }

            i++;
        }
        return -1;
    }

    /**
     * Search method (used for debugging)
     * @param key
     */
    public HashObject search(Object key) {
        int i = 0;

        while (i < capacity) {
            int index = h(key, i);

            if (table[index] == null) {
                return null;
            }

            if (table[index].getKey().equals(key)) {
                return table[index];
            }

            i++;
        }

        return null;
    }

    /**
     * gets the number of unique keys in the hash table
     */
    public int getCount() {
        return count;
    }

    /**
     * gets the total number of duplicates found in the hash table
     */
    public int getTotalDuplicates() {
        return totalDuplicates;
    }

    /**
     * Gets the total number of probes for successful inserts
     */
    public int getTotalProbeCount() {
        return totalProbeCount;
    }

    /**
     * Get average number of probes per successful insert
     */
    public double getAverageProbes() {
        return (double) totalProbeCount / count;
    }

    /**
     * Dump contents of hash table to a file (used for debugging)
     */
    public void dumpToFile(String fileName) {
        try {
            PrintWriter out = new PrintWriter(fileName);

            for (int i = 0; i < capacity; i++) {
                if (table[i] != null) {
                    out.println("table[" + i + "]: " + table[i]);
                }
            }

            out.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error writing file: " + fileName);
        }
    }
}