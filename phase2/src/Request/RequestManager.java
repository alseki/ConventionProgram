package Request;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestManager implements Serializable {
    private ArrayList<RequestEntity> requestsList;
    private Map<String, RequestEntity> idToRequest;
    private PropertyChangeSupport observable;

    /**
     * contructor for the request manager
     */
    public RequestManager(){
        this.requestsList = new ArrayList<RequestEntity>();
        idToRequest = new HashMap<String, RequestEntity>();

    }
    private void addRequest(RequestEntity req){
        requestsList.add(req);
    }

    /**
     * creates a reauest
     * @param reqUserId the user who is making the request
     * @param reqContent string fro the content of the request
     * @return true
     */
    public boolean createRequest(String reqUserId, String reqContent){
        RequestEntity req = new RequestEntity(reqContent, reqUserId);
        updateMap(reqUserId, req);
        addRequest(req);
        return true;
    }
    private void updateMap(String str, RequestEntity req){
        idToRequest.put(str, req);
    }

    /**
     * get a specific entity with reqid
     * @param reqId string for request id
     * @return the request entity with request id
     */
    public RequestEntity getRequestEntity(String reqId){
        return idToRequest.get(reqId);
    }

    /**
     * Add a new observer to observe the changes to this class.
     * @param observer
     */
    public void addObserver(PropertyChangeListener observer, String reqId) {
        RequestEntity req = getRequestEntity(reqId);
        req.addObserver(observer);
    }

    /**
     * to update an entity
     * @param reqId string for request id
     */
    public void updateEntity(String reqId){
        RequestEntity req = getRequestEntity(reqId);
        req.setFulfilled();
    }

}




