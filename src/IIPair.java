
public class IIPair extends KVPair<Integer, Integer> implements Comparable<IIPair>{
    /**
     * Creates the SIPair object with a string key and integer value.
     * @param key The key for this pair.
     * @param value The value for this pair.
     */
    public IIPair(int key, int value) {
        super(key, value);
    }
    
    /**
     * Gets the key for this pair.
     * @return The integer key
     */
    public Integer getKey() {
        return super.getKey();
    }
    
    /**
     * Sets the key for the pair.
     * @param key The integer key for the pair.
     */
    public void setKey(int key) {
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

    /**
     * Compare this object to the object that is passed in and return the result.
     * Compares the keys first and if they are the same, compares the values.
     * Passing in an IIPair with value -1 indicates that it is a searching pair and
     * thus should not be comparing the values.
     * 
     * @param o The input IIPair to use.
     * @return 
     */
	@Override
	public int compareTo(IIPair o) {
		int retVal = getKey().compareTo(o.getKey());
		if (retVal == 0 && o.getValue() != -1) {
		    retVal = getValue().compareTo(o.getValue());
		}
		return retVal;
	}
}
