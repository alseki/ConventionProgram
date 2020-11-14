package Events;

import Events.Event;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 2 2020

// Architecture level - Entity

public class Talk extends Event {

    private String speakerID;
    private String description;

    public Talk (String name, String speakerID, int startTime) {
        super(name, startTime,1);
        this.speakerID = speakerID;
        description = "Talk: " + name;
    }

    public Talk (String name, int room, String speaker, String description) {
        super(name, room, 1);
        this.speakerID = speaker;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean conflictsWith(Event event) {
        if (event.getStartTime() == this.getStartTime()) {
            return true;
        }
        return false;
    }
}
