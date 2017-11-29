
public class SIPair extends KVPair<String, Integer> {
    /**
     * Creates the SIPair object with a string key and integer value.
     * @param key The key for this pair.
     * @param value The value for this pair.
     */
    public SIPair(String key, int value) {
        super(key, value);
    }
    
    /**
     * Gets the key for this pair.
     * @return The string key
     */
    public String getKey() {
        return super.getKey();
    }
    
    /**
     * Sets the key for the pair.
     * @param key The string key for the pair.
     */
    public void setKey(String key) {
        super.setKey(key);
    }
    
    /**
     * Gets the value of the pair.
     * @return The pair's value.
     */
    public Integer getValue() {
        return super.getValue();
    }

    /**
     * Sets the value of the pair.
     * @param value The integer value for the pair.
     */
    public void setValue(int value) {
        super.setValue(value);
    }
}
