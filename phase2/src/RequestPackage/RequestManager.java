package ReqestPackage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestManager {
    private ArrayList<RequestPackage.RequestEntity> requestsList;
    private Map<String, RequestPackage.RequestEntity> idToRequest;
    private PropertyChangeSupport observable;
    public RequestManager(){
        this.requestsList = new ArrayList<RequestPackage.RequestEntity>();
        idToRequest = new HashMap<String, RequestPackage.RequestEntity>();
        this.observable = new PropertyChangeSupport (this);
    }
    private void addRequest(RequestPackage.RequestEntity req){
        requestsList.add(req);
    }
    public boolean createRequest(String reqUserId, String reqContent){
        RequestPackage.RequestEntity req = new RequestPackage.RequestEntity(reqContent, reqUserId);
        updateMap(reqUserId, req);
        addRequest(req);
        return true;
    }
    private void updateMap(String str, RequestPackage.RequestEntity req){
        idToRequest.put(str, req);
    }
    public RequestPackage.RequestEntity getRequestEntity(String reqId){
        return idToRequest.get(reqId);
    }
    public void updateEntity(String reqId) {
        RequestPackage.RequestEntity req = getRequestEntity(reqId);
        req.setFulfilled();
        PropertyChangeEvent newEvent = new PropertyChangeEvent(
                this, reqId, false, true);
        notifyObservers(newEvent);
    }
    public ArrayList<RequestPackage.RequestEntity> getAllRequests(){
        return requestsList;

    }

    /**
     * Add a new observer to observe the changes to this class.
     * @param observer
     */
    public void addObserver(PropertyChangeListener observer) {
        //observable.addPropertyChangeListener(this.requestId, observer);
    }
    /**
     * Notify observers o the change event.
     * @param newEvent
     */
    public void notifyObservers (PropertyChangeEvent newEvent)
    {
        for ( PropertyChangeListener observer : observable.getPropertyChangeListeners())
            observer.propertyChange(newEvent);
    }



    }

