/**
 * Tests the database classes methods in full. Ensures that all functionality
 * works as intended.
 * 
 * @author Samuel Turner <samt5>
 * @version 2017.12.06
 *
 */
public class DatabaseTest extends student.TestCase {
    private Database myDb;

    /**
     * Initializes the testing environment.
     */
    public void setUp() {
        myDb = new Database(10);
    }

    /**
     * Tests the add value functionality of Database.
     */
    public void testAddValue() {
        // normal case
        byte[] valArray = "zuri".getBytes();
        int strLen = valArray.length;
        assertEquals(myDb.addValue("zuri").getValue(), 0);
        // System.out.print(myDb.getSize());
        // System.out.println(strLen);
        assertEquals(myDb.addValue("sam").getValue(), strLen + 3);
        assertEquals(myDb.getCapacity(), 20);
        StringBuilder strBuild = new StringBuilder();
        for (int i = 0; i < 65537; i++){
        	strBuild.append("a");
        }
        assertEquals(myDb.addValue(strBuild.toString()).getValue(),-1);
        

    }

    /**
     * Tests the Get Value functionality of Database.
     */
    public void testGetValue() {
        myDb.addValue("zuri");
        myDb.addValue("sam");
        Handle handleZuri = new Handle(0);
        Handle handleSam = new Handle(7);
        assertEquals(myDb.getValue(handleZuri), "zuri");
        assertEquals(myDb.getValue(handleSam), "sam");
    }

    /**
     * Tests removal from the database.
     */
    public void testRemove() {
        myDb.addValue("zuri");
        myDb.addValue("sam");
        Handle handleZuri = new Handle(0);
        myDb.remove(handleZuri);
        assertEquals(myDb.getValue(handleZuri), "no record exists");
    }

}
