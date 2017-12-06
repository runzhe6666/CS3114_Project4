
// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Adds songs to a database and provides functionality for determining where
 * they were placed, how they are related, along with the ability to remove
 * songs by their name or artist or both.
 * 
 * @author Samuel Turner <samt5>
 * @author Zuri Wong <zuriw>
 * @version 2017.12.02
 *
 */
public class SongSearch {
    /**
     * Runs when the program is called from the command line.
     * 
     * @param args
     *            The arguments that will be used. Must contain three elements:
     *            the hash table size, database size, and file name
     */
    public static void main(String[] args) {
        if (args == null || args.length != 3) {
            System.out.println("Incorrect number of arguments");
        }
        int hashSize = Integer.parseInt(args[0]);
        int blockSize = Integer.parseInt(args[1]);
        File file = new File(args[2]);
        SongSearch s = new SongSearch(hashSize, blockSize, file);
        s.parseFile();
    }

    private File myFile;
    private Handler myHandler;

    /**
     * Initializes the SongSearch object with a file, the initial hash table's
     * size, and the initial block size for the database.
     * 
     * @param initHashSize
     *            The hash table size
     * @param initBlockSize
     *            The database size
     * @param fileIn
     *            The file to read from.
     */
    public SongSearch(int initHashSize, int initBlockSize, File fileIn) {
        myFile = fileIn;
        myHandler = new Handler(initBlockSize, initHashSize);
    }

    /**
     * Parses the input file and prints the result to standard out.
     * 
     * @return The parsing was successful.
     */
    public boolean parseFile() {
        if (myFile == null || !myFile.exists()) {
            return false;
        }
        Scanner in = null;
        try {
            in = new Scanner(myFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        while (in.hasNextLine()) {

            String cmdLine = in.nextLine();
            // System.out.println(cmdLine);
            Scanner lineCmd = new Scanner(cmdLine);
            String cmd = lineCmd.next();
            // The line starts with insert, so execute that command
            if (cmd.equals("insert")) {
                String artistNSong = "";
                while (lineCmd.hasNext()) {
                    artistNSong += lineCmd.next() + " ";
                }

                // String artistNSong = lineCmd.next();
                String[] inputArray = artistNSong.split("<SEP>");
                String artist = inputArray[0].trim();
                String song = inputArray[1].trim();
                myHandler.insert(artist, song);
                // System.out.println();
            }
            // The line starts with remove, so execute that command
            else if (cmd.equals("remove")) {
                String type = lineCmd.next();
                String input = "";
                while (lineCmd.hasNext()) {
                    input += lineCmd.next() + " ";
                }
                if (type.equals("song")) {
                    myHandler.removeSong(input.trim());
                }
                else if (type.equals("artist")) {
                    myHandler.removeArtist(input.trim());
                }
                // System.out.println();
            }
            // The line starts with list, so execute that command
            else if (cmd.equals("list")) {
                String type = lineCmd.next();
                String input = "";
                while (lineCmd.hasNext()) {
                    input += lineCmd.next() + " ";
                }

                if (type.equals("song")) {
                    myHandler.listSong(input.trim());
                }
                else if (type.equals("artist")) {
                    myHandler.listArtist(input.trim());
                }
                // System.out.println();
            }
            // The line starts with delete, so execute that command
            else if (cmd.equals("delete")) {
                String artistNSong = "";
                while (lineCmd.hasNext()) {
                    artistNSong += lineCmd.next() + " ";
                }

                // String artistNSong = lineCmd.next();
                String[] inputArray = artistNSong.split("<SEP>");
                String artist = inputArray[0].trim();
                String song = inputArray[1].trim();
                myHandler.delete(artist, song);
                // System.out.println();
            }
            // The line starts with print, so execute that command
            else if (cmd.equals("print")) {
                String input = lineCmd.next();
                if (input.equals("tree")) {
                    myHandler.printTree();
                }
                else {
                    int totalNumOfEle = myHandler.print(input);
                    System.out
                            .println("total " + input + "s: " + totalNumOfEle);
                }

                // System.out.println();
            }
            lineCmd.close();
        }
        in.close();
        return true;
    }

}
