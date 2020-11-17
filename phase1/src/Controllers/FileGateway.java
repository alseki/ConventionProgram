package Controllers;

import java.io.*;
import java.util.regex.Pattern;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 16 2020

// Architecture Level - Gateway

public class FileGateway<T> {
    String path;


    public FileGateway(String filename) {
        path = findPath() + filename;

    }

    public String findPath() {
        try {
            String directoryString = System.getProperty("user.dir");
            String pathString = "";
            String[] parts = directoryString.split(Pattern.quote("\\"));

            if (parts.length < 1) {
                pathString = directoryString + "\\";
            }
            else {
                String part = parts[0];
                int i = 0;
                while (!part.equals("phase1")) {
                    pathString += part + "\\";
                    i++;
                    part = parts[i];
                }
                pathString += part + "\\data\\";
            }
            return pathString;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Reads a file that contains a serialized Serializable of type T
     * @return The object contained in the file; null, if the method doesn't complete
     */
    public T readFile() {
        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            T returnValue = (T)input.readObject();

            input.close();
            return returnValue;
        }
        catch (EOFException f) {
            System.out.println("Sorry! Couldn't read from file.");
            System.out.println(f.toString());
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
     * @return Whether the method completes
     */
    public boolean writeFile(T objIn) {
        try {
            OutputStream file = new FileOutputStream(path);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);


            output.writeObject(objIn);
            output.close();
            System.out.println("Object successfully written!");
            return true;
        }
        catch (EOFException f) {
            System.out.println("Sorry! Couldn't write to file.");
            System.out.println(f.toString());
            return false;
        }
        catch (Exception f) {
            System.out.println(f.toString());
            f.printStackTrace();
            return false;
        }
    }

}
