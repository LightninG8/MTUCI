public class HashTableEntry <T, K> {

    private T key;
    private K value;

    public HashTableEntry (T key, K value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public K getValue() {
        return value;
    }

    public void setValue (K value) {
        this.value = value;
    }

    public boolean equalsKeys(T a) {
        return (key.equals(a));
    }
}
