/**
 * The handler that manages insertions, searches, prints, and deletions from all
 * of the data structures in this project. Initialized with two capacities for
 * the hash table and the database.
 * 
 * @author Samuel Turner <samt5>
 * @version 2017.12.03
 *
 */
public class Handler {
	private Database myDb;
	private HashTable artistHash;
	private HashTable songHash;
	private BST<IIPair> artistSongTree;
	private BST<IIPair> songArtistTree;

	/**
	 * Initializes the Handler with a database, two hash tables, and two BSTs.
	 * 
	 * @param init_blockSize
	 *            The initial size of the database.
	 * @param init_capacity
	 *            The initial size of the hash table.
	 */
	public Handler(int init_blockSize, int init_capacity) {
		myDb = new Database(init_blockSize);
		artistHash = new HashTable(init_capacity);
		songHash = new HashTable(init_capacity);
		artistSongTree = new BST<IIPair>();
		songArtistTree = new BST<IIPair>();
	}

	/**
     * Inserts an artist, song pair into the database.
     * 
     * @param artist
     *            The artist to place.
     * @param song
     *            The song to place.
     * @return The insertion was successful.
     */
    public boolean insert(String artist, String song) {
        Handle artistHandle = new Handle(-1);
        Handle songHandle = new Handle(-1);

        // if artist hash doesn't have artist
        if (artistHash.getHandle(artist).getValue() == -1) {
            artistHandle = myDb.addValue(artist);
            if (artistHash.add(artist, artistHandle) == 1) {
                //System.out.println("artist hash table expanded in size");
            }
            System.out.println("|" + artist + "| " + "is added to the Artist database.");
        }
        else {
            artistHandle = artistHash.getHandle(artist);
            System.out.println("|" + artist + "| " + "duplicates a record already in the Artist database.");
        }
        if (songHash.getHandle(song).getValue() == -1) {
            songHandle = myDb.addValue(song);
            if (songHash.add(song, songHandle) == 1) {
                //System.out.println("song has table expanded in size");
            }
            System.out.println("|" + song + "| " + "is added to the Song database.");
        }
        else {
            songHandle = songHash.getHandle(song);
            System.out.println("|" + song + "| " + "duplicates a record already in the Song database.");
        }

        IIPair artistSongPair = new IIPair(artistHandle, songHandle);
        IIPair songArtistPair = new IIPair(songHandle, artistHandle);

        if (artistSongTree.find(artistSongPair) == null) { // not a duplicate
            artistSongTree.insert(artistSongPair);
            songArtistTree.insert(songArtistPair);
            System.out.println("The KVPair (|" + artist + "|,|" + song + "|),(" + artistHandle + "," + songHandle + ") is added to the tree.");
            System.out.println("The KVPair (|" + song + "|,|" + artist + "|),(" + songHandle + "," + artistHandle + ") is added to the tree.");
            return true;
        }
        
        System.out.println("The KVPair (|"+artist +"|,|"+song+"|),("+ artistHandle +","+ songHandle + ") duplicates a record already in the tree.");
        System.out.println("The KVPair (|"+song +"|,|"+artist+"|),("+ songHandle +","+ artistHandle + ") duplicates a record already in the tree.");
        return false;
    }

	/**
	 * Prints either all artists or all songs based upon what is passed in.
	 * 
	 * @param in
	 *            Either "song" or "artist" based upon what type is trying to be
	 *            output.
	 * @return The number of that type that exists in the database.
	 */
	public int print(String in) {
		int total = 0;
		int[] indices = null;
		String[] values = new String[1];
		if (in.equals("artist")) {
			indices = new int[artistHash.getNumElements()];
			values = artistHash.getAllElements(indices);
			for (int i = 0; i < values.length; i++) {
				System.out.println("|" + values[i] + "| " + indices[i]);
			}
			total = artistHash.getNumElements();
		} else if (in.equals("song")) {
			indices = new int[songHash.getNumElements()];
			values = songHash.getAllElements(indices);
			for (int i = 0; i < values.length; i++) {
				System.out.println("|" + values[i] + "| " + indices[i]);
			}
			total = songHash.getNumElements();
		}

		return total;
	}

	/**
	 * Deletes the specified element from the entire database.
	 * 
	 * @param artist
	 *            The name artist to remove.
	 * @param song
	 *            The name of the song to remove.
	 * @return The deletion was successful.
	 */
	public boolean delete(String artist, String song) {
		Handle artistHandle = artistHash.getHandle(artist);
		Handle songHandle = songHash.getHandle(song);

		if (artistHandle.getValue() == -1) {
			System.out.println("|" + artist + "| does not exist in the artist database.");
			return false;
		}
		if (songHandle.getValue() == -1){
			System.out.println("|" + song + "| does not exist in the artist database.");
			return false;
		}

		
		IIPair artistSongPair = new IIPair(artistHandle, songHandle);
		IIPair songArtistPair = new IIPair(songHandle, artistHandle);
		if (artistSongTree.find(artistSongPair) == null) {
			return false;
		}
		if (songArtistTree.find(songArtistPair) == null) {
			return false;
		}

		// remove from the artistSongTree
		artistSongTree.remove(artistSongPair);
		System.out.println("The KVPair (|" + artist + "|,|" + song + "|) is deleted from the tree.");
		// remove from the songArtistTree
		songArtistTree.remove(songArtistPair);
		System.out.println("The KVPair (|" + song + "|,|" + artist + "|) is deleted from the tree.");

		IIPair artistSongPair2 = new IIPair(artistHandle, new Handle(-1));
		IIPair songArtistPair2 = new IIPair(songHandle, new Handle(-1));
		if (artistSongTree.find(artistSongPair2) == null) { // no more songs for
															// this artist
			artistHash.remove(artist);
			myDb.remove(artistHandle);
			System.out.println( "|" + artist + "| is deleted from the artist database.");
		}
		if (songArtistTree.find(songArtistPair2) == null) { // no more artists
															// for this song
			songHash.remove(song);
			myDb.remove(songHandle);
			System.out.println("|" + song + "| is deleted from the song database.");
			
		}

		return true;
	}

