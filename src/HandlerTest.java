
public class HandlerTest extends student.TestCase{
	private Handler myHandler;
	
	public void setUp(){
		myHandler = new Handler(10, 10);
		
	}
	
	public void testInsert(){
		myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues"); //success 
		
	}
	
	public void testPrint(){
		myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
		myHandler.insert("Charley Patton", "Long Lonesome Blues");
		myHandler.insert("Bukka White", "Long Lonesome Blues");
		myHandler.insert("Sleepy John Estes", "Long Lonesome Blues");
		myHandler.insert("Another Bukka White", "Long Lonesome Blues");
		assertEquals(myHandler.print("artist"), 5);
	}
}
