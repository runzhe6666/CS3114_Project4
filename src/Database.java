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

    /**
     * Initializes the database with the provided size.
     * 
     * @param initialSize
     *            The original size of the database.
     */
    public Database(int initialSize) {
        data = new byte[initialSize];
        size = 0;
        capacity = initialSize;
    }

    /**
     * Adds a record into the database.
     * @param value The value to add
     * @return The position that the value was placed in the database.
     */
    public int addValue(String value) {
        int maxStringSize = 65536;
        byte[] valArray = value.getBytes();
        if (valArray.length > maxStringSize) {
            return -1;
        }
        //put records into a new array with double capacity
        if (size + valArray.length > capacity){ 
        	byte[] newData = new byte[capacity * 2];
        	for (int i = 0; i < size; i++){
        		newData[i] = data[i];
        	}
        	data = newData;
        	capacity *= 2;
        }
        
        byte[] record = new byte[3 + valArray.length];
        record[0] = 1; //set record to active, flag = 1
        int recordPosition = size;
        size += record.length;
        return recordPosition ;
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
    	int len = ((data[position+ 1] & 0xff) << 8) | (data[position + 2] & 0xff);
    	//put representing bytes into a new bytes array 
        byte[] strArray = new byte[len];
        int startIndex = 0;
        for (int i = position; i < len; i++){
        	strArray[startIndex] = data[i];
        	startIndex++;
        }
        //convert bytes array into string
        String value = new String(strArray);
        return value;
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
