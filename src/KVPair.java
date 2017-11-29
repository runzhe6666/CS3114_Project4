/**
 * Stores pairs of keys and values. Values are set and retrieved using the
 * provided getter and setter methods.
 * 
 * @author Samuel Turner <samt5>
 * @version 2017.11.15
 * 
 * @param <K>
 *            The type that the key should be.
 * @param <V>
 *            The type that the value should be.
 */
public class KVPair<K extends Comparable<K>, V> {
    private K key;
    private V value;

    /**
     * Initializes this KVPair.
     * @param initKey The initial value of the key.
     * @param initVal The initial value of the value.
     */
    public KVPair(K initKey, V initVal) {
        setKey(initKey);
        setValue(initVal);
    }

    /**
     * Gets the stored key.
     * @return the key
     */
    public K getKey() {
        return key;
    }

    /**
     * Sets the key.
     * @param key
     *            the key to set
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Gets the stored value.
     * @return the value
     */
    public V getValue() {
        return value;
    }

    /**
     * Sets the value.
     * @param value
     *            the value to set
     */
    public void setValue(V value) {
        this.value = value;
    }
}
