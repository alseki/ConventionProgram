package RequestPackage;

import java.util.ArrayList;
import java.util.UUID;


// This class will allow attendees, speakers and employees (at the conference) to make requests to the
// organizers (sent to the request board) to be either tagged or addressed. The request will be made via
// a message object.
// While attendees and speakers can be thought to be making requests, employees can requests and recommendations

// the parameters will be

public class RequestEntity {
    private String requestId;
    private String requestContent;
    private boolean fulfilled;
    private final String requestingUserId;
    private final ArrayList <String> eventsConcerned;

    /*  Helper class for making this class as an observable  */


    public RequestEntity(String requestContent, String requestingUserId) {
        this.requestContent = requestContent;
        this.requestingUserId = requestingUserId;
        this.fulfilled = false;
        this.requestId = UUID.randomUUID().toString();
    }

    public RequestEntity(String requestContent, String requestingUserId, ArrayList <String> eventsConcerned){
            this.requestContent = requestContent;
            this.requestingUserId = requestingUserId;
            this.eventsConcerned = eventsConcerned;
            this.fulfilled = false;
            this.requestId = UUID.randomUUID().toString();

    }
        public boolean getFulfilled () {
            return this.fulfilled;
        }

        public String getRequestingUserId () {
            return requestingUserId;
        }
        public String getRequestContent () {
            return requestContent;
        }

        public String getRequestId () {
            return this.requestingUserId;
        }

        public ArrayList<String> getEventsConcerned () {
            return this.eventsConcerned;
        }

        public void setFulfilled () {
            this.fulfilled = true;
        }

    }

