/**
 * A hash table that operates on pairs of String keys and integer values. Sorts
 * elements using the key.
 * 
 * @author Samuel Turner <samt5>
 * @version 2017.11.22
 *
 */
public class HashTable {
    private SIPair[] table;
    private int capacity;
    private int numElements;

    /**
     * Instantiates the hash table with the provided capacity. Pre:
     * initialCapacity is greater than 0.
     * 
     * @param initialCapacity
     *            The capacity that the table will be initialized with.
     */
    public HashTable(int initialCapacity) {
        capacity = initialCapacity;
        numElements = 0;
        table = new SIPair[capacity];
    }

    /**
     * Adds an element to the hash table with the provided value and the
     * position in the database.
     * 
     * @param value
     *            The string value to add.
     * @param position
     *            The position in the database.
     */
    public int add(String value, Handle position) {
        int n = 0; // this tells me if hash table expanded in size
        if (numElements > capacity / 2) {
            expandCapacity();
            n = 1;
        }
        SIPair newEntry = new SIPair(value, position);
        numElements++;
        addToArray(newEntry, table, capacity);
        return n;
    }

    /**
     * Removes the value that has the provided string as its key.
     * 
     * @param value
     *            The value to find.
     * @return The handle in the database.
     */
    public Handle remove(String value) {
        int index = findIndex(value);
        Handle handle = new Handle(-1);
        if (index != -1) {
            handle = table[index].getValue();
            table[index] = new Tombstone();
            numElements--;
        }
        return handle;
    }

    /**
     * Gets the handle in the database of a value that matches the provided
     * value.
     * 
     * @param value
     *            The value to search for.
     * @return The handle that was found.
     */
    public Handle getHandle(String value) {
        int index = findIndex(value);
        if (index == -1) {
            return new Handle(-1);
        }
        return table[index].getValue();
    }

    /**
     * This is the provided hash function that should be used.
     * 
     * @param s
     *            The string that should be hashed.
     * @param m
     *            The size of the hash table.
     * @return The value of the hash for the provided string.
     */
    public int h(String s, int m) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }
        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }
        return (int) (Math.abs(sum) % m);
    }

    /**
     * Expands the array to allow new elements to be added.
     * 
     * @return The expansion was successful.
     */
    private boolean expandCapacity() {
        int newCapacity = 2 * capacity;
        SIPair[] newTable = new SIPair[newCapacity];
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && !(table[i] instanceof Tombstone)) {
                addToArray(table[i], newTable, newCapacity);
            }
        }
        capacity = newCapacity;
        table = newTable;
        return true;
    }

    /**
     * Adds the provided KVPair into the provided array. Pre: The array is large
     * enough for the element to be added.
     * 
     * @param newEntry
     *            The KVPair to add.
     * @param array
     *            The array to use.
     * @param size
     *            The size of the array.
     */
    private void addToArray(SIPair newEntry, SIPair[] array, int size) {
        int target = h(newEntry.getKey(), size);
        int i = 0;
        // If the position is available, insert it there.
        while (array[(target + (i * i)) % size] != null
                && !(array[(target + (i * i)) % size] instanceof Tombstone)) {
            i++;
        }
        array[(target + (i * i)) % size] = newEntry;
    }

    /**
     * Finds the index of the provided value in the hash table if it exists.
     * 
     * @param value
     *            The value to search for.
     * @return The index of the value or -1 if it isn't there.
     */
    private int findIndex(String value) {
        int i = 0;
        int target = h(value, capacity);
        while (table[(target + (i * i)) % capacity] != null) {
            if (table[(target + (i * i)) % capacity].getKey()
                    .compareTo(value) == 0) {
                return (target + (i * i)) % capacity;
            }
            i++;
        }
        return -1;
    }

    /**
     * Gets the capacity of the hash table.
     * 
     * @return The capacity of the table.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets the number of elements in the hash table.
     * 
     * @return The number of elements in the table.
     */
    public int getNumElements() {
        return numElements;
    }

    public String[] getAllElements(int[] indices) {
        String[] myStrArray = new String[numElements];
        int arrayIndex = 0;
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && !(table[i] instanceof Tombstone)) {
                myStrArray[arrayIndex] = table[i].getKey();
                indices[arrayIndex] = i;
                arrayIndex++;
            }
        }
        return myStrArray;
    }
}
