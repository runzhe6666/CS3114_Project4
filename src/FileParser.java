import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileParser {
	private int blockSize;
	private int hashSize;
	private File myFile;
	
	public FileParser(){
		
	}
	
	
	public void parseFile(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        
        Scanner in = null;
        
        try {
            in = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        
        while (in.hasNext()) {
            String command = in.next();
            // The line starts with insert, so execute that command
            if (command.equals("insert")) {
                insert();
            }
            // The line starts with remove, so execute that command
            else if (command.equals("remove")) {
                remove();
            }
            // The line starts with print tree, so execute that command
            else if (command.equals("print tree")) {
                printTree();
            }
            // The line starts with list, so execute that command
            else if (command.equals("list")) {
                list();
            }
            // The line starts with delete, so execute that command
            else if (command.equals("delete")) {
                search();
            }
            // The line starts with print, so execute that command
            else if (command.equals("print")) {
                print();
            }
        }
        in.close();
    }
	
	public void insert(String artist, String song){
		
	}
	public void remove(String in, String name){
		
	}
	public void print(String in){
		
	}
	public void list(String in, String name){
		
	}
	public void delete(String artist, String song){
		
	}
	public void printTree(){
		
	}
}
