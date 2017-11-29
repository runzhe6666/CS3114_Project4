/**
 * 
 */

/**
 * A tuple object for the trees to use in their manipulations. Solves the
 * problem of multiple returns for returning from a function.
 * 
 * @author Samuel Turner <samt5>
 * @version 2017.10.17
 * @param <J> The first type.
 * @param <K> The second type.
 */
public class Tuple<J, K> {
    /**
     * The fist value that is stored in this Tuple
     */
    private J value1;
    /**
     * The second value that is stored in this Tuple
     */
    private K value2;

    /**
     * Creates the object with two elements
     * @param val1 Element 1
     * @param val2 Element 2
     */
    public Tuple(J val1, K val2) {
        value1 = val1;
        value2 = val2;
    }
    
    /**
     * @return the value1
     */
    public J getValue1() {
        return value1;
    }

    /**
     * @param value1
     *            the value1 to set
     */
    public void setValue1(J value1) {
        this.value1 = value1;
    }

    /**
     * @return the value2
     */
    public K getValue2() {
        return value2;
    }

    /**
     * @param value2
     *            the value2 to set
     */
    public void setValue2(K value2) {
        this.value2 = value2;
    }
}
