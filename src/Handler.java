
public class Handler {
    private Database myDb;
    private HashTable artistHash;
    private HashTable songHash;
    private BST<IIPair> artistSongTree;
    private BST<IIPair> songArtistTree;

    public Handler(int init_blockSize, int init_capacity) {
        myDb = new Database(init_blockSize);
        artistHash = new HashTable(init_capacity);
        songHash = new HashTable(init_capacity);
        artistSongTree = new BST<IIPair>();
        songArtistTree = new BST<IIPair>();
    }

    public void insert(String artist, String song) {
        int artistHandle = -1;
        int songHandle = -1;

        if (artistHash.getHandle(artist) == -1) {
            artistHandle = myDb.addValue(artist);
            if (artistHash.add(artist, artistHandle) == 1) {
                System.out.println("artist hash table expanded in size");
            }
        }
        else {
            artistHandle = artistHash.getHandle(artist);
        }
        if (songHash.getHandle(song) == -1) {
            songHandle = myDb.addValue(song);
            if (songHash.add(song, songHandle) == 1) {
                System.out.println("song has table expanded in size");
            }
        }
        else {
            songHandle = songHash.getHandle(song);
        }

        IIPair artistSongPair = new IIPair(artistHandle, songHandle);
        IIPair songArtistPair = new IIPair(songHandle, artistHandle);

        artistSongTree.insert(artistSongPair);
        songArtistTree.insert(songArtistPair);

    }

    public int print(String in) {
        int total = 0;
        if (in.equals("artist")) {
            for (String artist : artistHash.getAllElements()) {
                System.out.println(artist);
            }
            total = artistHash.getNumElements();
        }
        else if (in.equals("song")) {
            for (String song : songHash.getAllElements()) {
                System.out.println(song);
            }
            total = songHash.getNumElements();
        }

        return total;
    }

    public boolean delete(String artist, String song) {
        int artistHandle = artistHash.getHandle(artist);
        int songHandle = songHash.getHandle(song);

        if (artistHandle == -1) {
            return false;
        }
        if (songHandle == -1) {
            return false;
        }

        IIPair artistSongPair = new IIPair(artistHandle, songHandle);
        IIPair songArtistPair = new IIPair(songHandle, artistHandle);
        // remove from the artistSongTree
        if (artistSongTree.find(artistSongPair) == null) {
            return false;
        }
        else {
            artistSongTree.remove(artistSongPair);
        }
        // remove from the songArtistTree
        if (songArtistTree.find(songArtistPair) == null) {
            return false;
        }
        else {
            songArtistTree.remove(songArtistPair);
        }

        IIPair artistSongPair2 = new IIPair(artistHandle, -1);
        IIPair songArtistPair2 = new IIPair(songHandle, -1);
        if (artistSongTree.find(artistSongPair2) == null) { // no more songs for
                                                            // this artist
            artistHash.remove(artist);
            myDb.remove(artistHandle);
        }
        if (songArtistTree.find(songArtistPair2) == null) { // no more artists
                                                            // for this song
            songHash.remove(song);
            myDb.remove(songHandle);
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
        int handle = artistHash.remove(artist);
        if (handle < 0) {
            return false;
        }
        // We are going to assume that the artist exists from here out.
        IIPair temp = new IIPair(handle, -1);
        IIPair result = artistSongTree.remove(temp);
        while (result != null) {
            temp = new IIPair(result.getValue(), result.getKey());
            result = songArtistTree.remove(temp);
            temp.setValue(-1);
            temp = songArtistTree.find(temp);
            if (temp == null) {
                songHash.remove(myDb.getValue(result.getKey()));
                myDb.remove(result.getKey());
            }
            temp = new IIPair(handle, -1);
            result = artistSongTree.remove(temp);
        }
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
        int handle = songHash.remove(song);
        if (handle < 0) {
            return false;
        }
        // We are going to assume that the artist exists from here out.
        IIPair temp = new IIPair(handle, -1);
        IIPair result = songArtistTree.remove(temp);
        while (result != null) {
            temp = new IIPair(result.getValue(), result.getKey());
            result = artistSongTree.remove(temp);
            temp.setValue(-1);
            temp = artistSongTree.find(temp);
            // Remove the song if it is no longer in the song tree.
            if (temp == null) {
                artistHash.remove(myDb.getValue(result.getKey()));
                myDb.remove(result.getKey());
            }
            temp = new IIPair(handle, -1);
            result = artistSongTree.remove(temp);
        }
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
            return;
        }
        IIPair temp = new IIPair(aHandle, new Handle(-1));
        int i = 0;
        int sHandle = artistSongTree.find(temp, i).getValue();
        while (sHandle >= 0) {
            System.out.println("|" + myDb.getValue(sHandle) + "|");
            i++;
            sHandle = artistSongTree.find(temp, i).getValue();
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
            return;
        }
        IIPair temp = new IIPair(sHandle, new Handle(-1));
        int i = 0;
        int aHandle = songArtistTree.find(temp, i).getValue();
        while (aHandle >= 0) {
            System.out.println("|" + myDb.getValue(aHandle) + "|");
            i++;
            aHandle = songArtistTree.find(temp, i).getValue();
        }
    }
}
