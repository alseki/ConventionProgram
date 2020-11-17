// Programmer: Cara McNeil
// Description: Tests for Sub Menus
// Date Created: 14/11/2020
// Date Modified: 14/11/2020


import Controllers.*;
import Events.EventManager;
import Events.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.AttendeeManager;
import Person.OrganizerManager;
import Person.PersonManager;
import Person.SpeakerManager;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class SubMenuControllersTests {

    private String userID = "007";
    private AttendeeManager attendeeManager = new AttendeeManager();
    private OrganizerManager organizerManager = new OrganizerManager();
    private SpeakerManager speakerManager = new SpeakerManager();
    private MessageManager messageManager = new MessageManager();
    private ChatManager chatManager = new ChatManager();
    // TODO private RoomManager roomManager = new RoomManager();
    // TODO private EventManager eventManager = new EventManager();

    // -----------------------------------------------------------------------------------------------------------------
    // AttEventController Tests


    @Test(timeout = 50)
    public void testAttEventController() {
        new AttEventController(userID, attendeeManager);
        new AttEventController(userID, organizerManager);
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
        new LoginController(attendeeManager);
        new LoginController(organizerManager);
        new LoginController(speakerManager);
    }

    // TODO tests for menu option methods


    // -----------------------------------------------------------------------------------------------------------------
    // MessageController Tests

    @Test(timeout = 50)
    public void testMessageController() {
        new MessageController(userID, attendeeManager, messageManager, chatManager);
        new MessageController(userID, organizerManager, messageManager, chatManager);
        new MessageController(userID, speakerManager, messageManager, chatManager);
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
