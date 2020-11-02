import org.junit.*;

import static org.junit.Assert.*;

public class EventTests {

    @Test (timeout = 50)
    public void testTalk() {
        Talk t = new Talk("The wonderful world of time management", 1, "Not Sarah Kronenfeld");
        assertTrue(t.getAnnouncements() == null);
        assertTrue(t.getAttendeeIDs() == null);
        assertTrue(t.getName().equals("The wonderful world of time management"));
        assertTrue(t.getRoomID() == 1);
        String[] speakers = t.getSpeakers();
        assertTrue(speakers.length == 1);
        assertTrue(speakers[0].equals("Not Sarah Kronenfeld")); // Note - this usually will be an ID, not a name.
        // this val is used for testing only
        assertTrue(t.getDescription().equals("The wonderful world of time management, in Room 1"));
    }

    @Test (timeout = 50)
    public void testDescriptTalk() {
        Talk t = new Talk("The wonderful world of time management", 1, "Not Sarah Kronenfeld",
                "A seminar on how to get homework done before the last minute (I'm writing this at 4am)");
        assertTrue(t.getDescription().equals("A seminar on how to get homework done before the last minute (I'm writing this at 4am)"));
    }

}
