/**
 * Contains the locations within the database in which the associated string is
 * placed. Capable of comparing it's value with another and determining which is
 * larger with the contained compareTo method.
 * 
 * @author Samuel Turner <samt5>
 * @version 2017.12.04
 *
 */
public class Handle implements Comparable<Handle> {

    private int location; // start location of a record

    /**
     * Instantiates the Handle object.
     * 
     * @param locIn
     *            The location in the database that this handle corresponds to.
     */
    public Handle(int locIn) {
        location = locIn;
    }

    /**
     * Gets the value of this handle.
     * 
     * @return The value contained in the Handle
     */
    public int getValue() {
        return location;
    }

    /**
     * Sets the value of this handle.
     * 
     * @param locIn
     *            The value to set.
     */
    public void setValue(int locIn) {
        location = locIn;
    }

    /**
     * Compares this Handle to the input handle.
     * 
     * @param other
     *            The handle to compare to.
     */
    @Override
    public int compareTo(Handle other) {
        Integer a = new Integer(getValue());
        Integer b = new Integer(other.getValue());
        return a.compareTo(b);
    }

    /**
     * Overrides the provided toString for output purposes.
     * 
     * @return The value in this Handle as a string.
     */
    public String toString() {
        return location + "";
    }
}
