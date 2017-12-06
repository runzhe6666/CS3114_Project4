import java.io.File;

/**
 * Tests the song search class.
 * 
 * @author Samuel Turner <samt5>
 * @version 2017.12.02
 * 
 */
public class SongSearchTest extends student.TestCase {
    private SongSearch ss1;

    /**
     * Initializes the testing environment for SongSearch.
     */
    public void setUp() {
        ss1 = new SongSearch(1000, 1000, new File("test.txt"));
    }

    /**
     * Tests the main function for the project.
     */
    public void testMain() {
        String[] mainArray = new String[3];
        mainArray[0] = "100";
        mainArray[1] = "100";
        mainArray[2] = "SyntaxTest.txt";
        SongSearch.main(mainArray);
        assertNotNull(ss1.toString());
    }

    /**
     * Tests file parser by running it with a basic test function. Currently the
     * tests are reviewed by hand since the outputs are hard to collect
     * properly. This method checks if FileParser's primary functionality works
     * and doesn't check edge cases.
     */
    public void testParseFile() {
        File file = null;
        Exception e = null;
        file = new File("TestFile.txt");
        assertTrue(file.exists());
        SongSearch mySongSearch = new SongSearch(100, 100, file);
        try {
            assertTrue(mySongSearch.parseFile());
        }
        catch (Exception e1) {
            e1.printStackTrace();
            e = e1;
        }
        // Ensures that the fileparser ran to completion
        assertNull(e);
    }

}
