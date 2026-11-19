package quickchat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    private Message messageManager;

    @Before
    public void setUp() {
        messageManager = new Message();
        messageManager.addMessageRecord("001", "+27834557896", "Did you get the cake?", "00:0:DIDCAKE", "Sent");
        messageManager.addMessageRecord("002", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "02:1:WHERETIME", "Stored");
        messageManager.addMessageRecord("003", "+27834484567", "Yohoooo, I am at your gate.", "03:2:YOHOGATE", "Disregard");
        messageManager.addMessageRecord("004", "0838884567", "It is dinner time!", "04:3:ITTIME", "Sent");
        messageManager.addMessageRecord("005", "+27838884567", "Ok, I am leaving without you.", "05:4:OKYOU", "Stored");
    }

    @Test
    public void testSentMessagesPopulated() {
        assertEquals("Did you get the cake?", messageManager.sentMessages.get(0));
        assertEquals("It is dinner time!", messageManager.sentMessages.get(1));
    }

    @Test
    public void testLongestMessage() {
        assertEquals("Where are you? You are late! I have asked you to be on time.", messageManager.displayLongestMessage());
    }

    @Test
    public void testSearchByMessageID() {
        String found = messageManager.searchByID("004");
        assertTrue(found.contains("It is dinner time!"));
    }

    @Test
    public void testSearchByRecipient() {
        String found = messageManager.searchByRecipient("+27838884567");
        assertTrue(found.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(found.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteByHash() {
        assertTrue(messageManager.deleteByHash("02:1:WHERETIME"));
    }

    @Test
    public void testDisplayReport() {
        String report = messageManager.displayFullReport();
        assertTrue(report.contains("00:0:DIDCAKE"));
        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("Did you get the cake?"));
    }
}
