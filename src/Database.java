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
    private int blockSize;

    /**
     * Initializes the database with the provided size.
     * 
     * @param initialSize
     *            The original size of the database.
     */
    public Database(int initialSize) {
        data = new byte[initialSize];
        blockSize = initialSize;
        size = 0;
        capacity = initialSize;
    }

    /**
     * Adds a record into the database.
     * @param value The value to add
     * @return The position that the value was placed in the database.
     */
    public Handle addValue(String value) {
        int maxStringSize = 65536;
        Handle myHandle = new Handle(-1);
        byte[] valArray = value.getBytes();
//        ByteBuffer.wrap(valArray).getInt()
        if (valArray.length > maxStringSize) {
            return myHandle;
        }
        //put records into a new array with double capacity
//        if ((size + valArray.length + 3) > capacity){ 
//        	byte[] newData = new byte[capacity * 2];
//        	for (int i = 0; i < size; i++){
//        		newData[i] = data[i];
//        	}
//        	data = newData;
//        	capacity += blockSize;
//        	System.out.println("memory pool expanded in size");
//        }
        
        while ((size + valArray.length + 3) > capacity){
        	capacity += blockSize;
        	byte[] newData = new byte[capacity];
        	for(int i = 0; i < size; i++){
        		newData[i] = data[i];
        	}
        	data = newData;
        	System.out.println("memory pool expanded in size");
        }
        
        data[size] = 1; //set record to active, flag = 1
        data[size + 1] = (byte)(valArray.length >> 8);
        data[size + 2] = (byte)(valArray.length & ((1 << 8) - 1));
        
        for (int i = 0; i < valArray.length; i++){
        	data[size + 3 + i] = valArray[i];
        }
        
        myHandle.setValue(size);
        //int recordPosition = size;
        size = size + 3 + valArray.length;
        return myHandle ;
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
    	if (data[position] != 1){
    		return "no record exists";
    	}
    	int len = data[position + 1] << 8 | data[position + 2];
    	//put representing bytes into a new bytes array 
        byte[] strArray = new byte[len];
        int startIndex = 0;
        for (int i = position; i < len + position; i++){
        	strArray[startIndex] = data[i + 3];
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
    
    public int getSize(){
    	return size;
    }
    
    public int getCapacity(){
    	return capacity;
    }
    

}
