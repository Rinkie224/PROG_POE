package quickchat;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Message {

    public ArrayList<String> sentMessages = new ArrayList<>();
    public ArrayList<String> disregardedMessages = new ArrayList<>();
    public ArrayList<String> storedMessages = new ArrayList<>();
    public ArrayList<String> messageHashes = new ArrayList<>();
    public ArrayList<String> messageIDs = new ArrayList<>();
    public ArrayList<String> recipients = new ArrayList<>();
    public ArrayList<String> allMessages = new ArrayList<>();

    public boolean checkMessageID(String messageID) {
        return messageID != null && messageID.length() <= 10;
    }

    public boolean checkRecipientCell(String recipientCell) {
        String regex = "^(\\+27[6-8][0-9]{8}|0[6-8][0-9]{8})$";
        return recipientCell != null && Pattern.matches(regex, recipientCell);
    }

    public boolean checkMessageLength(String message) {
        return message != null && !message.trim().isEmpty() && message.length() <= 250;
    }

    public String createMessageHash(String messageID, int messageNumber, String messageText) {
        String prefix = messageID.length() >= 2 ? messageID.substring(0, 2) : messageID;
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0].replaceAll("[^a-zA-Z0-9]", "") : "";
        String lastWord = words.length > 1 ? words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "") : firstWord;
        return (prefix + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Message disregarded.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    public void addMessageRecord(String id, String recipient, String msg, String hash, String flag) {
        messageIDs.add(id);
        recipients.add(recipient);
        messageHashes.add(hash);
        allMessages.add(msg);

        if ("Sent".equalsIgnoreCase(flag)) {
            sentMessages.add(msg);
        } else if ("Stored".equalsIgnoreCase(flag)) {
            storedMessages.add(msg);
        } else if ("Disregard".equalsIgnoreCase(flag) || "Disregarded".equalsIgnoreCase(flag)) {
            disregardedMessages.add(msg);
        }
    }

    public String displayLongestMessage() {
        ArrayList<String> pool = !storedMessages.isEmpty() ? storedMessages : allMessages;
        if (pool.isEmpty()) {
            return "No messages available.";
        }
        String longest = pool.get(0);
        for (String m : pool) {
            if (m.length() > longest.length()) {
                longest = m;
            }
        }
        return longest;
    }

    public String searchByID(String targetID) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equalsIgnoreCase(targetID)) {
                String msg = i < allMessages.size() ? allMessages.get(i) : "";
                return "Recipient: " + recipients.get(i) + "\nMessage: " + msg;
            }
        }
        return "No message found.";
    }

    public String searchByRecipient(String targetRecipient) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).equals(targetRecipient) && i < allMessages.size()) {
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(allMessages.get(i));
            }
        }
        return sb.toString();
    }

    public boolean deleteByHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equalsIgnoreCase(hash)) {
                String removedMsg = i < allMessages.size() ? allMessages.get(i) : "";
                messageHashes.remove(i);
                messageIDs.remove(i);
                recipients.remove(i);
                if (i < allMessages.size()) {
                    allMessages.remove(i);
                }
                sentMessages.remove(removedMsg);
                storedMessages.remove(removedMsg);
                disregardedMessages.remove(removedMsg);
                return true;
            }
        }
        return false;
    }

    public String displayFullReport() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentMessages.size(); i++) {
            sb.append("Message: ").append(sentMessages.get(i)).append("\n");
        }
        for (int i = 0; i < messageHashes.size(); i++) {
            sb.append("Hash: ").append(messageHashes.get(i))
              .append(" | Recipient: ").append(recipients.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}
