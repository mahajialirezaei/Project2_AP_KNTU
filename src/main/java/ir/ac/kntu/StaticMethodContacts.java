package ir.ac.kntu;

import java.util.Map;
import java.util.Scanner;

public class StaticMethodContacts {
    public static void contacts(User user1, Scanner scan, Map<User, String> users) {
        System.out.println(Colors.colorString() + "\n\n---------------------\n\n" + "1.add contact");
        System.out.println("2.show contacts");
        System.out.println("3.quit");
        String order1 = new String();
        order1 = scan.nextLine();
        switch (order1) {
            case "1":
                StaticMethodAccount.addContact(user1, users, scan);
                break;
            case "2":
                showContacts(user1, scan);
                break;
            case "3":
                return;
            default:
                System.out.println("you should choose 1-3\n\n");
                break;
        }
    }

    public static void showContacts(User user1, Scanner scan) {
        for (int i = 0; i < user1.getUserContacts().size(); i++) {
            System.out.println(i + "." + " " + user1.getUserContacts().get(i).privateInfo());
        }
        if (user1.getUserContacts().size() == 0) {
            System.out.println("you have no any contact\n\n");
        }
        while (true) {
            System.out.println("Do you want to show or edit any contact");
            System.out.println("1.yes");
            System.out.println("2.quit");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    showOrEditContacts(user1, scan);
                    break;
                case "2":
                    return;
                default:
                    System.out.println("you should choose 1-2");
                    break;
            }
        }
    }

    private static void showOrEditContacts(User user1, Scanner scan) {
        System.out.println("Enter index of your object's contact");
        while (true) {
            String order1 = scan.nextLine();
            if (!order1.matches("\\d+")) {
                System.out.println("enter a number");
                continue;
            } else {
                int index = Integer.parseInt(order1);
                if (index >= user1.getUserContacts().size()) {
                    System.out.println("enter a number between 0-" + (user1.getUserContacts().size() - 1));
                } else {
                    System.out.println(user1.getUserContacts().get(index).toString());
                    editContacts(user1, user1.getUserContacts().get(index), scan);
                }
            }
            System.out.println("if you want to quit enter 1\n else write any text to continue");
            String order2 = scan.nextLine();
            if ("1".equals(order2)) {
                return;
            }
        }
    }

    public static void editContacts(User user1, Contact contact1, Scanner scan) {
        String[] fields = new String[3];
        while (true) {
            String order1 = new String();
            System.out.println(Colors.ANSI_RED + "edit contacts menu" + "\n\n---------------------\n\n"
                    + "choose 1-4\n1.edit firstname\n2.edit lastname\n3.edit phonenumber\n4.quit");
            order1 = scan.nextLine();
            if ("4".equals(order1)) {
                return;
            }
            boolean check1 = editContactsSwitch(order1, fields, contact1, scan);
            if (check1) {
                setContactField(order1, contact1, fields);
                changeTransactions(user1, contact1);
                System.out.println("contact has been saved");
            }
        }
    }

    public static void setContactField(String order1, Contact contact1, String[] fields) {
        switch (order1) {
            case "1":
                contact1.setFirstName(fields[0]);
                break;
            case "2":
                contact1.setLastName(fields[1]);
                break;
            case "3":
                contact1.setPhoneNumber(fields[2]);
                break;
            default:
                break;
        }
    }

    public static void changeTransactions(User user1, Contact contact1) {
        for (Transaction transaction : user1.getAccount().getTransactions()) {
            if (transaction.getDestination().equals(contact1.getIDocument())
                    && transaction.getType() == TransactionType.TRANSFERCONTACT) {
                transaction.setFirstNameDest(contact1.getFirstName());
                transaction.setLastNameDest(contact1.getLastName());
            }
        }
    }

    private static boolean editContactsSwitch(String order1, String[] fields, Contact contact1, Scanner scan) {
        fields[0] = contact1.getFirstName();
        fields[1] = contact1.getLastName();
        fields[2] = contact1.getPhoneNumber();
        switch (order1) {
            case "1":
                System.out.println("Enter Your firstname");
                fields[0] = scan.nextLine();
                break;
            case "2":
                System.out.println("Enter Your lastname");
                fields[1] = scan.nextLine();
                break;
            case "3":
                System.out.println("Enter Your phonenumber");
                fields[2] = scan.nextLine();
                break;
            default:
                System.out.println("You should choose 1-4");
                break;
        }
        return !StaticMethodCheckCondition.checkEmptyContact(fields) && fields[2].matches("\\d{11}");

    }

    public static void changeContactsFeature(User user1, Scanner scan) {
        while (true) {
            String order1;
            System.out.println(
                    Colors.ANSI_GREEN + "change contacts feature menu" + Colors.AQUA + "\n\n---------------------\n\n"
                            + "1.set contacts feature available");
            System.out.println("2. set contacts feature unavailable");
            System.out.println("3. quit");
            order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    user1.setContactsFeature(true);
                    System.out.println("succesfully changed to available");
                    return;
                case "2":
                    user1.setContactsFeature(false);
                    System.out.println("succesfully changed to unavailable");
                    return;
                case "3":
                    return;
                default:
                    System.out.println("you should choose 1-3");
                    break;
            }
        }
    }

}
