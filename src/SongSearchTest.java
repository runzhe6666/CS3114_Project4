import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
/**
 * Tests the song search class.
 * @author Samuel Turner <samt5>
 * @version 2017.12.02
 * 
 */
public class SongSearchTest extends student.TestCase {
    private SongSearch ss1;
    
    public void setUp() {
        ss1 = new SongSearch(1000, 1000, new File("test.txt"));
    }
    
    public void testMain() {
    	String[] mainArray = new String[3];
    	mainArray[0] = "100";
    	mainArray[1] = "100";
    	mainArray[2] = "SyntaxTest.txt";
        SongSearch.main(mainArray);
        assertNotNull(ss1.toString());
    }
    
    /**
     * Tests file parser by running it with a basic test function.
     * Currently the tests are reviewed by hand since the outputs
     * are hard to collect properly. This method checks if FileParser's
     * primary functionality works and doesn't check edge cases.
     */
    public void testParseFile() {
        URL url = getClass().getResource("P4_Input1_Sample_final.txt");
        File file = null;
        boolean a = true;
        try {
            if (url != null) {
                file = new File(url.toURI());
            }
        }
        catch (URISyntaxException e) {
            //The file wasn't found where it was supposed to be.
            System.out.println("The file is missing");
        }
        SongSearch mySongSearch = new SongSearch(10, 10, file);
        try {
            assertTrue(mySongSearch.parseFile());
        }
        catch (Exception e) {
            e.printStackTrace();
            a = false;
        }
        //Ensures that the fileparser ran to completion
        assertTrue(a);
    }
    

}
