package Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Employee extends Person {

    protected ArrayList<String> anChatList = new ArrayList<>();

    private final Map<String, Boolean> requestIdToStatus;

    //protected ArrayList<String> anChatIds = new ArrayList<>();


    public Employee (String fullName, String username, String password, String email){
        super(fullName, username, password, email);
        this.typePerson = 4;
        this.requestIdToStatus = new HashMap<>();
    }

    public Map<String, Boolean> getRequestIdToStatus() {
        return requestIdToStatus;
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

