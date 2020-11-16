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
        String pathString = "";
        if (directoryString.contains("Controllers")) {
            String[] parts = directoryString.split(Pattern.quote("\\"));
            if (parts.length > 1) {
                for (int i = 0; i < parts.length - 2; i++) {
                    pathString = pathString + parts[i] + "\\";
                }
            }
            pathString = pathString + "data\\";
        } else if (directoryString.contains("src")) {
            String[] parts = directoryString.split(Pattern.quote("\\"));
            if (parts.length > 1) {
                for (int i = 0; i < parts.length - 2; i++) {
                    pathString = pathString + parts[i] + "\\";
                }
            }
            pathString = pathString + "data\\";
        } else if (directoryString.contains("phase1")) {
            pathString = directoryString + "\\data\\";
        } else if (directoryString.contains("group_0467")) {
            pathString = directoryString + "\\phase1\\data\\";
        } else {
            System.out.println("Whoops! Error constructing path");
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
                    new FileInputStream(new File(path + filename)));
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
    public boolean writeFile(T objIn, String filename) {
        try {
            File toFile = new File(path + filename);
            toFile.createNewFile();
            ObjectOutputStream saver = new ObjectOutputStream(
                    new FileOutputStream(toFile));
            saver.writeObject(objIn);
            return true;
        }
        catch (FileNotFoundException f) {
            System.out.println(f.toString());
            System.out.println(path + "data\\" + filename);
            return false;
        }
        catch (Exception f) {
            System.out.println(f.toString());
            f.printStackTrace();
            return false;
        }
    }

}
