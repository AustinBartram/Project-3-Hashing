/**
 * This is the DoubleHashing class that extends the Hashtable class. It implements the double 
 * hashing collision resolution strategy. Double hashing uses two hash functions to calculate the 
 * index for a key.
 * @author Austin Bartram
 */
public class DoubleHashing extends Hashtable {

    /**
     * Constructor that initializes the hash table with the given capacity and load factor.
     * @param capacity the size of the hash table
     * @param loadFactor the load factor for resizing the hash table
     */
    public DoubleHashing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    /**
     * This is the double hashing function. It takes in the key and the probe number and returns the index.
     * @param key the key to be inserted
     * @param i the probe number (starts at 0 and increments for each probe)
     * @return the index to insert the key into the hash table
     */
    @Override
    protected int h(Object key, int i) {
        int hashCode = key.hashCode();

        int h1 = positiveMod(hashCode, capacity);
        int h2 = 1 + positiveMod(hashCode, capacity - 2);

        return positiveMod(h1 + i * h2, capacity);
    }
}