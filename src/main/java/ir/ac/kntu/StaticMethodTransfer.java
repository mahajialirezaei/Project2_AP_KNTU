package ir.ac.kntu;

import java.util.Map;
import java.util.Scanner;

public class StaticMethodTransfer {
    public static void moneyTransfer(User user1, Scanner scan, Map<User, String> users) {
        while (true) {
            String order1 = "";
            System.out.println("1.custom\n2.last account number\n3. my contacts\n4. quit");
            order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    customTransfer(user1, scan, users);
                    break;
                case "2":
                    StaticMethodCheckTransaction.lastAccountNumberTransfer(user1, scan, users);
                    break;
                case "3":
                    contactsTransfer(user1, scan, users);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("you should choose 1-4");
                    break;
            }

        }
    }

    public static void contactsTransfer(User user1, Scanner scan, Map<User, String> users) {
        if (user1.getContactsFeature()) {
            printUserContacts(user1);
            while (true) {
                System.out.println(Colors.colorString() + "1. enter your object contact phonenumber\n2. quit");
                String order1 = scan.nextLine();
                switch (order1) {
                    case "1":
                        order1 = scan.nextLine();
                        if (order1.matches("\\d{11}")) {
                            if (StaticMethodMoneyTransfer.mutualRelContact(order1, users, user1)) {
                                Object[] usersUser1 = { users, user1 };
                                Contact contact1 = StaticMethodMoneyTransfer.getContactByPhonenumber(user1, order1);
                                StaticMethodMoneyTransfer.finalTransfer(contact1.getIDocument(), usersUser1, scan,
                                        TransferType.CONTACT);
                            }
                        } else {
                            System.out.println("phone number structure is incorrect");
                        }
                        break;
                    case "2":
                        return;
                    default:
                        System.out.println("You should choose 1-2");
                        break;
                }
            }
        }
        System.out.println("Your contact feature is unavailable");
    }

    private static void printUserContacts(User user1) {
        for (Contact contact : user1.getUserContacts()) {
            System.out.println(contact);
        }
    }

    public static void customTransfer(User user1, Scanner scan, Map<User, String> users) {
        while (true) {
            String order1 = new String();
            System.out.println("1.Enter account number");
            System.out.println("2. quit");
            order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    order1 = scan.nextLine();
                    Object[] usersUser1 = { users, user1 };
                    StaticMethodMoneyTransfer.finalTransfer(order1, usersUser1, scan, TransferType.CUSTOM);
                    break;
                case "2":
                    return;
                default:
                    System.out.println("you should choose 1-2");
                    break;
            }
        }
    }

    public static void laAcTransfer(User user1, Scanner scan, Map<User, String> users) {
        while (true) {
            String order1 = new String();
            System.out.println("1.Enter account index");
            System.out.println("2. quit");
            order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    order1 = scan.nextLine();
                    if (!order1.matches("\\d+")) {
                        System.out.println("enter a number");
                        continue;
                    }
                    if (Integer.parseInt(order1) >= user1.getAccount().getLastAcountNumbers().size()) {
                        System.out.println(
                                "enter a number between 0-" +
                                        (user1.getAccount().getLastAcountNumbers().size() - 1));
                        continue;
                    }
                    Object[] usersUser1 = { users, user1 };
                    StaticMethodMoneyTransfer.finalTransfer(
                            ((User) usersUser1[1]).getAccount().getLastAcountNumbers().get(Integer.parseInt(order1)),
                            usersUser1, scan, TransferType.CUSTOM);
                    break;
                case "2":
                    return;
                default:
                    System.out.println("you should choose 1-2");
                    break;
            }
        }
    }
}
