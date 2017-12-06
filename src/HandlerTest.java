/**
 * This class test all methods in Handler class which will be used in the
 * songSearch class
 * 
 * @author Zuri Wong <zuriw>
 * @version 2017.12.04
 */
public class HandlerTest extends student.TestCase {
    private Handler myHandler;

    /**
     * initializes handler object with block size equals to
     */
    public void setUp() {
        myHandler = new Handler(10, 10);

    }

    /**
     * Testing insert method in Handler class to make sure no duplicate is
     * inserted into BST
     */
    public void testInsert() {
        assertTrue(myHandler.insert("Blind Lemon Jefferson",
                "Long Lonesome Blues")); // success
        assertFalse(myHandler.insert("Blind Lemon Jefferson",
                "Long Lonesome Blues"));
        assertTrue(myHandler.insert("Zuri Wong", "Long Lonesome Blues"));
        assertTrue(myHandler.insert("Zuri Wong", "ABC"));
        assertTrue(myHandler.insert("Imagine Dragons", "Believer"));
        assertTrue(myHandler.insert("Imagine Drgons", "Believer"));
        assertTrue(myHandler.insert("A1", "A2"));
        assertTrue(myHandler.insert("B1", "B2"));
        assertTrue(myHandler.insert("C1", "C2"));
        assertTrue(myHandler.insert("D1", "D2"));
        assertTrue(myHandler.insert("E1", "E2"));
        assertTrue(myHandler.insert("F1", "F2"));
        assertTrue(myHandler.insert("G1", "G2"));
    }

    /**
     * Tests the functionality of delete for the whole database.
     */
    public void testDelete() {
        assertTrue(myHandler.insert("A1", "A2"));
        assertTrue(myHandler.insert("B1", "B2"));
        assertTrue(myHandler.insert("C1", "C2"));
        assertTrue(myHandler.insert("D1", "D2"));
        assertTrue(myHandler.insert("E1", "E2"));
        assertTrue(myHandler.insert("F1", "F2"));
        assertTrue(myHandler.insert("G1", "G2"));
        assertTrue(myHandler.delete("A1", "A2"));
        assertTrue(myHandler.insert("B1", "B3"));
        assertTrue(myHandler.insert("C3", "C2"));
        assertTrue(myHandler.delete("B1", "B3"));
        assertTrue(myHandler.delete("C3", "C2"));
        assertFalse(myHandler.delete("C1", "C"));
        assertFalse(myHandler.delete("C4", "C4"));
    }

    /**
     * testing print method in Handler class to print all artists or all songs
     */
    public void testPrint() {
        myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
        myHandler.insert("Charley Patton", "Long Lonesome Blues");
        myHandler.insert("Bukka White", "Long Lonesome Blues");
        myHandler.insert("Sleepy John Estes", "Long Lonesome Blues");
        myHandler.insert("Another Bukka White", "Long Lonesome Blues");
        assertEquals(myHandler.print("artist"), 5);
        assertEquals(myHandler.print("song"), 1);
        assertEquals(myHandler.print("garbage"), 0);
    }

    /**
     * Tests the functionality of removal for an artist.
     */
    public void testRemoveArtist() {
        myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
        myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
        myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
        myHandler.insert("Sleepy John Estes", "Long Lonesome Blues");
        myHandler.insert("Another Bukka White", "Long Lonesome Blues");
        assertTrue(myHandler.removeArtist("Blind Lemon Jefferson"));
        assertFalse(myHandler.removeArtist("Blind Lemon Jefferson"));
        assertTrue(myHandler.removeArtist("Sleepy John Estes"));
        assertTrue(myHandler.removeArtist("Another Bukka White"));
        myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
        myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues 2");
        assertTrue(myHandler.removeArtist("Blind Lemon Jefferson"));
    }

    /**
     * Tests the functionality of removal for a song.
     */
    public void testRemoveSong() {
        myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
        myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
        myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
        myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues 2");
        myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues 3");
        assertTrue(myHandler.removeSong("Long Lonesome Blues"));
        assertFalse(myHandler.removeSong("Long Lonesome Blues"));
        assertTrue(myHandler.removeSong("Long Lonesome Blues 3"));
        assertTrue(myHandler.removeSong("Long Lonesome Blues 2"));
        myHandler.insert("Blind Lemon Jefferson", "Long Lonesome Blues");
        myHandler.insert("Blind Lemon Jefferson 2", "Long Lonesome Blues");
        assertTrue(myHandler.removeSong("Long Lonesome Blues"));
        myHandler.printTree();
    }

    /**
     * Tests the listArtist function. Because these outputs are purely run
     * through the console, these are checked manually with an expected output.
     */
    public void testListArtist() {
        KVPair<String, String> song1 = new KVPair<>("21 Pilots",
                "Guns for Hands");
        KVPair<String, String> song2 = new KVPair<>("21 Pilots",
                "House of Gold");
        KVPair<String, String> song3 = new KVPair<>("Flobots", "Handlebars");
        myHandler.listArtist(song1.getKey());
        assertFalse(myHandler.removeArtist(song1.getKey()));
        myHandler.insert(song1.getKey(), song1.getValue());
        myHandler.insert(song2.getKey(), song2.getValue());
        myHandler.insert(song3.getKey(), song3.getValue());
        myHandler.listArtist(song1.getKey());
        assertTrue(myHandler.removeArtist(song1.getKey()));
        myHandler.printTree();
    }

    /**
     * Tests the listSong function. Because these outputs are purely run through
     * the console, these are checked manually with an expected output.
     */
    public void testListSong() {
        KVPair<String, String> song1 = new KVPair<>("21 Pilots",
                "Guns for Hands");
        KVPair<String, String> song2 = new KVPair<>("21 Pilots",
                "House of Gold");
        KVPair<String, String> song3 = new KVPair<>("Flobots", "House of Gold");
        myHandler.listSong(song1.getKey());
        assertFalse(myHandler.removeSong(song1.getKey()));
        myHandler.insert(song1.getKey(), song1.getValue());
        myHandler.insert(song2.getKey(), song2.getValue());
        myHandler.insert(song3.getKey(), song3.getValue());
        myHandler.listSong(song2.getValue());
        assertTrue(myHandler.removeSong(song2.getValue()));
        myHandler.printTree();
    }
}
