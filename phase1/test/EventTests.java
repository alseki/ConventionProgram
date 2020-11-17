import Controllers.FileGateway;
import Events.*;
import org.junit.*;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class EventTests {

    @Test (timeout = 500)
    public void testTalk() {
        Talk t = new Talk("The wonderful world of time management", "1", LocalDateTime.of(2017, 1, 14, 10, 34));
        assertTrue(t.getAttendeeIDs().size() == 0);
        assertTrue(t.getName().equals("The wonderful world of time management"));
        String speaker = t.getSpeaker();
        assertTrue(speaker.equals("1"));
        assertTrue(t.getDescription().equals("Talk: The wonderful world of time management"));
        Talk r = new Talk("workshop", "1", LocalDateTime.of(2017, 1, 14, 10, 34));
        assertTrue(t.conflictsWith(r) == true);
        Talk s = new Talk("workshop", "1", LocalDateTime.of(2017, 1, 14, 12, 34));
        assertTrue(t.conflictsWith(s) == false);
    }

    @Test (timeout = 500)
    public void testDescriptTalk() {
        Talk p = new Talk("workshop", "1", LocalDateTime.of(2017, 1, 14, 10, 34), "A seminar on how to get homework done before the last minute (I'm writing this at 4am)");
        assertTrue(p.getDescription().equals("A seminar on how to get homework done before the last minute (I'm writing this at 4am)"));
    }

    @Test
    public void testEventManager() {
        RoomManager rooms = new RoomManager(LocalDateTime.now());
        RoomManager rooms2 = new RoomManager(LocalDateTime.now());
        String name = "Cool Room B)";
        rooms.addRoom(name, 200);
        rooms.addRoom("Sad Room :(");
        rooms2.addRoom(name, 200);
        assertTrue(rooms.getRoom(rooms.getRoomID(name)).equals(rooms2.getRoom(rooms2.getRoomID(name))));
    }

    @Test
    public void testRoomManager() {
        RoomManager rooms = new RoomManager(LocalDateTime.now());
        rooms.addRoom("Cool Room B)", 200);
        rooms.addRoom("Sad Room :(");
        RoomManager rooms2 = new RoomManager(LocalDateTime.now());
        rooms2.addRoom("Cool Room B)", 200);
        rooms2.addRoom("Sad Room :(");
        assertTrue(rooms.equals(rooms2));
    }

    @Test
    public void testGateway() {
        FileGateway<RoomManager> gateway = new FileGateway<RoomManager>("test.ser");
        RoomManager rooms = new RoomManager(LocalDateTime.now());
        rooms.addRoom("Cool Room B)", 200);
        rooms.addRoom("Sad Room :(");
        gateway.writeFile(rooms);
        RoomManager rooms2 = gateway.readFile();
        assertTrue(rooms.equals(rooms2));
    }

/*    @Test (timeout = 500)
    public void testEventDB() {
        Event[] eventArray = {};
        EventDB events = new EventDB(eventArray);
        Event event = new Talk("The wonderful world of time management", 1, "Not Sarah Kronenfeld");
        assertTrue(events.addEvent(event));
        assertTrue(events.getEventID("The wonderful world of time management").equals(event.getID()));
        assertTrue(events.getEvent(event.getID()).equals(event));
        assertTrue(events.removeEvent(event.getID()));
    }*/

}
