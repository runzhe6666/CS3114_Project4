import java.io.File;

/**
 * 
 */

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
        SongSearch.main(null);
        assertNotNull(ss1.toString());
    }
}
