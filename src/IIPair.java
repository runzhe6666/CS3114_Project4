
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
     * if key and value matches, returns 1
     * else return 0
     * 
     */
	@Override
	public int compareTo(IIPair o) {
		if (o.getKey() == getKey() && o.getValue() == getValue()){
			return 1;
		}
		
		return 0;
	}
}
