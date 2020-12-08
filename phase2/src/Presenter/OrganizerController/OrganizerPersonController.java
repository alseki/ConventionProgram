package Presenter.OrganizerController;


// Programmers:
// Description: All the methods that deal with userAccounts in OrganizerController Event Menu
// Date Created: 01/11/2020
// Date Modified: 19/11/2020


import Event.EventPermissions;
import Person.AttendeeManager;
import Person.EmployeeManager;
import Person.OrganizerManager;
import Person.SpeakerManager;
import Presenter.Central.SubMenu;
import Presenter.Central.SubMenuPrinter;
import Presenter.Exceptions.OverwritingException;

public class OrganizerPersonController extends SubMenu {

    private String currentUserID;
    private SpeakerManager speakerManager;
    private EmployeeManager employeeManager;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;

    public OrganizerPersonController(SubMenu subMenu, String currentUserID, SpeakerManager speakerManager,
                              EmployeeManager employeeManager, AttendeeManager attendeeManager) {
        super(subMenu);
        this.currentUserID = currentUserID;
        this.speakerManager = speakerManager;
        this.employeeManager = employeeManager;
        this.attendeeManager = attendeeManager;
        this.organizerManager = (OrganizerManager)personManager;
    }

    /**
     * Creates a new Person.SpeakerController account and adds it to the system
     * @param name The name of the SpeakerController
     * @param username The username of the SpeakerController
     * @param password The password of the SpeakerController
     * @param email The email of the SpeakerController
     * @return true iff a new Person.SpeakerController object was created
     */
    public void createSpeaker(String name, String username, String password, String email) throws OverwritingException {
        if (!speakerManager.findPerson(username)) {
            speakerManager.createAccount(name, username, password, email);
        }
        else {
            throw new OverwritingException("account");
        }
    }

    /**
     * Creates a new Person.Employee account and adds it to the system (including into a specific dictionary of employees solely)
     * @param name The name of the employee
     * @param username The username of the employee
     * @param password The password of the employee
     * @param email The email of the employee
     * @return true iff a new employee object was created
     */
    public void createEmployee(String name, String username, String password, String email) throws OverwritingException {
        if (!employeeManager.findPerson(username)) {
            employeeManager.createAccount(name, username, password, email);
        }
        else {
            throw new OverwritingException("account");
        }

        // TODO createAttendee


        // **** createEmployee will be in AdminEventController. Only administrators will create employees along with organizers
    }




//    public String createAnnouncementChat(String eventId, ArrayList<String> attendeeIds, String chatName){
//        Chat ac = new Chat(eventId, attendeeIds, chatName);
//        aChatsList.add(ac);
//        return ac.getId();

    // TODO private String?? event ID

    public void cancelAttendeeAccount(String userId){
        personManager.cancelAccount(userId);

    }

    public void cancelAttendeeAccountByUsername(String username){
        personManager.cancelAccount(username);
    }

    public void cancelSpeakerAccount(String userId){
        personManager.cancelAccount(userId);

    }

    public void cancelSpeakerAccountByUsername(String username){
        personManager.cancelAccount(username);
    }

    public void cancelEmployeeAccount(String userId){
        employeeManager.cancelEmployeeAccount(userId);

    }

    public void cancelEmployeeAccountByUsername(String username){
        employeeManager.cancelEmployeeAccount(username);
    }

    @Override
    public SubMenuPrinter getPresenter() {
        return null;
    }
}
