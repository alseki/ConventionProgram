package Person;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
// The main purpose of this class is to allow for single responbility principle.
// allows for implementation of the facade design
public class OrganizerManagerReqs implements PropertyChangeListener {
    private Map<String, Boolean> requestIdToStatus;

    /**
     * contructor for oragnizer manger requests
     */
    OrganizerManagerReqs(){
        this.requestIdToStatus = new HashMap<String, Boolean>();
    }

    /**
     * allows to add a resuest with requestid -- meant for new unfufilied request only
     * @param requestId strign represeting the request id
     */
    public void addRequest(String requestId){
        requestIdToStatus.put(requestId, false);
    }

    /**
     * propertychange of a request
     * @param evt propertychange evt
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        requestIdToStatus.replace(evt.getPropertyName(),(boolean)evt.getNewValue());
        // should update the request status here, this is here so that
        // all organizers can have access.

    }

    /**
     * getter for the status of the request with reqwid
     * @param reqId string representing the request id
     * @return status pf requestid
     */
    public boolean getStatus(String reqId){
        return requestIdToStatus.get(reqId);
    }

}
