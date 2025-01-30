package ir.ac.kntu;

import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;

public class StaticMethodMoneyTransfer {
    private static double incomeBank = 0.1;

    public static double getIncomeBank() {
        return incomeBank;
    }

    public static void setIncomeBank(double incomeBank1) {
        incomeBank = incomeBank1;
    }

    public static void finalTransfer(String iDocument, Object[] usersUser1, Scanner scan,
            TransferType transfertype) {
        Map<User, String> users = (Map<User, String>) usersUser1[0];
        for (User userTmp : users.keySet()) {
            if (userTmp.getAccount().getIDocument().equals(iDocument)) {
                setHowMuchMoney(usersUser1, transfertype, iDocument, scan);
                return;
            }
        }
        System.out.println("this iDocument for transfer money does not exist");
    }

    public static void setHowMuchMoney(Object[] usersUser1, TransferType transfertype, String iDocument,
            Scanner scan) {
        while (true) {
            String order2 = new String();
            System.out.println("1.enter your money\n2.quit");
            order2 = scan.nextLine();
            switch (order2) {
                case "1":
                    enterMoney(usersUser1, iDocument, scan, transfertype);
                    break;
                case "2":
                    return;
                default:
                    System.out.println("you should choose 1-2");
                    break;
            }
        }
    }

    public static void enterMoney(Object[] usersUser1, String iDocument, Scanner scan, TransferType transfertype) {
        User user1 = (User) usersUser1[1];
        String order2 = scan.nextLine();
        if (order2.matches("\\d+")) {
            double money = Integer.parseInt(order2);
            if (money * (1 + incomeBank) <= user1.getAccount().getBalance()) {
                if (transfertype.equals(TransferType.CUSTOM)) {
                    incDecMoneyCustom(money, usersUser1, iDocument, scan);
                } else {
                    incDecMoneyContact(money, usersUser1, iDocument, scan);
                }
            } else {
                System.out.println("Your money is not enough");
            }
        } else {
            System.out.println("enter a number");
        }
    }

    public static void incDecMoneyContact(double money, Object[] usersUser1, String iDocument,
            Scanner scan) {
        Map<User, String> users = (Map<User, String>) usersUser1[0];
        User user1 = (User) usersUser1[1];
        for (User user : users.keySet()) {
            if (user.getAccount().getIDocument().equals(iDocument) && user.getAuthentication()) {
                showTransferdDetails(user, money);
                System.out.println("1.ACCEPT\n2.quit");
                String order1 = scan.nextLine();
                if ("1".equals(order1)) {
                    user1.getAccount().setBalance(user1.getAccount().getBalance() - (1 + incomeBank) * money);
                    user.getAccount().setBalance(user.getAccount().getBalance() + money);
                    StaticMethodCheckTransaction.transactionTransfer(TransferType.CONTACT, user1, user, money);
                    user1.getAccount().getLastAcountNumbers().add(iDocument);
                    Comparator<Transaction> transComp = (trans1, trans2) -> trans1.compareTo(trans2);
                    user1.getAccount().getTransactions().sort(transComp);
                    System.out.println("succesfully transfered");
                    System.out.println(user1.getAccount().getTransactions().get(0));
                }
                return;
            }
        }
    }

    public static void incDecMoneyCustom(double money, Object[] usersUser1, String iDocument,
            Scanner scan) {
        Map<User, String> users = (Map<User, String>) usersUser1[0];
        User user1 = (User) usersUser1[1];
        for (User user : users.keySet()) {
            if (user.getAccount().getIDocument().equals(iDocument) && user.getAuthentication()) {
                showTransferdDetails(user, money);
                System.out.println("1.ACCEPT\n2.quit");
                String order1 = scan.nextLine();
                if ("1".equals(order1)) {
                    user1.getAccount().setBalance(user1.getAccount().getBalance() - (1 + incomeBank) * money);
                    user.getAccount().setBalance(user.getAccount().getBalance() + money);
                    StaticMethodCheckTransaction.transactionTransfer(TransferType.CUSTOM, user1, user, money);
                    user1.getAccount().getLastAcountNumbers().add(iDocument);
                    Comparator<Transaction> transComp = (trans1, trans2) -> trans1.compareTo(trans2);
                    user1.getAccount().getTransactions().sort(transComp);
                    System.out.println("succesfully transfered");
                    System.out.println(user1.getAccount().getTransactions().get(0));
                }
                return;
            }
        }
    }

    public static void showTransferdDetails(User user, double money) {
        System.out.println("user firstname" + user.getFirstName() + "\n" + "user lastname" + user.getLastName() + "\n"
                + "money:" + money);
    }

    public static boolean mutualRelContact(String order1, Map<User, String> users, User user1) {
        boolean check1 = false, check2 = false, check3 = false;
        Contact contact1 = getContactByPhonenumber(user1, order1);
        for (User user : users.keySet()) {
            // System.out.println("user contact 1 " + user.getIDocument() + " " +
            // contact1.getIDocument());
            if (contact1 != null && user.getAccount().getIDocument().equals(contact1.getIDocument())) {
                for (Contact contact : user.getUserContacts()) {
                    if (contact.getIDocument().equals(user1.getAccount().getIDocument())) {
                        check1 = true;
                        if (user.getContactsFeature()) {
                            check3 = user.getContactsFeature();
                        }
                        break;
                    }
                }
            }
        }
        for (Contact contact : user1.getUserContacts()) {
            if (contact.getPhoneNumber().equals(order1)) {
                check2 = true;
                break;
            }
        }
        if (!check3 || !check2 || !check1) {
            System.out.println(
                    "this contact contact feature is not available or this phone number is not in your contacts or you are not in this contact");
            System.out.println("");
            System.out.println("");
        }
        return check2 && check1 && check3;
    }

    public static Contact getContactByPhonenumber(User user1, String order1) {
        for (Contact contact : user1.getUserContacts()) {
            if (contact.getPhoneNumber().equals(order1)) {
                return contact;
            }
        }
        return null;
    }

}
