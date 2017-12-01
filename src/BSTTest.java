
/**
 * Test the BST's behavior along with the behavior of the Iterator defined
 * within the BST class.
 * 
 * @author Samuel Turner <samt5>
 * @version 2017.09.19
 */
public class BSTTest extends student.TestCase {
    private BST<> bst;
    private Rectangle r1;
    private Rectangle r2;
    private Rectangle r3;
    private Rectangle r4;
    private Rectangle r5;
    private Rectangle r6;
    private Rectangle r7;

    /**
     * Initializes all of the common variables across the various test methods.
     */
    public void setUp() {
        bst = new BST<Rectangle>();
        r1 = new Rectangle("a", 10, 10, 100, 100);
        r2 = new Rectangle("b", 20, 20, 200, 200);
        r3 = new Rectangle("c", 50, 50, 300, 100);
        r4 = new Rectangle("d", 200, 200, 50, 50);
        r5 = new Rectangle("e", 250, 210, 10, 50);
        r6 = new Rectangle("f", 300, 100, 20, 50);
        r7 = new Rectangle("g", 100, 500, 50, 10);
    }

    /**
     * Ensures that isEmpty returns properly.
     */
    public void testIsEmpty() {
        assertTrue(bst.isEmpty());
        bst.insert(r1);
        assertFalse(bst.isEmpty());
    }

    /**
     * Ensures that makeEmpty clears the tree.
     */
    public void testMakeEmpty() {
        bst.makeEmpty();
        assertTrue(bst.isEmpty());
        bst.insert(r1);
        assertFalse(bst.isEmpty());
        bst.makeEmpty();
        assertTrue(bst.isEmpty());
    }

    /**
     * Tests the variations on what can happen with insert. Performed by filling
     * the tree with specific values to force specific behavior.
     */
    public void testInsert() {
        bst.insert(null);
        assertNull(bst.find(null));
        assertTrue(bst.isEmpty());
        bst.insert(r2);
        assertFalse(bst.isEmpty());
        // Test where the name is the same
        Rectangle temp = new Rectangle("b", 1, 1, 1, 1);
        assertEquals(bst.find(temp), r2);
        assertEquals(bst.find(r2), r2);
        bst.insert(r1);
        assertFalse(bst.isEmpty());
        temp = new Rectangle("a", 1, 1, 1, 1);
        assertEquals(bst.find(temp), r1);
        assertEquals(bst.find(r1), r1);
        bst.insert(r3);
        assertFalse(bst.isEmpty());
        temp = new Rectangle("c", 1, 1, 1, 1);
        assertEquals(bst.find(temp), r3);
        assertEquals(bst.find(r3), r3);
        bst.insert(r4);
        assertFalse(bst.isEmpty());
        temp = new Rectangle("d", 1, 1, 1, 1);
        assertEquals(bst.find(temp), r4);
        assertEquals(bst.find(r4), r4);
        bst.insert(r2);
        assertFalse(bst.isEmpty());
        temp = new Rectangle("b", 1, 1, 1, 1);
        assertEquals(bst.find(temp), r2);
        assertEquals(bst.find(r2), r2);
        bst.insert(r4);
        bst.remove(r4);
        assertEquals(bst.find(r4), r4);
        bst.insert(null);
        assertNull(bst.find(null));
    }

    /**
     * Tests the variations on what can happen with remove for the BST.
     */
    public void testRemove() {
        // Tests the action when the root has and another element logically
        // share the same data.
        bst.insert(r4);
        bst.insert(r2);
        bst.insert(r3);
        bst.insert(r1);
        bst.insert(r6);
        bst.insert(r5);
        bst.insert(r7);
        bst.insert(r5);
        bst.insert(r2);
        bst.insert(r4);
        assertEquals(bst.getDepth(r4, 0), 1);
        assertEquals(bst.getDepth(r4, 1), 4);
        bst.remove(r4);
        assertEquals(bst.getDepth(r4, 0), 1);
        assertEquals(bst.getDepth(r4, 1), 0);
        // Clear out the results of that test.
        bst.makeEmpty();
        assertEquals(0, bst.getDepth(null, 0));
        bst.insert(r1);
        bst.insert(r2);
        bst.insert(r4);
        bst.insert(r3);
        bst.remove(r2);
        assertNull(bst.find(r2));
        assertEquals(bst.find(r3), r3);
        assertEquals(bst.find(r4), r4);
        bst.remove(r1);
        assertNull(bst.find(r1));
        assertEquals(bst.find(r4), r4);
        bst.remove(r4);
        Exception iNF = null;
        try {
            bst.remove(r1);
        }
        catch (Exception e) {
            iNF = e;
        }
        assertTrue(iNF instanceof ItemNotFoundException);
        bst.makeEmpty();
        bst.insert(r2);
        bst.insert(r4);
        bst.insert(r1);
        bst.insert(r3);
        bst.remove(r2);
        assertFalse(bst.isEmpty());
        assertEquals(bst.find(r1), r1);
        assertEquals(bst.find(r3), r3);
        assertEquals(bst.find(r4), r4);
    }

