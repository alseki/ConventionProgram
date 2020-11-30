package Person;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
// The main purpose of this class is to allow for single responbility principle.
public class OrganizerManagerReqs implements PropertyChangeListener {
    private Map<String, Boolean> requestIdToStatus;
    OrganizerManagerReqs(){
        this.requestIdToStatus = new HashMap<String, Boolean>();
    }

    public void addRequest(String requestId){
        requestIdToStatus.put(requestId, false);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        requestIdToStatus.replace(evt.getPropertyName(),(boolean)evt.getNewValue());
        // should update the request status here, this is here so that
        // all organizers can have access.

    }
    public boolean getStatus(String reqId){
        return requestIdToStatus.get(reqId);
    }

}
