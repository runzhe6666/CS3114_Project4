/**
 * A tomb stone that allows the hash table to mark empty locations
 * when an element is removed.
 * 
 * @author Samuel Turner <samt5>
 * @version 2017.11.22
 *
 */
public class Tombstone extends SIPair {
    public Tombstone() {
        super("", -1);
    }
}
