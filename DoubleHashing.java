/**
 * DoubleHashing.java - Implements double hashing for collision resolution in a hash table.
 * This class extends the abstract Hashtable class and provides a specific implementation
 * of the hash function using two hash functions (h1 and h2) to calculate the index for a
 * given key and probe number. The first hash function (h1) is based on the key's hash
 * code and the table's capacity, while the second hash function (h2) is designed to ensure
 * that it is non-zero.
 * 
 * Author: Austin Bartram
 */
public class DoubleHashing extends Hashtable {

    /**
     * Constructor for DoubleHashing class. Initializes the hash table with the specified capacity
     * and load factor.
     * @param capacity
     * @param loadFactor
     */
    public DoubleHashing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    /**
     * Implements the double hashing function to calculate the index for a given key and probe number.
     * The first hash function (h1) is based on the key's hash code and the table's capacity,
     *  while the second hash function (h2) is designed to ensure that it is non-zero.
     * @param key The key for which the index is to be calculated.
     * @param i The probe number, which is used to calculate the index in case of collisions.
     * @return The calculated index for the given key and probe number.
     */
    @Override
    protected int h(Object key, int i) {
        int hashCode = key.hashCode();

        int h1 = positiveMod(hashCode, capacity);
        int h2 = 1 + positiveMod(hashCode, capacity - 2);

        return positiveMod(h1 + i * h2, capacity);
    }
}