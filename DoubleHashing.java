public class DoubleHashing extends Hashtable {

    public DoubleHashing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    @Override
    protected int h(Object key, int i) {
        int hashCode = key.hashCode();

        int h1 = positiveMod(hashCode, capacity);
        int h2 = 1 + positiveMod(hashCode, capacity - 2);

        return positiveMod(h1 + i * h2, capacity);
    }
}