	/**
	 * Entirely removes an artist from the database.
	 * 
	 * @param artist
	 *            The artist to remove.
	 * @return The artist was removed successfully.
	 */
	public boolean removeArtist(String artist) {
		Handle handle = artistHash.getHandle(artist);
		if (handle.getValue() < 0) {
			System.out.println("|" + artist + "| does not exist in the artist database.");
			return false;
		}
		// We are going to assume that the artist exists from here out.
		IIPair temp = new IIPair(handle, new Handle(-1));
		IIPair result = artistSongTree.remove(temp); // PROBLEM
		while (result != null) {
			temp = new IIPair(result.getValue(), result.getKey());
			result = songArtistTree.remove(temp);
			String song = myDb.getValue(result.getKey());
			System.out.println("The KVPair (|" + song+"|,|" + artist+ "|) is deleted from the tree.");
			temp.setValue(new Handle(-1));
			temp = songArtistTree.find(temp);
			if (temp == null) {
				songHash.remove(myDb.getValue(result.getKey()));
				myDb.remove(result.getKey());
				System.out.println("|" + song + "| is deleted from the Song database.");
			}
			temp = new IIPair(handle, new Handle(-1));
			try {
				result = artistSongTree.remove(temp);
				System.out.println("The KVPair (|" + artist+"|,|" + song+ "|) is deleted from the tree.");
			} catch (ItemNotFoundException e) {
				result = null;
			}
		}
		artistHash.remove(artist);
        System.out.println("|" + artist + "| is deleted from the Artist database.");
		myDb.remove(handle);
		return true;
	}

	/**
	 * Entirely removes a song from the database.
	 * 
	 * @param song
	 *            The song to remove.
	 * @return The song was removed successfully.
	 */
	public boolean removeSong(String song) {
		Handle handle = songHash.getHandle(song);
		if (handle.getValue() < 0) {
			System.out.println("|" + song + "| does not exist in the song database.");
			return false;
		}
		// We are going to assume that the artist exists from here out.
		IIPair temp = new IIPair(handle, new Handle(-1));
		IIPair result = songArtistTree.remove(temp);
		while (result != null) {
			temp = new IIPair(result.getValue(), result.getKey());
			result = artistSongTree.remove(temp);
			String artist = myDb.getValue(result.getKey());
			System.out.println("The KVPair (|" + artist+"|,|" + song+ "|) is deleted from the tree.");
			temp.setValue(new Handle(-1));
			temp = artistSongTree.find(temp);
			// Remove the song if it is no longer in the song tree.
			if (temp == null) {
				artistHash.remove(myDb.getValue(result.getKey()));
				myDb.remove(result.getKey());
				System.out.println("|" + artist + "| is deleted from the Artist database.");
			}
			temp = new IIPair(handle, new Handle(-1));
			try {
				result = songArtistTree.remove(temp);
				System.out.println("The KVPair (|" + song+"|,|" + artist+ "|) is deleted from the tree.");
			} catch (ItemNotFoundException e) {
				result = null;
			}
		}
		songHash.remove(song);
        System.out.println("|" + song + "| is deleted from the Song database");
		myDb.remove(handle);
		return true;
	}

	/**
	 * Lists out all songs corresponding to the provided artist
	 * 
	 * @param artist
	 *            The artist to search for.
	 */
	public void listArtist(String artist) {
		Handle aHandle = artistHash.getHandle(artist);
		if (aHandle.getValue() < 0) {
			System.out.println("|" + artist + "| does not exist in the artist database.");
			return;
		}
		IIPair temp = new IIPair(aHandle, new Handle(-1));
		BST<IIPair>.BSTIterator iterator = artistSongTree.new BSTIterator();
		while (iterator.hasNext()) {
			IIPair treeValue = iterator.next();
			if (temp.compareTo(treeValue) == 0) {
				System.out.println("|" + myDb.getValue(treeValue.getValue()) + "|");
			}
		}
	}

	/**
	 * Lists out all artists corresponding to the provided song.
	 * 
	 * @param song
	 *            The song to search for.
	 */
	public void listSong(String song) {
		Handle sHandle = songHash.getHandle(song);
		if (sHandle.getValue() < 0) {
			System.out.println("|" + song + "| does not exist in the song database.");
			return;
		}
		IIPair temp = new IIPair(sHandle, new Handle(-1));
		BST<IIPair>.BSTIterator iterator = songArtistTree.new BSTIterator();
		while (iterator.hasNext()) {
			IIPair treeValue = iterator.next();
			if (temp.compareTo(treeValue) == 0) {
				System.out.println("|" + myDb.getValue(treeValue.getValue()) + "|");
			}
		}
	}

	/**
	 * Prints the artist tree to the console.
	 */
	public void printTree() {
		System.out.println("Printing artist tree:");
		System.out.print(artistSongTree.toString());
		System.out.println("Printing song tree:");
		System.out.print(songArtistTree.toString());
	}
}
