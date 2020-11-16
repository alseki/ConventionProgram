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
            System.out.println("Launched from Controllers folder");
            pathString = pathString + "data\\";
        } else if (directoryString.contains("src")) {
            String[] parts = directoryString.split(Pattern.quote("\\"));
            if (parts.length > 1) {
                for (int i = 0; i < parts.length - 2; i++) {
                    pathString = pathString + parts[i] + "\\";
                }
            }
            System.out.println("Launched from src folder");
            pathString = pathString + "data\\";
        } else if (directoryString.contains("phase1")) {
            System.out.println("Launched from phase1 folder");
            pathString = directoryString + "\\data\\";
        } else if (directoryString.contains("group_0467")) {
            System.out.println("Launched from group_0467 folder");
            pathString = directoryString + "\\phase1\\data\\";
        } else {
            String[] parts = directoryString.split(Pattern.quote("\\"));
            System.out.println("Whoops! Error constructing path");
            System.out.println("Launched from "+ parts[parts.length - 1] +" folder");
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
            InputStream file = new FileInputStream(path + filename);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            T returnValue = (T)input.readObject();

            input.close();
            return returnValue;
        }
        catch (EOFException f) {
            System.out.println(f.toString());
            f.printStackTrace();
            System.out.println("Sorry! No such object found.");
            return null;
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
            OutputStream file = new FileOutputStream(path + filename);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);


            output.writeObject(objIn);
            output.close();
            System.out.println("Object successfully written!");
            return true;
        }
        catch (FileNotFoundException f) {
            System.out.println(f.toString());
            System.out.println(path + filename);
            return false;
        }
        catch (Exception f) {
            System.out.println(f.toString());
            f.printStackTrace();
            return false;
        }
    }

}
