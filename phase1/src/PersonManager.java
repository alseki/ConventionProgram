import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public abstract class PersonManager {

    List<Person> allPersons;   // creating a list of people would probably be easiest and straightforward -Allen

    public PersonManager() {   // constructor that creates the list
        allPersons = new ArrayList<>();
    }




    /**
     *  This creates a dictionary for Persons, where the key is the person's id, and the value linked to key
     *  is ArrayList of strings of person's information. When a person object is instantiated by way of the controller
     *  this dictionary is used. Further this dictionary will be called by other functions in the program
     */
    Map<String, ArrayList<String>> personMap = new HashMap<String, ArrayList<String>>();

    /**
     * This adds new user to the dictionary of persons
     * @param id
     * @param personInformation
     */

    public void addNewUser(String id, ArrayList personInformation){
        personMap.put(id, personInformation);
    }

    /**
     *
     * @param id
     * @param userName
     * @param password
     * @return a boolean value with respect to whether the user has a login name and password. Being called by a
     * controller class, this will tell the controller to sign the user in
     *
     */
    public boolean checkUsernamePasswordAtLogin(String id, String userName, String password){
        ArrayList listPersonInformation = personMap.get(id);
        if(listPersonInformation.contains(userName) && listPersonInformation.contains(password)){
            return true;
        }
        return false;
    }






}






