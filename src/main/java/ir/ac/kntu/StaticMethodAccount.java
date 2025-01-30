package ir.ac.kntu;

import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;

import ir.ac.kntu.util.Calendar;

public class StaticMethodAccount {
    public static void accountManagement(User user1, Scanner scan) {
        while (true) {
            System.out.println("ACCOUNT MANAGEMENT" + "\n\n---------------------\n\n" + "1.charge account");
            System.out.println("2. show balance");
            System.out.println("3.show transaction list");
            System.out.println("4.quit");
            String order = scan.nextLine();
            switch (order) {
                case "1":
                    charge(user1, scan);
                    break;
                case "2":
                    showBalance(user1);
                    break;
                case "3":
                    showTransactionList(user1, scan);
                    break;
                case "4":
                    return;
                default:
                    System.out.println(Colors.ANSI_RED + "you should choose 1-4");
                    break;
            }
        }
    }

    public static void showTransactionList(User user1, Scanner scan) {
        while (true) {
            String order1 = new String();
            System.out.println("show Transaction list menu" + "\n\n---------------------\n\n"
                    + "1.Without filter\n2. with filter\n3.quit");
            order1 = scan.nextLine();
            switch (order1) {
                case "2":
                    showTransactionListFilter(user1, scan);
                    break;
                case "1":
                    for (Contact contact : user1.getUserContacts()) {
                        StaticMethodContacts.changeTransactions(user1, contact);
                    }
                    for (Transaction tmpTransaction : user1.getAccount().getTransactions()) {
                        System.out.println(tmpTransaction);
                    }
                    break;
                case "3":
                    return;
                default:
                    System.out.println(Colors.ANSI_RED + "you should choose 1-2");
                    break;
            }

        }
    }

    public static void showTransactionListFilter(User user1, Scanner scan) {
        String[] fields = new String[6];
        while (!scanFilterFields(scan, fields)) {
            System.out.println("enter a number");
        }
        finalShowTransactionFilter(user1, fields);
    }

    public static void finalShowTransactionFilter(User user1, String[] fields) {
        for (Transaction transaction : user1.getAccount().getTransactions()) {
            if (checkTransactionCondition(fields, transaction)) {
                System.out.println(transaction);
            }
        }
    }

    public static boolean checkTransactionCondition(String[] fields, Transaction transaction) {
        Date date1 = new Date(fields[0], fields[1], fields[2]);
        Date date2 = new Date(fields[3], fields[4], fields[5]);
        return transaction.getDate().sub() >= date1.sub() && transaction.getDate().sub() <= date2.sub();
    }

    public static boolean scanFilterFields(Scanner scan, String[] fields) {
        System.out.println("Enter start year");
        fields[0] = scan.nextLine();
        System.out.println("Enter start month");
        fields[1] = scan.nextLine();
        System.out.println("Enter start day");
        fields[2] = scan.nextLine();
        System.out.println("Enter end year");
        fields[3] = scan.nextLine();
        System.out.println("Enter ean month");
        fields[4] = scan.nextLine();
        System.out.println("Enter end day");
        fields[5] = scan.nextLine();
        for (String string : fields) {
            if (!string.matches("\\d+")) {
                return false;
            }
        }
        return true;
    }

    public static void showBalance(User user1) {
        System.out.println("you balance is " + user1.getAccount().getBalance());
    }

    public static void charge(User user1, Scanner scan) {
        String input = new String();
        System.out.println("enter your money or quit");
        input = scan.nextLine();
        if ("quit".equals(input)) {
            return;
        } else if (input.matches("\\d+")) {
            int amount = Integer.parseInt(input);
            user1.getAccount().setBalance(user1.getAccount().getBalance() + Integer.parseInt(input));
            user1.getAccount().getTransactions()
                    .add(new Transaction(TransactionType.CHARGE, amount, "charge",
                            user1.getAccount().getIDocument(), Calendar.now(), user1.getFirstName(),
                            user1.getLastName()));
            Comparator<Transaction> transComp = (trans1, trans2) -> trans1.compareTo(trans2);
            user1.getAccount().getTransactions().sort(transComp);
        } else {
            System.out.println("input a number");
        }
    }

    public static void addContact(User user1, Map<User, String> users, Scanner scan) {
        String[] fields = new String[3];
        while (true) {
            System.out.println("do you want to continue\n write every thing else 2 to continue");
            System.out.println("2.quit");
            String order1 = scan.nextLine();
            if ("2".equals(order1)) {
                return;
            }
            System.out.println("Enter your contact firstname");
            fields[0] = scan.nextLine();
            System.out.println("Enter your contact lastname");
            fields[1] = scan.nextLine();
            System.out.println("Enter your contact phonenumber");
            fields[2] = scan.nextLine();
            boolean check1 = StaticMethodCheckCondition.checkCondition(fields, user1),
                    check2 = StaticMethodCheckCondition.checkEmptyContact(fields);
            if (!(check1 && !check2)) {
                return;
            }
            Contact contact1 = new Contact(fields[0], fields[1], fields[2]);
            user1.getUserContacts().add(contact1);

        }
    }

}
