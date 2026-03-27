public abstract class Hashtable {

    protected HashObject[] table;
    protected int capacity;
    protected double loadFactor;
    protected int count;
    protected int totalProbeCount;

    public Hashtable(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = new HashObject[capacity];
        this.count = 0;
        this.totalProbeCount = 0;
    }

    protected int positiveMod(int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0)
            quotient += divisor;
        return quotient;
    }

    protected abstract int h(Object key, int probe);

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

    public int getTotalProbeCount() {
        return totalProbeCount;
    }

    public int getCount() {
        return count;
    }
}