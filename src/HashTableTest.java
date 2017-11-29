/**
 * 
 * @author Samuel Turner <samt5>
 * @version 2017.
 * 
 */
public class HashTableTest extends student.TestCase {
    private HashTable table1;
    private HashTable table2;
    private SIPair p1;
    private SIPair p2;
    private SIPair p3;
    private SIPair p4;
    private SIPair p5;
    private SIPair p6;
    private SIPair p7;

    public void setUp() {
        table1 = new HashTable(10);
        table2 = new HashTable(6);
        p1 = new SIPair("a", 10);
        p2 = new SIPair("b", 15);
        p3 = new SIPair("c", 0);
        p4 = new SIPair("d", 23);
        p5 = new SIPair("e", 4);
        p6 = new SIPair("f", 40);
        p7 = new SIPair("g", 42);
    }

    /**
     * Tests the simple functionality of the add function.
     */
    public void testAdd() {
        table1.add(p1.getKey(), p1.getValue());
        assertEquals(10, table1.getCapacity());
        assertEquals(1, table1.getNumElements());
        table1.add(p2.getKey(), p2.getValue());
        table1.add(p3.getKey(), p3.getValue());
        table1.add(p4.getKey(), p4.getValue());
        p5.setKey("l");
        table1.add(p5.getKey(), p5.getValue());
        assertEquals(10, table1.getCapacity());
        assertEquals(5, table1.getNumElements());
        table1.add(p6.getKey(), p6.getValue());
        assertEquals(20, table1.getCapacity());
        assertEquals(6, table1.getNumElements());

        // Check that the values are all copied correctly.
        assertEquals(p1.getValue().intValue(), table1.getHandle(p1.getKey()));
        assertEquals(p2.getValue().intValue(), table1.getHandle(p2.getKey()));
        assertEquals(p3.getValue().intValue(), table1.getHandle(p3.getKey()));
        assertEquals(p4.getValue().intValue(), table1.getHandle(p4.getKey()));
        assertEquals(p5.getValue().intValue(), table1.getHandle(p5.getKey()));
        assertEquals(p6.getValue().intValue(), table1.getHandle(p6.getKey()));

        // Check adding after removal.
        assertEquals(table1.remove(p1.getKey()), p1.getValue().intValue());
        p7.setKey("a");
        table1.add(p7.getKey(), p7.getValue());
        assertEquals(6, table1.getNumElements());
        assertEquals(p7.getValue().intValue(), table1.getHandle(p7.getKey()));
    }

    /**
     * Tests the add function when there are multiple collisions and removals.
     */
    public void testAdd2() {
        table1.add(p1.getKey(), p1.getValue());
        p2.setKey(genCollision('a', 10, 1));
        p3.setKey(genCollision('a', 10, 2));
        table1.add(p2.getKey(), p2.getValue());
        table1.add(p3.getKey(), p3.getValue());

        assertEquals(p1.getValue().intValue(), table1.getHandle(p1.getKey()));
        assertEquals(p2.getValue().intValue(), table1.getHandle(p2.getKey()));
        assertEquals(p3.getValue().intValue(), table1.getHandle(p3.getKey()));
        
    }

    /**
     * Helps with collision testing by generating strings that will collide
     * assuming the string is 1 character long.
     * 
     * @return The colliding string.
     */
    private String genCollision(char initCharacter, int tableSize,
            int collisionNumber) {
        return (char) ((int) initCharacter + (collisionNumber * tableSize))
                + "";
    }
}
