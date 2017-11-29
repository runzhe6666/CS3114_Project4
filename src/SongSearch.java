import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SongSearch {

	private int blockSize;
	private int hashSize;
	private File myFile;
	private byte[] songs;
	private byte[] artists;

	public SongSearch(int init_HashSize, int init_BlockSize, File fileIn) {
		blockSize = init_BlockSize;
		songs = new byte[blockSize];
		artists = new byte[blockSize];
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
					insert();
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
					print();
				}
			}

		}
		in.close();
	}

	public void insert(String artist, String song){
			Record myRecord = new Record(1, len, str);
			
			
		}

	public void remove(String in, String name) {

	}

	public void print(String in) {

	}

	public void list(String in, String name) {

	}

	public void delete(String artist, String song) {

	}

	public void printTree() {

	}
}
