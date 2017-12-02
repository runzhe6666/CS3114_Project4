
public class Handler<K extends Comparable<K>, V> {
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
		} else {
			artistHandle = artistHash.getHandle(artist);
		}
		if (songHash.getHandle(song) == -1) {
			songHandle = myDb.addValue(song);
			if (songHash.add(song, songHandle) == 1) {
				System.out.println("song has table expanded in size");
			}
		} else {
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
			for (String artist: artistHash.getAllElements()){
				System.out.println(artist);
			}
			total = artistHash.getNumElements();
		} else if (in.equals("song")) {
			for (String song: songHash.getAllElements()){
				System.out.println(song);
			}
			total = songHash.getNumElements();
		}

		return -1;
	}
}
