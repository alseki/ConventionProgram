package Request;

import java.io.Serializable;
import java.util.UUID;


// This class will allow attendees, speakers and employees (at the conference) to make requests to the
// organizers (sent to the request board) to be either tagged or addressed. The request will be made via
// a message object.
// While attendees and speakers can be thought to be making requests, employees can requests and recommendations

// the parameters will be

public class RequestEntity implements Serializable {
    private String requestId;
    private String requestContent;
    private boolean fulfilled;
    private final String requestingUserId;
    private String eventsConcerned;
    /*  Helper class for making this class as an observable  */

    /**
     * contructor for request entity
     * @param requestContent string representing hte request content
     * @param requestingUserId String representing the requesting user Id.
     */

    public RequestEntity(String requestContent, String requestingUserId) {
        this.requestContent = requestContent;
        this.requestingUserId = requestingUserId;
        this.fulfilled = false;
        this.requestId = UUID.randomUUID().toString();

    }

    /**
     * contructor for request entity - 2nd type where event name can be entered
     * @param requestContent
     * @param requestingUserId
     * @param eventsConcerned
     */

    public RequestEntity(String requestContent, String requestingUserId, String eventsConcerned){
            this.requestContent = requestContent;
            this.requestingUserId = requestingUserId;
            this.eventsConcerned = eventsConcerned;
            this.fulfilled = false;
            this.requestId = UUID.randomUUID().toString();

    }

    /**
     * getter fir fulffiled status
     * @return boolean
     */
        public boolean getFulfilled () {
            return this.fulfilled;
        }

    /**
     * getter for the requesting user id
     * @return String representing the requesting user Id
     */
    public String getRequestingUserId () {
            return requestingUserId;
        }

    /**
     * getter for request content
     * @return string reqpresenting the request content
     */
    public String getRequestContent () {
            return requestContent;
        }

    /**
     * getter for the request id
     * @return string representing the request id
     */
    public String getRequestId () {
            return this.requestingUserId;
        }

    /**
     * getter for events concerned
     * @return String
     */
    public String getEventsConcerned () {
            return this.eventsConcerned;
        }

    /**
     * sets this.fuflified to true, and notifiies observiers
     */
    public void setFulfilled () {
            this.fulfilled = true;
        }

    /**
     * To string method for request entity
     * comes in the format of requestId     requestcontent      pending/fulfilled       requestingusedID
     * @return string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(requestId);
        sb.append("\t");
        sb.append(requestContent);
        sb.append("\t");
        if(!this.fulfilled){
            sb.append("Pending");
        }
        else{
            sb.append("Fulfilled");
        }
        sb.append("\t");
        sb.append(requestingUserId);
        sb.append("\n");
        return sb.toString();
    }




    }