    /**
     * Tests the find function to determine if it returns properly in the empty
     * tree and tree with one element case.
     */
    public void testFind() {
        bst.insert(r1);
        assertEquals(bst.find(r1), r1);
        bst.remove(r1);
        assertEquals(bst.find(r1), null);
    }

    /**
     * Determines if the depth of a value is being computed properly.
     */
    public void testGetDepth() {
        assertEquals(bst.getDepth(r1, 0), 0);
        bst.insert(r1);
        assertEquals(bst.getDepth(r1, 0), 1);
        bst.insert(r4);
        bst.insert(r3);
        bst.insert(r2);
        bst.insert(r6);
        bst.insert(r5);
        bst.insert(r7);
        bst.insert(r2);
        assertEquals(bst.getDepth(r2, 0), 4);
        assertEquals(bst.getDepth(r3, 0), 3);
        assertEquals(bst.getDepth(r4, 0), 2);
        assertEquals(bst.getDepth(r5, 0), 4);
        assertEquals(bst.getDepth(r6, 0), 3);
        assertEquals(bst.getDepth(r7, 0), 4);
        assertEquals(bst.getDepth(r2, 1), 5);
        assertEquals(bst.getDepth(r2, 2), 0);
    }

    /**
     * Checks that the toString() method of the BST returns the expected values.
     */
    public void testToString() {
        assertEquals(bst.toString(), "()");
        bst.insert(r4);
        bst.insert(r2);
        bst.insert(r3);
        bst.insert(r1);
        bst.insert(r6);
        bst.insert(r5);
        assertEquals(bst.toString(),
                "((a, 10, 10, 100, 100), "
                        + "(b, 20, 20, 200, 200), (c, 50, 50, 300, 100), "
                        + "(d, 200, 200, 50, 50), (e, 250, 210, 10, 50), "
                        + "(f, 300, 100, 20, 50))");
    }

    /**
     * Tests the methods in the iterator object. Since they are all closely
     * related, we can achieve full code coverage with just this method.
     */
    public void testIterator() {
        BST<Rectangle>.BSTIterator iterator1;
        BST<Rectangle>.BSTIterator iterator2;
        iterator1 = bst.new BSTIterator();
        // Verify that iterator.getValue returns null when the tree is empty
        assertNull(iterator1.getValue());
        Rectangle[] rArr = { r1, r2, r3, r4, r5, r6, r7 };
        bst.insert(r1);
        bst.insert(r2);
        bst.insert(r6);
        assertEquals(0, bst.getDepth(r7, 0));
        assertEquals(0, bst.getDepth(r5, 0));
        bst.insert(r7);
        bst.insert(r5);
        bst.insert(r3);
        bst.insert(r4);
        iterator1 = bst.new BSTIterator();
        // Run through all elements in the iterator and see if they
        // are in order.
        for (int i = 0; i < 7; i++) {
            assertEquals(iterator1.getSize(), 7 - i);
            assertTrue(iterator1.hasNext());
            assertEquals(iterator1.getValue(), rArr[i]);
            assertEquals(iterator1.next(), rArr[i]);
        }
        assertFalse(iterator1.hasNext());
        assertNull(iterator1.next());
        bst.insert(r4);
        iterator2 = bst.new BSTIterator();
        assertEquals(iterator2.getSize(), 8);
    }
}
