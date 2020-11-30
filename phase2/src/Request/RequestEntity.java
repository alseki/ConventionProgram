package Request;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
    private PropertyChangeSupport observable;

    /*  Helper class for making this class as an observable  */


    public RequestEntity(String requestContent, String requestingUserId) {
        this.requestContent = requestContent;
        this.requestingUserId = requestingUserId;
        this.fulfilled = false;
        this.requestId = UUID.randomUUID().toString();
        eventsConcerned = new ArrayList<String>(); //TAKEOUT
        this.observable = new PropertyChangeSupport (this);
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
            PropertyChangeEvent newEvent = new PropertyChangeEvent(
                    this, this.requestId, false, true);
            notifyObservers(newEvent);
            this.fulfilled = true;
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

    }

