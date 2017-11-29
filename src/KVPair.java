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
public class KVPair {
    private String key;
    private int value;

    /**
     * Initializes this KVPair.
     * @param initKey The initial value of the key.
     * @param initVal The initial value of the value.
     */
    public KVPair(String initKey, int initVal) {
        setKey(initKey);
        setValue(initVal);
    }

    /**
     * Gets the stored key.
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key.
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the stored value.
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value.
     * @param value
     *            the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }
}
