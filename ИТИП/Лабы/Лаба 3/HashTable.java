import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.AbstractMap;

public class HashTable <T, K> {

    private final int hashTableSize = 100;
    private boolean isEmpty;
    private int amountOfElements;
    public ArrayList<LinkedList<HashTableEntry<T, K> > > tableIndex;

    public HashTable() {

        isEmpty = true;
        amountOfElements = 0;
        tableIndex = new ArrayList <LinkedList<HashTableEntry<T, K> > >();
        for (int i = 0; i < hashTableSize; i++) {
            tableIndex.add(null);
        }
    }

    public void put (T key, K value) {
        int index = key.hashCode() % hashTableSize;

        isEmpty = false;
        if (tableIndex.get(index) == null) {
            tableIndex.set(index, new LinkedList<HashTableEntry<T, K>>());
        }
        for (HashTableEntry<T, K> entry : tableIndex.get(index)) {
            if (entry.equalsKeys(key)) {
                entry.setValue(value);
                return;
            }
        }
        amountOfElements += 1;
        tableIndex.get(index).add(new HashTableEntry<T, K>(key, value));

    }

    public K get (T key) {
        int index = key.hashCode() % hashTableSize;

        if (tableIndex.get(index) == null) {
            return null;
        }
        for (HashTableEntry<T, K> entry : tableIndex.get(index)) {
            if (entry.equalsKeys(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void remove (T key) {
        int index = key.hashCode() % hashTableSize;

        for (HashTableEntry<T, K> entry : tableIndex.get(index)) {
            if (entry.equalsKeys(key)) {
                amountOfElements -= 1;
                tableIndex.get(index).remove(entry);
            }
        }
        if (amountOfElements == 0) {
            isEmpty = true;
        }
    }

    public int size() {
        return this.amountOfElements;
    }

    public boolean isEmpty() {
        return this.isEmpty;
    }
}
