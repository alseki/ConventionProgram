import Message.Message;
import Message.MessageManager;
import Message.Chat;
import Message.ChatManager;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageTests {

    @Test (timeout = 50)
    public void testMessage() {
        Message m = new Message("001", "100", "I am number one, you are number one hundred.");
        assertEquals("001", m.getSenderId());
        assertEquals("100", m.getRecipientId());
        assertEquals("I am number one, you are number one hundred.", m.getContent());
    }

    @Test (timeout = 50)
    public void testMessageManager(){
        MessageManager mess = new MessageManager();
        assertTrue(mess.createMessage("01","10","I am first place."));
        assertTrue(mess.createMessage("02","20", "I am second place."));
        assertTrue(mess.createMessage("03","30","I am third place."));
        //in the middle of writing tests for the use case part
    }

    @Test (timeout = 50)
    public void testChat(){
        Message one = new Message("01","10","One to ten.");
        Message two = new Message("02","20","Two to twenty.");
        Message three = new Message("03","30","Three to thirty.");
        Chat c = new Chat();
        assertTrue(c.addMessageIds(one.getMessageId()));
        assertTrue(c.addMessageIds(two.getMessageId()));
        assertTrue(c.addMessageIds(three.getMessageId()));
        ArrayList <String> nums = new ArrayList<>();
        nums.add(one.getMessageId());
        nums.add(two.getMessageId());
        nums.add(three.getMessageId());
        Collections.sort(nums);
        Collections.sort(c.getMessageIds());
        assertEquals(c.getMessageIds(), nums);

    }

    @Test (timeout = 50)
    public void testChatManager(){

    }
}