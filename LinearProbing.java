/**
 * This is the LinearProbing class that extends the Hashtable class. 
 * It implements the linear probing collision. 
 * @author Austin Bartram
 */
public class LinearProbing extends Hashtable {

    /**
     * Constructor that initializes the hash table with the given capacity and load factor.
     * @param capacity the size of the hash table
     * @param loadFactor the load factor for resizing the hash table
     */
    public LinearProbing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    /**
     * this is the linear probing function. It takes in the key and the probe number and returns the index
     * to insert the key into the hash table. The index is calculated by taking the hash code of the key,
     * adding the probe number, and taking the modulus with the capacity of the hash table.
     * @param key the key to be inserted
     * @param i the probe number (starts at 0 and increments for each probe)
     * @return the index to insert the key into the hash table
     */
    @Override
    protected int h(Object key, int i) {
        return positiveMod(
                positiveMod(key.hashCode(), capacity) + i,
                capacity
        );
    }
}