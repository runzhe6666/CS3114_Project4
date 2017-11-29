import student.TestCase;

/**
 * Tests the tuple class for web-cat. Tuple really doesn't do anything so this
 * test class is very simple.
 * 
 * @author Samuel Turner <samt5>
 * @version 2017.10.20
 *
 */
public class TupleTest extends TestCase {
    private Tuple<String, String> tuple;

    /**
     * Init the test env
     */
    public void setUp() {
        tuple = new Tuple<>("Hi", "How are you?");
    }

    /**
     * Tests the getters
     */
    public void testGetters() {
        assertEquals("Hi", tuple.getValue1());
        assertEquals("How are you?", tuple.getValue2());
    }

    /**
     * Tests the setters
     */
    public void testSetters() {
        tuple.setValue1("How are you?");
        tuple.setValue2("Hi");
        assertEquals("Hi", tuple.getValue2());
        assertEquals("How are you?", tuple.getValue1());
    }
}
