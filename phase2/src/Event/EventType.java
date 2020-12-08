package Event;

public enum EventType {
    TALK, WORKSHOP, PANEL, PARTY;

    public String convertToString(EventType event){
        String eventType = event.toString();
        return eventType;
    }
}
