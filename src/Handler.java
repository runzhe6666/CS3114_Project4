
public class Handler <K extends Comparable<K>, V> {
	private Database myDb;
	private HashTable artistHash;
	private HashTable songHash;
	private BST<IIPair> artistSongTree;
	private BST<IIPair> songArtistTree;
	
	public Handler(int init_blockSize, int init_capacity){
		myDb = new Database(init_blockSize);
		artistHash = new HashTable(init_capacity);
		songHash = new HashTable(init_capacity);
		artistSongTree = new BST<IIPair>();
		songArtistTree = new BST<IIPair>();
	}
	
	
	public boolean insert(String artist, String song){
		int artistHandle = -1;
		int songHandle = -1;
		
		if (artistHash.getHandle(artist) == -1){
			artistHandle = myDb.addValue(artist);
			if (artistHash.add(artist, artistHandle) == 1){
				System.out.println("artist hash table expanded in size");
			}
		}else {
			artistHandle = myDb.getValue(artist);
			
		}
		if (songHash.getHandle(song) == -1){
			songHandle = myDb.addValue(song);
			if (songHash.add(song,songHandle) == 1){
				System.out.println("song has table expanded in size");
			}
		}
		artistHandle
		IIPair artistSongPair = new IIPair(artistHandle, songHandle);
		
		
		
		
		
	}
}
