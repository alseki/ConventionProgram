package Request;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
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
    private final ArrayList <String> eventsConcerned;
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
        eventsConcerned = new ArrayList<String>(); //TAKEOUT
    }

    /**
     * contructor for request entity
     * @param requestContent
     * @param requestingUserId
     * @param eventsConcerned
     */

    public RequestEntity(String requestContent, String requestingUserId, ArrayList <String> eventsConcerned){
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

        public ArrayList<String> getEventsConcerned () {
            return this.eventsConcerned;
        }

    /**
     * sets this.fuflified to true, and notifiies observiers
     */
    public void setFulfilled () {
            this.fulfilled = true;
        }

    }

