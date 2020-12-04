package Person;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Employee extends Person {

    //protected ArrayList<String> eventsSignedUp = new ArrayList<>();

    protected ArrayList<String> anChatList = new ArrayList<>();

    private Map<String, Boolean> requestIdToStatus;

    public Employee (String fullName, String username, String password, String email){
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = UUID.randomUUID().toString();
        this.typePerson = 4;
        this.requestIdToStatus = new HashMap<>();
    }

    public ArrayList<String> getAnChatList() {
        return anChatList;
    }

    public void setAnChatList(ArrayList<String> anChatList) {
        this.anChatList = anChatList;
    }

    public Map<String, Boolean> getRequestIdToStatus() {
        return requestIdToStatus;
    }


    public void addAnChat(String chatID) {
        anChatList.add(chatID);
    }

    public void removeAnChat(String chatID) {
        if (anChatList.contains(chatID)) {
            anChatList.remove(chatID);
        }
    }
    public String getRequestStatus(String requestId){
        if (!requestIdToStatus.get(requestId)){
            return "Pending";
        }
        else{
            return "Fulfilled";
        }
    }
    public void newRequest(String requestId){
        requestIdToStatus.put(requestId, false);
    }

    //@Override
    public void propertyChange(PropertyChangeEvent evt) { // should this be in use case as it's modifying the variables?
        this.requestIdToStatus.replace(evt.getPropertyName(),(boolean)evt.getNewValue());

    }



    // apply notify observers

    // get messages from Organizer and from other Employees /**


    // check request board

    // change the status of requests, and implement notifications




}

