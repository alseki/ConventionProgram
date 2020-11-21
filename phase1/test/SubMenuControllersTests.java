// Programmer: Cara McNeil
// Description: Tests for Sub Menus
// Date Created: 14/11/2020
// Date Modified: 14/11/2020


import Controllers.*;
import Events.EventManager;
import Events.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class SubMenuControllersTests {

    private String userID = "007";
    private Map<String, Person> name2person = new HashMap<>();
    private Map<String, Person> id2person = new HashMap<>();
    private AttendeeManager attendeeManager = new AttendeeManager(name2person, id2person);
    private OrganizerManager organizerManager = new OrganizerManager(name2person, id2person);
    private SpeakerManager speakerManager = new SpeakerManager(name2person, id2person);
    private MessageManager messageManager = new MessageManager();
    private ChatManager chatManager = new ChatManager();
    private RoomManager roomManager = new RoomManager();
    private EventManager eventManager = new EventManager();
    // TODO private EventManager eventManager = new EventManager();

    // -----------------------------------------------------------------------------------------------------------------
    // AttEventController Tests


    @Test(timeout = 50)
    public void testAttEventController() {
        new AttEventController(userID, attendeeManager, roomManager, eventManager);
    }

    // TODO tests for menu option methods


    // -----------------------------------------------------------------------------------------------------------------
    // ContactController Tests

    @Test(timeout = 50)
    public void testContactController() {
        new ContactController(userID, attendeeManager);
        new ContactController(userID, organizerManager);
        new ContactController(userID, speakerManager);
    }

    // TODO tests for menu option methods


    // -----------------------------------------------------------------------------------------------------------------
    // LoginController Tests

    @Test(timeout = 50)
    public void testLoginController() {
        new LoginController(attendeeManager, 1);
        new LoginController(organizerManager, 2);
        new LoginController(speakerManager, 3);
    }

    // TODO tests for menu option methods


    // -----------------------------------------------------------------------------------------------------------------
    // MessageController Tests

    @Test(timeout = 50)
    public void testMessageController() {
        new AttMessageController(userID, attendeeManager, messageManager, chatManager, eventManager);
        new MessageController(userID, speakerManager, messageManager, chatManager, eventManager);
        new MessageController(userID, organizerManager, messageManager, chatManager, eventManager);
    }

    // TODO tests for menu option methods


    // -----------------------------------------------------------------------------------------------------------------
    // OrgEventController Tests

    @Test(timeout = 50)
    public void testOrgEventController() {
        //TODO new OrgEventController(userID, organizerManager, speakerManager, roomManager, messageManager, chatManager);
    }

    // TODO tests for menu option methods


    // -----------------------------------------------------------------------------------------------------------------
    // SpeEventController Tests

    @Test(timeout = 50)
    public void testSpeEventController() {
        // TODO new SpeEventController(userID, speakerManager, messageManager, chatManager, );
    }

    // TODO tests for menu option methods

}
