package ReqestPackage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.UUID;

public class RequestEntity{
    private String requestingUserId;
    private String requestContent;
    private boolean fulfilled;
    private final String requestId;
    private PropertyChangeSupport observable;

    /*  Helper class for making this class as an observable  */


    public RequestEntity(String requestContent, String requestingUserId) {
        this.requestContent= requestContent;
        this.requestingUserId = requestingUserId;
        this.fulfilled = false;
        this.requestId = UUID.randomUUID().toString();
        this.observable = new PropertyChangeSupport (this);
    }

    /**
     * this getter for the fufilled staus
     * @return boolean representing the fulfilled status
     */
    public boolean getFulfilled(){
        return this.fulfilled;
    }

    /**
     * getter for the requesting user id
     * @return String representing the requesting userid
     */

    public String getRequestingUserId() {
        return requestingUserId;
    }

    /**
     * getter for the request content
     * @return a getter for the request content
     */
    public String getRequestContent(){
        return requestContent;
    }

    /**
     * getter for the request ID
     * @return String representing the request ID
     */
    public String getRequestId(){
        return this.requestId;
    }

    /**
     * Changes the fufilled status to true
     * also, notify's the observers of the entity of this change
     */
    public void setFulfilled(){
        this.fulfilled = true;
        PropertyChangeEvent event = new PropertyChangeEvent(this, this.requestId, false,
               true);
        notifyObservers(event);
    }


    /**
     * Add a new observer to observe the changes to this class.
     * @param observer propertychange listerner for observer
     */
    public void addObserver(PropertyChangeListener observer) {
        //observable.addPropertyChangeListener(this.requestId, observer);
    }
    private void notifyObservers (PropertyChangeEvent newEvent)
    {
        for ( PropertyChangeListener observer : observable.getPropertyChangeListeners())
            observer.propertyChange(newEvent);
    }



}
