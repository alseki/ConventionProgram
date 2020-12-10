package Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmployeeManager extends PersonManager {

    // This account is created by organizer or administrator


    private ArrayList<String> requestIds;

    // dictionary of all employees; this is under the theme of creating a restricted chat for employees with employees only
    protected Map<String, Employee> usernameToEmployee = new HashMap<String, Employee>();

    String name = getName();

    private String getName() {
        return this.name;
    }

    public EmployeeManager(Map<String, Person> usernameToPerson, Map<String, Person> idToPerson) {
        super(usernameToPerson, idToPerson);
        this.requestIds = new ArrayList<String>();


    }

    @Override
    public boolean createAccount(String name, String username, String password, String email) {
        if (!usernameToPerson.containsKey(username)) {
            Employee newEmployee = new Employee(name, username, password, email);
            usernameToPerson.put(username, newEmployee);
            idToPerson.put(newEmployee.getID(), newEmployee);
            usernameToEmployee.put(username, newEmployee);
            return true;
        }
        return false;
    }

    public boolean cancelEmployeeAccount(String userID){
        if(idToPerson.containsKey((userID))){
            String username = getPerson(userID).getUsername();
            int typeUser = getPerson(userID).typePerson;
            if(typeUser == 4) {
                usernameToPerson.remove(username);
                idToPerson.remove(userID);
                usernameToEmployee.remove(username);
                return true;
            }
        }
        return false;
    }

    public boolean cancelEmployeeAccountByUsername(String username){
        if(usernameToPerson.containsKey(getPerson(username))){
            String userID = getPerson(username).getID();
            int typeUser = getPerson(userID).typePerson;
            if(typeUser == 4) {
                usernameToPerson.remove(username);
                idToPerson.remove(userID);
                usernameToEmployee.remove(username);
                return true;
            }
        }
        return false;
    }

    public Map<String, Employee> getUsernameToEmployee(){
        return usernameToEmployee;
    }


    public String getFullName(String username) {
        Person person = (Employee) getPerson(username);
        return person.getFullName();

    }

    public String getFullNameWithID(String userID){
        Person employee = (Employee) getPerson(userID);
        return employee.getFullName();
    }

    public String getUserName(String userID) {
        Person person = (Employee) getPerson(userID);
        return person.getUsername();

    }

    public String getEmployeeID(String userName) {
        Person employee = (Employee) getPerson(userName);
        return employee.getID();
    }

    public ArrayList<String> getAllEmployees() {
        ArrayList<String> nameUsernamePassword = new ArrayList<>();
        Map<String, Employee> map = getUsernameToEmployee();

        for (Employee employee : map.values()) {

                nameUsernamePassword.add("(" + employee.getFullName() + "; " + employee.username + "; " + employee.getID() + ")");
        }
        return nameUsernamePassword;

    }

    public ArrayList getRequestsIDs(String userID){
        return requestIds;
    }


    public boolean addAnnouncementChats(String personId, String acId) {
        Speaker individual = (Speaker) idToPerson.get(personId);
        if(!individual.getAnnouncementChats().contains(acId)) {
            individual.announcementChatIds.add(acId);
            return true;
        }
        return false;
    }

    public ArrayList getAnnouncementChats(String userID){
        Employee employee = (Employee) getPerson(userID);
        return employee.getAnnouncementChats();
    }

    /**
     * Get list of all talks in a Speaker object, referred to by the Speaker's ID.
     * @param speakerID ID of the Speaker
     * @return Arraylist of Strings corresponding to Talk Event IDs
     */
    public ArrayList<String> getSpeakerIdAllTalks(String speakerID){
        Speaker spe = (Speaker) getPerson(speakerID);
        return spe.getAllTalks();}

    public String IDtoUsername(String Id){
        return getPerson(Id).getUsername();
    }
    // apply notify observers

    // get messages from Organizer and from other Employees /**

    // check request board

    // change the status of requests, and implement notifications
}
