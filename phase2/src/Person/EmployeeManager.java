package Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Hi Paul, earlier, I didn't know that the error was called "missing symbol"
// but for your bug in employee manager, it's saying you're calling methods that don't exist.
// For example, individual.getAnnouncementChats() don't work bc such method doesn't exist in
// Speaker class(individual is a variable with type Speaker)
public class EmployeeManager extends PersonManager {

    // This account is created by organizer

    private ArrayList<String> requestIds;

    // dictionary of all employees; this is under the theme of creating a restricted chat for employees with employees only
    protected Map<String, Employee> usernameToEmployee = new HashMap<String, Employee>();
    protected ArrayList<String> employeeList = new ArrayList<>();


    public EmployeeManager(Map<String, Person> usernameToPerson, Map<String, Person> idToPerson) {

        super(usernameToPerson, idToPerson);
        this.requestIds = new ArrayList<String>();
        this.employeeList = employeeList;


    }

    public ArrayList getEmployeeList(String userID){
        return employeeList;

    }

    @Override
    public boolean createAccount(String name, String username, String password, String email) {
        if (!usernameToPerson.containsKey(username)) {
            Employee newEmployee = new Employee(name, username, password, email);
            usernameToPerson.put(username, newEmployee);
            idToPerson.put(newEmployee.getID(), newEmployee);
            usernameToEmployee.put(username, newEmployee);
            employeeList.add(newEmployee.getID());
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

    // This was decided not to be used. But will kept for future. Organizer would have been able to cancel employee
    // account with employee username.

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

    /**
     * Adds a chat
     * @param currentID
     * @param chatID
     */
    public void addAnChat(String currentID, String chatID) {
        Employee employee = (Employee)getPerson(currentID);
        employee.addAnChat(chatID);
    }

    /**
     * Removes a chat
     * @param currentID
     * @param chatID
     */
    public void removeAnChat(String currentID, String chatID) {
        Employee employee = (Employee)getPerson(currentID);
        employee.addAnChat(chatID);
    }

    public Map<String, Employee> getUsernameToEmployee(){
        return usernameToEmployee;
    }


    public String getFullName(String username) {
        Employee employee = (Employee) getPerson(username);
        return employee.getFullName();

    }

    public String getFullNameWithID(String userID){
        Employee employee = (Employee) getPerson(userID);
        return employee.getFullName();
    }

    public String getUserName(String userID) {
        Employee employee = (Employee) getPerson(userID);
        return employee.getUsername();

    }

    public String getEmployeeID(String userName) {
        Employee employee = (Employee) getPerson(userName);
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

    public ArrayList<String> getRequestsIDs(String userID){
        return requestIds;
    }


    public ArrayList getAnChats(String userID){
        Employee employee = (Employee) getPerson(userID);
        return employee.getAnChatList();
    }

    // Getting a list of all talks at the conference is available from the Employee event controller

     }


