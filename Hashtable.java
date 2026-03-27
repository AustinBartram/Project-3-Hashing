import java.io.PrintWriter;

/**
 * Abstract class representing a hash table. It provides the basic structure and common 
 * methods for hash tables, including insertion and searching of keys, as well as counting
 * the total number of probes.
 * Author: Austin Bartram
 */
public abstract class Hashtable {

    protected HashObject[] table;
    protected int capacity;
    protected double loadFactor;
    protected int count;
    protected int totalProbeCount;

    /**
     * Constructor for the Hashtable class. Initializes the hash table with the specified capacity 
     * and load factor.
     * @param capacity The size of the hash table.
     * @param loadFactor The load factor that determines when the hash table should be resized.
     */
    public Hashtable(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = new HashObject[capacity];
        this.count = 0;
        this.totalProbeCount = 0;
    }

    /**
     * Helper method to compute the positive modulus of a dividend and divisor. This is used to ensure
     * that the result of the modulus operation is always non-negative, which is important for calculating
     * hash quotients in the hash table.
     * @param dividend
     * @param divisor
     * @return The positive modulus of the dividend and divisor.
     */
    protected int positiveMod(int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0)
            quotient += divisor;
        return quotient;
    }

    /**
     * Abstract method to calculate the index for a given key and probe number.
     * @param key
     * @param probe
     * @return
     */
    protected abstract int h(Object key, int probe);

    /**
     * Inserts a key into the hash table. This method uses the hash function defined by the subclass
     * to determine the index for the key and handles collisions according to the specific collision
     * resolution strategy implemented by the subclass.
     * @param key The key to be inserted into the hash table.
     */
    public void insert(Object key) {

        int i = 0;
        int probeCountForThisInsert = 0;

        while (i < capacity) {
            int index = h(key, i);
            probeCountForThisInsert++;

            if (table[index] == null) {
                HashObject obj = new HashObject(key);
                obj.setProbeCount(probeCountForThisInsert);

                table[index] = obj;
                count++;
                totalProbeCount += probeCountForThisInsert;
                return;

            } else if (table[index].getKey().equals(key)) {
                table[index].incrementFrequency();
                return;
            }

            i++;
        }
    }

    /**
     * Searches for a key in the hash table and returns the corresponding HashObject if found,
     * or null if not found.
     * @param key
     * @return The HashObject associated with the key if found, or null if the key is not in the hash table.
     */
    public HashObject search(Object key) {
        int i = 0;

        while (i < capacity) {
            int index = h(key, i);

            if (table[index] == null) {
                return null;
            } else if (table[index].getKey().equals(key)) {
                return table[index];
            }

            i++;
        }

        return null;
    }

    /**
     * Returns the total number of probes that have been made during insertions into the hash table.
     * @return The total probe count for all insertions into the hash table.
     */
    public int getTotalProbeCount() {
        return totalProbeCount;
    }

    /**
     * Returns the number of keys currently stored in the hash table.
     * @return The count of keys currently stored in the hash table.
     */
    public int getCount() {
        return count;
    }

    /**
     * Dumps the contents of the hash table to a file. Each line in the output file will contain the index
     * of the hash table and the corresponding HashObject (key, frequency, probe count).
     * @param fileName
     * @throws Exception
     */
    public void dumpToFile(String fileName) {
        try {
            PrintWriter out = new PrintWriter(fileName);

            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    out.println("table[" + i + "]: " + table[i]);
                }
            }

            out.close();
        } catch (Exception e) {
            System.out.println("Error writing file");
        }
    }
}