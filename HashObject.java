/**
 * This is the HashObject class that represents an object stored in the hash table. 
 * It contains the key, frequency, and probe count for each object. The frequency is 
 * used to keep track of how many times a duplicate key has been inserted into the hash table. 
 * The probe count is used to keep track of how many probes it took to insert the object into the hash table.
 * @author Austin Bartram
 */
public class HashObject {

    private Object key;
    private int frequency;
    private int probeCount;

    /**
     * Constructor that initializes the key, frequency, and probe count. 
     * Frequency starts at 1 since the object is being inserted for the first time.
     * @param key the key to be stored in the hash table
     */
    public HashObject(Object key) {
        this.key = key;
        this.frequency = 1;
        this.probeCount = 0;
    }

    /**
     * Returns the key
     */
    public Object getKey() {
        return key;
    }

    /**
     * Increments duplicate count
     */
    public void incrementFrequency() {
        frequency++;
    }

    /**
     * Returns frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Sets probe count 
     */
    public void setProbeCount(int probeCount) {
        this.probeCount = probeCount;
    }

    /**
     * Gets probe count 
     */
    public int getProbeCount() {
        return probeCount;
    }

    /**
     * Equality based on keys. Two HashObjects are equal if their keys are equal.
     * @param obj the object to compare with
     * @return true if the keys are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        HashObject other = (HashObject) obj;
        return key.equals(other.key);
    }

    /**
     * String representation for the hash table. 
     */
    @Override
    public String toString() {
        return key + " " + frequency + " " + probeCount;
    }
}