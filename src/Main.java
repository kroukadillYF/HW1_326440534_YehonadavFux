import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Message> messages = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        //  מוסיף ידנית אובייקטים כדי לראות אם תקין ושיהיה תוכן מיידי אחרי שמריצים
        try {
            messages.add(new BoardMessage("B1", "Dana", "Important update regarding the final project schedule.", "Announcements"));
            messages.add(new BoardMessage("B2", "Yossi", "The cafeteria will be closed tomorrow for cleaning.", BoardMessage.Priority.URGENT, "General"));
            
            messages.add(new EmailMessage("E1", "Noa", "Please review the attached document before our next meeting.", "Project Submission"));
            EmailMessage email2 = new EmailMessage("E2", "Avi", "Here are the financial reports for Q3.", "Finance Report");
            email2.addAttachment(new File("Report.pdf", "PDF"));
            messages.add(email2);

            messages.add(new ChatVipMessage("C1", "Ron", "System maintenance starting in 10 minutes.", 3, true));
            messages.add(new ChatVipMessage("C2", "Maya", "Welcome to our VIP support channel!", 5, false));
        } catch (Exception e) {
            System.out.println("Error initializing default data: " + e.getMessage());
        }

        // יצירת התפריט
        int choice = 0;
        do {
            System.out.println("\n--- Messaging System Menu ---");
            System.out.println("1. Add a new message");
            System.out.println("2. Delete a message by ID");
            System.out.println("3. Print all messages repository");
            System.out.println("4. Count messages containing specific words in content");
            System.out.println("5. Print digital messages only (IDigital)");
            System.out.println("6. Print previews of all messages (Additional Option)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Choose message type to add (1: Board, 2: Email, 3: VIP Chat):");
                    try {
                        int type = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Sender: ");
                        String sender = scanner.nextLine();
                        System.out.print("Enter Content: ");
                        String content = scanner.nextLine();

                        if (type == 1) {
                            System.out.print("Enter Board Category: ");
                            String cat = scanner.nextLine();
                            messages.add(new BoardMessage(id, sender, content, cat));
                            System.out.println("Board message added successfully.");
                        } else if (type == 2) {
                            System.out.print("Enter Email Subject: ");
                            String subject = scanner.nextLine();
                            messages.add(new EmailMessage(id, sender, content, subject));
                            System.out.println("Email message added successfully.");
                        } else if (type == 3) {
                            System.out.print("Enter VIP Level (1-5): ");
                            int lvl = Integer.parseInt(scanner.nextLine());
                            messages.add(new ChatVipMessage(id, sender, content, lvl, true));
                            System.out.println("VIP Chat message added successfully.");
                        } else {
                            System.out.println("Invalid message type.");
                        }
                    } catch (Exception e) {
                        System.out.println("Failed to add message: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter ID of the message to delete: ");
                    String targetId = scanner.nextLine();
                    boolean removed = messages.removeIf(m -> m.getId().equals(targetId));
                    if (removed) {
                        System.out.println("Message deleted successfully.");
                    } else {
                        System.out.println("Message with ID " + targetId + " not found.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- All Messages Repository ---");
                    for (Message m : messages) {
                        System.out.println(m);
                    }
                    break;

                case 4:
                    System.out.print("Enter words separated by space to search: ");
                    String line = scanner.nextLine();
                    String[] splitWords = line.split("\\s+");
                    ArrayList<String> searchWords = new ArrayList<>();
                    for (String w : splitWords) {
                        searchWords.add(w);
                    }

                    long count = messages.stream().filter(m -> m.find(searchWords)).count();
                    System.out.println("Number of messages containing at least one of the words: " + count);
                    break;

                case 5:
                    System.out.println("\n--- Digital Messages Only ---");
                    for (Message m : messages) {
                        if (m instanceof IDigital) {
                            IDigital digitalMsg = (IDigital) m;
                            System.out.println(m + " | Communication: " + digitalMsg.printCommunicationMethod());
                        }
                    }
                    break;

                case 6:
                    System.out.println("\n--- Messages Previews ---");
                    for (Message m : messages) {
                        System.out.println(m.generatePreview());
                    }
                    break;

                case 7:
                    System.out.println("Exiting system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please choose between 1 and 7.");
            }
        } while (choice != 7);

        scanner.close();
    }
}