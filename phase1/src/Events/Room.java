package Events;

// Contributors: Sarah Kronenfeld
// Last edit: Nov 14 2020

// Architecture level - Entity

import java.util.UUID;

class Room {
    private int capacity;
    private String ID;
    private String name;

    public Room () {
        this.capacity = 2;
        ID = UUID.randomUUID().toString();
        name = "Room " + ID;
    }

    public Room (int capacity) {
        this.capacity = capacity;
        ID = UUID.randomUUID().toString();
        name = "Room " + ID;
    }

    public Room (String name) {
        this.capacity = 2;
        this.name = name;
        ID = UUID.randomUUID().toString();
    }

    public Room (int capacity, String name) {
        this.capacity = capacity;
        this.name = name;
        ID = UUID.randomUUID().toString();
    }

    public int getCapacity() {
        return capacity;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}
