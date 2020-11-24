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

    /*  Helper class for making this class as an observable  */
    private PropertyChangeSupport observable;

    public RequestEntity(String requestContent, String requestingUserId) {
        this.requestContent= requestContent;
        this.requestingUserId =requestingUserId;
        this.fulfilled = false;
        this.requestId = UUID.randomUUID().toString();
        this.observable = new PropertyChangeSupport (this);
    }
    public boolean getFulfilled(){
        return this.fulfilled;
    }

    public String getRequestingUserId() {
        return requestingUserId;
    }
    public String getRequestContent(){
        return requestContent;
    }

    public String getRequestId(){
        return this.requestId;
    }

    /**
     * Add a new observer to observe the changes to this class.
     * @param observer
     */
    public void addObserver(PropertyChangeListener observer) {
        observable.addPropertyChangeListener(this.requestId, observer);
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


    public void setFulfilled(){
        this.fulfilled = true;
        PropertyChangeEvent newEvent = new PropertyChangeEvent(
                this, this.requestId, false, true);
        notifyObservers(newEvent);
    }
    //todo need to set up a way so that all the organizers can observe changes in this entity.


}
