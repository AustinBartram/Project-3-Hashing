public class HashObject {

    private Object key;
    private int frequency;
    private int probeCount;

    public HashObject(Object key) {
        this.key = key;
        this.frequency = 1;
        this.probeCount = 0;
    }

    public Object getKey() {
        return key;
    }

    public void incrementFrequency() {
        frequency++;
    }

    public void setProbeCount(int probeCount) {
        this.probeCount = probeCount;
    }

    public int getProbeCount() {
        return probeCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        HashObject other = (HashObject) obj;
        return this.key.equals(other.key);
    }

    @Override
    public String toString() {
        return key + " " + frequency + " " + probeCount;
    }
}