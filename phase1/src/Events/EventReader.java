package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 5 2020
// Architecture level - Use class

public interface EventReader {
    public abstract Event[] readEvents();
    public abstract void save(Event[] events);
}
