package quickchat;

import java.util.Scanner;

public class QuickChatApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Message msgManager = new Message();

        msgManager.addMessageRecord("001", "+27834557896", "Did you get the cake?", "00:0:DIDCAKE", "Sent");
        msgManager.addMessageRecord("002", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "02:1:WHERETIME", "Stored");
        msgManager.addMessageRecord("003", "+27834484567", "Yohoooo, I am at your gate.", "03:2:YOHOGATE", "Disregard");
        msgManager.addMessageRecord("004", "0838884567", "It is dinner time!", "04:3:ITTIME", "Sent");
        msgManager.addMessageRecord("005", "+27838884567", "Ok, I am leaving without you.", "05:4:OKYOU", "Stored");

        System.out.println("Welcome to QuickChat.");

        int choice = 0;
        do {
            System.out.println("\n1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.println("4. Stored Messages");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid option. Please choose 1, 2, 3, or 4.");
                scanner.next();
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("How many messages do you wish to enter? ");
                    if (!scanner.hasNextInt()) {
                        scanner.next();
                        break;
                    }
                    int count = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < count; i++) {
                        System.out.print("Enter Message ID (max 10 chars): ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Recipient: ");
                        String rec = scanner.nextLine();
                        System.out.print("Enter Message: ");
                        String text = scanner.nextLine();
                        String hash = msgManager.createMessageHash(id, i, text);
                        System.out.println("1) Send  2) Disregard  3) Store");
                        int act = scanner.nextInt();
                        scanner.nextLine();
                        String flag = act == 1 ? "Sent" : (act == 3 ? "Stored" : "Disregard");
                        msgManager.addMessageRecord(id, rec, text, hash, flag);
                        System.out.println(msgManager.sentMessage(act));
                    }
                    break;
                case 2:
                    for (String m : msgManager.sentMessages) {
                        System.out.println(m);
                    }
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                case 4:
                    System.out.println("Longest message: " + msgManager.displayLongestMessage());
                    System.out.println(msgManager.displayFullReport());
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, 3, or 4.");
            }
        } while (choice != 3);

        scanner.close();
    }
}
