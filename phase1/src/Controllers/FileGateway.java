package Controllers;

import java.io.*;
import java.util.regex.Pattern;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 15 2020

// Architecture Level - Gateway

public class FileGateway<T> {
    String path;


    public FileGateway() {
        String directoryString = System.getProperty("user.dir");
        String[] parts = directoryString.split(Pattern.quote("\\"));
        String pathString = "";
        if (parts.length > 1) {
            for (int i = 0; i < parts.length - 1; i++) {
                pathString = pathString + parts[i] + "\\";
            }
        }
        else {
            System.out.println("Whoops! Error constructing filepath");
        }
        path = pathString;

    }

    /**
     * Reads a file that contains a serialized Serializable of type T
     * @param filename The filename of the file
     * @return The object contained in the file; null, if the method doesn't complete
     */
    public T readFile(String filename) {
        try {
            ObjectInputStream loader = new ObjectInputStream(
                    new FileInputStream(new File(path + "data\\" + filename)));
            Object returnObject = loader.readObject();
            return (T)returnObject;
        }
        catch (Exception f) {
            System.out.println(f.toString());
            f.printStackTrace();
            return null;
        }
    }

    /**
     * Writes a Serializable of type T into a file
     * @param objIn The Serializable
     * @param filename The filename of the file
     * @return Whether the method completes
     */
    public boolean writeRooms(T objIn, String filename) {
        try {
            ObjectOutputStream saver = new ObjectOutputStream(
                    new FileOutputStream(new File(path + "data\\" + filename)));
            saver.writeObject(objIn);
            return true;
        }
        catch (Exception f) {
            System.out.println(f.toString());
            f.printStackTrace();
            return false;
        }
    }

}
