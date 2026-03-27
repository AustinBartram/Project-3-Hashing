/**
 * HashObject.java - Represents an object stored in the hash table,
 * containing the key, frequency of occurrences, and the number of probes required to insert it.
 * This class provides methods to access the key, increment the frequency, and set and get the probe count.
 * Author: Austin Bartram
 */
public class HashObject {
    
    private Object key;
    private int frequency;
    private int probeCount;

    /**
     * Constructor for HashObject class. Initializes the key, frequency, and probe count.
     * @param key The key to be stored in the hash table.
     * The frequency is initialized to 1, and the probe count is initialized to 0.
     */
    public HashObject(Object key) {
        this.key = key;
        this.frequency = 1;
        this.probeCount = 0;
    }

    /**
     * Returns the key stored in this HashObject.
     * @return
     */
    public Object getKey() {
        return key;
    }

    /**
     * Increments the frequency of occurrences for this key. This method should be called when a
     * duplicate key is inserted into the hash table.
     */
    public void incrementFrequency() {
        frequency++;
    }

    /**
     * Sets the probe count for this HashObject. This method should be called when the object is inserted
     * into the hash table to record the number of probes required for the insertion.
     * @param probeCount
     */
    public void setProbeCount(int probeCount) {
        this.probeCount = probeCount;
    }

    /**
     * Returns the number of probes required to insert this key into the hash table.
     * This value is set when the object is inserted.
     * @return The probe count for this HashObject.
     */
    public int getProbeCount() {
        return probeCount;
    }

    /**
     * Overrides the equals method to compare HashObjects based on their keys.
     * Two HashObjects are considered equal if their keys are equal. 
     * @param obj The object that is being compared for equality with this HashObject.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        HashObject other = (HashObject) obj;
        return this.key.equals(other.key);
    }

    /**
     * Overrides the toString method to provide a string representation of the HashObject, 
     * including the key, frequency, and probe count.
     * @return A string representation of the HashObject in the format "key frequency probeCount".
     */
    @Override
    public String toString() {
        return key + " " + frequency + " " + probeCount;
    }
}