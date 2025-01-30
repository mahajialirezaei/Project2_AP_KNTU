package ir.ac.kntu;

import java.util.Map;
import java.util.Scanner;

import ir.ac.kntu.util.Calendar;

public class StaticMethodCheckTransaction {
    public static void transactionTransfer(TransferType transfertype, User user1, User user, double money) {
        if (transfertype.equals(TransferType.CUSTOM)) {
            transactionTransferCustom(user1, user, money);
            return;
        }
        transactionTransferContact(user1, user, money);
    }

    public static void transactionTransferContact(User user1, User user, double money) {
        for (Contact contact : user1.getUserContacts()) {
            if (contact.getPhoneNumber().equals(user.getPhoneNumber())) {
                user1.getAccount().getTransactions()
                        .add(new Transaction(TransactionType.TRANSFERCONTACT, money, user1.getAccount().getIDocument(),
                                user.getAccount().getIDocument(), Calendar.now(), contact.getFirstName(),
                                contact.getLastName()));
                user.getAccount().getTransactions()
                        .add(new Transaction(TransactionType.TRANSFERCONTACT, money, user1.getAccount().getIDocument(),
                                user.getAccount().getIDocument(), Calendar.now(), contact.getFirstName(),
                                contact.getLastName()));
            }
        }
    }

    public static void transactionTransferCustom(User user1, User user, double money) {
        user1.getAccount().getTransactions()
                .add(new Transaction(TransactionType.TRANSFERCUSTOM, money, user1.getAccount().getIDocument(),
                        user.getAccount().getIDocument(), Calendar.now(), user.getFirstName(), user.getLastName()));
        user.getAccount().getTransactions()
                .add(new Transaction(TransactionType.TRANSFERCUSTOM, money, user1.getAccount().getIDocument(),
                        user.getAccount().getIDocument(), Calendar.now(), user.getFirstName(), user.getLastName()));
    }

    public static void lastAccountNumberTransfer(User user1, Scanner scan, Map<User, String> users) {
        while (true) {
            System.out.println(user1.getAccount().printLastAcountNumber()); // should prints Last account numbers
            if (user1.getAccount().getLastAcountNumbers().size() == 0) {
                System.out.println(Colors.AQUA + "note: you have no any last account number");
            }
            System.out.println("1.continue and choose an account");
            System.out.println("2.quit");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    StaticMethodTransfer.laAcTransfer(user1, scan, users);
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
