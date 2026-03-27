public class LinearProbing extends Hashtable {

    public LinearProbing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    @Override
    protected int h(Object key, int i) {
        int hashCode = key.hashCode();
        int h1 = positiveMod(hashCode, capacity);

        return positiveMod(h1 + i, capacity);
    }
}