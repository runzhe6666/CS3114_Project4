/**
 * A string, handle pair for the hash table.
 * 
 * @author Samuel Turner <samt5>
 * @version 2017.12.04
 *
 */
public class SIPair extends KVPair<String, Handle> {
    /**
     * Creates the SIPair object with a string key and integer value.
     * 
     * @param key
     *            The key for this pair.
     * @param value
     *            The value for this pair.
     */
    public SIPair(String key, Handle value) {
        super(key, value);
    }

    /**
     * Gets the key for this pair.
     * 
     * @return The string key
     */
    public String getKey() {
        return super.getKey();
    }

    /**
     * Sets the key for the pair.
     * 
     * @param key
     *            The string key for the pair.
     */
    public void setKey(String key) {
        super.setKey(key);
    }

    /**
     * Gets the value of the pair.
     * 
     * @return The pair's value.
     */
    public Handle getValue() {
        return super.getValue();
    }

    /**
     * Sets the value of the pair.
     * 
     * @param value
     *            The integer value for the pair.
     */
    public void setValue(Handle value) {
        super.setValue(value);
    }
}
