
public class DatabaseTest extends student.TestCase{
	private Database myDb;
	
	public void setUp(){
		myDb = new Database(10);
	}
	
	public void testAddValue(){
		//normal case
		byte[] valArray = "zuri".getBytes();
		int strLen = valArray.length;
		assertEquals(myDb.addValue("zuri"), 0);
//		System.out.print(myDb.getSize());
//		System.out.println(strLen);
		assertEquals(myDb.addValue("sam"), strLen + 3);
		assertEquals(myDb.getCapacity(), 20);
		//need expand
	}
	
	public void testGetValue(){
		myDb.addValue("zuri");
		myDb.addValue("sam");
		assertEquals(myDb.getValue(0), "zuri");
		assertEquals(myDb.getValue(7), "sam");
	}
	
	public void testRemove(){
		myDb.addValue("zuri");
		myDb.addValue("sam");
		myDb.remove(0);
		assertEquals(myDb.getValue(0), "no record exists");
		
	}
	
	
	
}
