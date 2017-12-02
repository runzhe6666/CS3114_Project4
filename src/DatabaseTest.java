
public class DatabaseTest extends student.TestCase{
	private Database myDb;
	
	public void setUp(){
		myDb = new Database(10);
	}
	
	public void testAddValue(){
		//normal case
		byte[] valArray = "zuri".getBytes();
		int strLen = valArray.length;
		assertEquals(myDb.addValue("zuri").getValue(), 0);
//		System.out.print(myDb.getSize());
//		System.out.println(strLen);
		assertEquals(myDb.addValue("sam").getValue(), strLen + 3);
		assertEquals(myDb.getCapacity(), 20);
		
		//myDb.addValue("Blind Lemon Jefferson");

	}
	
	public void testGetValue(){
		myDb.addValue("zuri");
		myDb.addValue("sam");
		Handle handle_zuri = new Handle(0);
		Handle handle_sam = new Handle(7);
		assertEquals(myDb.getValue(handle_zuri), "zuri");
		assertEquals(myDb.getValue(handle_sam), "sam");
	}
	
	public void testRemove(){
		myDb.addValue("zuri");
		myDb.addValue("sam");
		Handle handle_zuri = new Handle(0);
		myDb.remove(handle_zuri);
		assertEquals(myDb.getValue(handle_zuri), "no record exists");
	}
	
	
	
}
