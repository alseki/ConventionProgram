package Person;

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

        // apply notify observers

    // get messages from Organizer and from other Employees

    // check request board

    // change the status of requests, and implement notifications




}


