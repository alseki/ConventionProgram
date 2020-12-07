package Presenter.OrganizerController;


// Programmers:
// Description: All the methods that deals with userAccounts in OrganizerController Event Menu
// Date Created: 01/11/2020
// Date Modified: 19/11/2020


import Presenter.Exceptions.OverwritingException;

public class OrganizerPersonController {

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











//    private String cancelEvent(String eventID){
//        LocalDateTime daytime = new LocalDateTime();
//        int dayTime = LocalDateTime.getHour();
//        int dayMinute = LocalDateTime.getMinute();
//
//        eventManager.get
//        char eventManger
//
//    }

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
}
