package Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Employee extends Person {

    protected ArrayList<String> anChatList = new ArrayList<>();

    private Map<String, Boolean> requestIdToStatus;

    //protected ArrayList<String> anChatIds = new ArrayList<>();


    public Employee (String fullName, String username, String password, String email){
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = UUID.randomUUID().toString();
        this.typePerson = 4;
        this.requestIdToStatus = new HashMap<>();
        this.anChatList = anChatList;
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

    public ArrayList<String> getAnChatList() {
        return anChatList;
    }

    public void addAnChat(String anChatID) {
        this.anChatList.add(anChatID);
    }

    public Map<String, Boolean> getRequestIdToStatus() {
        return requestIdToStatus;
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

}

