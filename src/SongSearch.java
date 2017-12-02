import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SongSearch {

	private int blockSize;
	private int hashSize;
	private File myFile;
	private Handler myHandler;
	public SongSearch(int init_HashSize, int init_BlockSize, File fileIn) {
		blockSize = init_BlockSize;
		hashSize = init_HashSize;
		myFile = fileIn;
		myHandler = new Handler(blockSize, hashSize);
		
		}

	public void parseFile(File file) {
		if (file == null || !file.exists()) {
			return;
		}

		Scanner in = null;

		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}

		while (in.hasNextLine()) {
			Scanner command = new Scanner(in.nextLine());
			while (command.hasNext()) {
				// The line starts with insert, so execute that command
				if (command.next() == "insert") {
					String artistNSong = command.next();
					String[] inputArray = artistNSong.split("<SEP>");
					String artist = inputArray[0];
					String song = inputArray[1];
					myHandler.insert(artist, song);
				}
				// The line starts with remove, so execute that command
				else if (command.next() == "remove") {
					remove();
				}
				// The line starts with print tree, so execute that command
				else if (command.next() == "printTree") {
					printTree();
				}
				// The line starts with list, so execute that command
				else if (command.next() == "list") {
					list();
				}
				// The line starts with delete, so execute that command
				else if (command.next() == "delete") {
					delete();
				}
				// The line starts with print, so execute that command
				else if (command.next() == "print") {
					String input = command.next();
					System.out.println(myHandler.print(input));
					
				}
			}

		}
		in.close();
	}
	
}
