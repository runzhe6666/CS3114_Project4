import java.nio.ByteBuffer;

/**
 * Contains the database array that will store values as laid out in the project
 * spec. The contents should be manipulated with the provided methods.
 * 
 * @author Samuel Turner <samt5>
 * @version 2017.11.15
 *
 */
public class Database {
    // Stores the data that is in the Database as bytes.
    private byte[] data;
    private int capacity;
    private int size;
    private int writeIndex;

    /**
     * Initializes the database with the provided size.
     * 
     * @param initialSize
     *            The original size of the database.
     */
    public Database(int initialSize) {
        data = new byte[initialSize];
        writeIndex = 0;
        size = 0;
        capacity = initialSize;
    }

    /**
     * Adds a record into the database.
     * @param value The value to add
     * @return The position that the value was placed in the database.
     */
    public int addValue(String value) {
        int maxStringSize = 0;
        byte[] valArray = value.getBytes();
        if (valArray.length > maxStringSize) {
            return -1;
        }
        byte[] record = new byte[10 + valArray.length];
        record[0] = 1;
        return 0;
    }

    /**
     * Gets the string value of a record based upon the provided position in the
     * array.
     * Pre: position is a valid start of the record.
     * 
     * @param position
     *            The position of the record.
     * @return The record at the position.
     */
    public String getValue(int position) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int length = buffer.getInt(position + 1);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(buffer.getChar(position + 2 + i));
        }
        return result.toString();
    }

    /**
     * Removes the record at the given position in the database.
     * Performs this by flipping the active byte to zero.
     * Pre: position is a valid start of a record.
     * 
     * @param position The position of the record
     */
    public void remove(int position) {
        data[position] = 0;
    }
}
