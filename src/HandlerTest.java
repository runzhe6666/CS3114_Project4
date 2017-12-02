/**
 * This class test all methods in Handler class which will 
 * be used in the songSearch class
 * @author Zuri
 *
 */
public class HandlerTest extends student.TestCase{
	private Handler myHandler;
	
	/**
	 * initializes handler object with block size equals to 
	 */
	public void setUp(){
		myHandler = new Handler(10, 10);
		
	}
	
	/**
	 * Testing insert method in Handler class to make 
	 * sure no duplicate is inserted into BST
	 */
	public void testInsert(){
		assertTrue(myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues")); //success 
		assertFalse(myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues"));
		assertTrue(myHandler.insert("Zuri Wong", "Long Lonesome Blues"));
		assertTrue(myHandler.insert("Zuri Wong", "ABC"));
		
	}
	
	/**
	 * testing print method in Handler class to 
	 * print all artists or all songs
	 */
	public void testPrint(){
		myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
		myHandler.insert("Charley Patton", "Long Lonesome Blues");
		myHandler.insert("Bukka White", "Long Lonesome Blues");
		myHandler.insert("Sleepy John Estes", "Long Lonesome Blues");
		myHandler.insert("Another Bukka White", "Long Lonesome Blues");
		assertEquals(myHandler.print("artist"), 5);
		assertEquals(myHandler.print("song"), 1);
		
	}
	
	public void testRemoveArtist(){
		myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
		myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
		myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
		myHandler.insert("Sleepy John Estes", "Long Lonesome Blues");
		myHandler.insert("Another Bukka White", "Long Lonesome Blues");
		
		assertTrue(myHandler.removeArtist("Blind Lemon Jefferson"));
		assertFalse(myHandler.removeArtist("Blind Lemon Jefferson"));
		
	}
}
