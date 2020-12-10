package Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Employee extends Person {

    //protected ArrayList<String> eventsSignedUp = new ArrayList<>();

    protected ArrayList<String> anChatList = new ArrayList<>();

    private Map<String, Boolean> requestIdToStatus;

    protected ArrayList<String> announcementChatIds = new ArrayList<>();

    public Employee (String fullName, String username, String password, String email){
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = UUID.randomUUID().toString();
        this.typePerson = 4;
        this.requestIdToStatus = new HashMap<>();
    }


    @Override
    public String getFullName() {
        return super.getFullName();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getID() {
        return super.getID();
    }

    //    public ArrayList<String> getAnChatList() {
//        return anChatList;
//    }
//
//    public void setAnChatList(ArrayList<String> anChatList) {
//        this.anChatList = anChatList;
//    }

    public Map<String, Boolean> getRequestIdToStatus() {
        return requestIdToStatus;
    }


//    public void addAnChat(String chatID) {
//        anChatList.add(chatID);
//    }
//
//    public void removeAnChat(String chatID) {
//        if (anChatList.contains(chatID)) {
//            anChatList.remove(chatID);
//        }
//    }

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

    /**
     *
     * This is a list of strings representing chatIds pertaining to announcements about events
     * @return ArrayList<String> of chatID of messages received by the User
     */

    public ArrayList<String> getAnnouncementChats() {
        return this.announcementChatIds;

    }

}

