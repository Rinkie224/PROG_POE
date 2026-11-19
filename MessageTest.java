/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121_poe_part01;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Student
 */
public class MessageTest {
    
    private Message messageManager;
    @Before
    public void setUp() {
    messageManager = new Message();
    // Populate with Test Data 1 to 5 from the
    messageManager.addMessageRecord("001", "+27834557896", "Did you get the cake?","00:0:DIDCAKE", "Sent");
    messageManager.addMessageRecord("002", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "02:1:WHEREWA", "Stored");
    messageManager.addMessageRecord("003", "+27834484567", "Yohoooo, I am at your gate.", "03:2:YOHOOOO", "Disregard");
    messageManager.addMessageRecord("004", "0838884567", "It is dinner time!","04:3:ITISDI", "Sent");
    messageManager.addMessageRecord("005", "+27838884567", "Ok, I am leaving without you.", "05:4:OKIAM", "Stored");
    } 

    
   @Test
   public void testMessageLengthSuccess() {
       //Test message within 250 characters
       assertTrue(messageManager.checkMessageLength("Hi Mike, can you join us for dinner tonight?"));
   }
   @Test
   public void testMessageLengthFailure() {
       //Test message exceeding 250 characters
       String longMessage = "A".repeat(251);
       assertFalse(messageManager.checkMessageLength(longMessage)); 
   }
   @Test
   public void testRecipientCellCorrect() {
       //Test data 1 recipient number: +27718693002
       assertTrue(messageManager.checkRecipientCell("+27718693002"));
   }
   @Test
   public void testRecipientCellInCorrect() {
       //Test data 2 recipient number: 08575975889 (missing international code / format check)
       assertFalse(messageManager.checkRecipientCell("08575975889"));
   }
   @Test
   public void testCreateMessageHash() {
       //Test hash generation matches expected output format (00:0:HITONIGHT)
       String hash = messageManager.createMessageHash("0012345678",0,"Hi Mike, can you join us for dinner tonight?");
       assertEquals("00:0:HITONIGHT", hash);
   }
   
   @Test
   public void testMessageSentChoice(){
       //Test Action 1: send Message
       assertEquals("Message successfully sent.",messageManager.sentMessage(1));
   }
   @Test
   public void testMessageDiscardChoice(){
       //Test Action 2: Disregard Message
       assertEquals("Press 0 to delete the message.",messageManager.sentMessage(2));
   }
   @Test
   public void testMessageStoreChoice(){
       //Test Action 3: Store Message
       assertEquals("Message successfully stored.",messageManager.sentMessage(3));
    }
   //--- Part 3 Required Unit Tests---
   @Test
public void testLongestMessageDisplay() {
String longest = messageManager.displayLongestMessage();
assertEquals("Where are you? You are late! I have asked you to be on time.", longest);

 }
    @Test
   public void testSearchByID() {
    String result = messageManager.searchByID("004");
   assertTrue(result.contains("It is dinner time!"));
     }
    @Test
    public void testSearchByRecipient() {
    String result = messageManager.searchByRecipient("+27838884567");
    assertTrue(result.contains("Where are you? You are late! I have asked you to be on time.")
    &&
      result.contains("Ok, I am leaving without you."));
    }
@Test
public void testDeleteByHash() {
boolean deleted = messageManager.deleteByHash("02:1:WHEREWA");
assertTrue(deleted);
  }
}
   
           
    

