package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class User extends Person {
    private String phoneNumber = new String();
    private Account account = new Account();
    private List<Contact> userContacts = new ArrayList<>();
    private Map<SupportRequest, User> supReqUser = new HashMap<>();
    private boolean contactsFeature = true;
    private boolean authentication = false;

    public boolean getContactsFeature() {
        return this.contactsFeature;
    }

    public Map<SupportRequest, User> getSupReqUser() {
        return this.supReqUser;
    }

    public void setSupReqUser(Map<SupportRequest, User> supReqUser) {
        this.supReqUser = supReqUser;
    }

    public boolean isAuthentication() {
        return this.authentication;
    }

    public boolean getAuthentication() {
        return this.authentication;
    }

    public void setAuthentication(boolean authentication) {
        this.authentication = authentication;
    }

    public void setContactsFeature(boolean contactsFeature) {
        this.contactsFeature = contactsFeature;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAccount(String iDocument) {
        this.account.setIDocument(iDocument);
        this.account.setCard(new Card(iDocument.substring(2), "1111"));
    }

    public List<Contact> getUserContacts() {
        return this.userContacts;
    }

    public void setUserContacts(List<Contact> userContacts) {
        this.userContacts = userContacts;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User() {
        super();
    }

    public User(String firstName, String lastName, String phoneNumber, String iDocument, String password) {
        super(firstName, lastName, iDocument, password);
        setPhoneNumber(phoneNumber);
        setIDocument(iDocument);
    }

    public static boolean checkCondition(String[] fields, Map<User, String> users) {
        if (!(fields[2] == null)) {
            if (!fields[2].matches("\\d{11}")) {
                System.out.println(Colors.colorString() + "Invalid phonenumber");
                return false;
            }
        }
        if (!(fields[3] == null)) {
            System.out.println(fields[3]);
            if (!(fields[3].matches("\\d{10}"))) {
                System.out.println("Invalid id");
                return false;
            }
        }
        if (!(fields[4] == null)) {
            if (!(fields[4].matches(".*[A-Z]+.*") && fields[4].matches(".*[a-z]+.*") && fields[4].matches(".*[0-9]+.*")
                    && fields[4].matches(".*[!|@|#|$|%|^|&|*|(|)|_|+|-|?|\\|/]+.*"))) {
                System.out.println(Colors.colorString() + "choose an strong password");
                return false;
            }
        }
        for (User user1 : users.keySet()) {
            if (user1.getIDocument().equals(fields[3])) {
                System.out.println("Repetitious id");
                return false;
            }
            if (user1.getPhoneNumber().equals(fields[2])) {
                System.out.println("Repititous phonenumber");
                return false;
            }
        }
        return true;
    }

    public static void settings(User user1, Scanner scan) {
        while (true) {
            System.out.println(Colors.colorString() + "1.change personal password");
            System.out.println("2.change card password");
            System.out.println("3.change contacts feature");
            System.out.println("4.quit");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    Account.changePersonalPassword(user1, scan);
                    break;
                case "2":
                    Account.changeCardPassword(user1, scan);
                    break;
                case "3":
                    StaticMethodContacts.changeContactsFeature(user1, scan);
                    break;
                case "4":
                    return;
                default:
                    System.out.println(Colors.colorString() + "You should choose 1-4");
                    break;
            }
        }
    }

    @Override
    public String toString() {
        Colors.colorString();
        return super.toString() + "{" +
                " phoneNumber='" + getPhoneNumber() + "'" +
                "}";
    }

    public static boolean checkName(String[] fields) {
        if (!fields[0].matches("[a-zA-Z ]+")) {
            System.out.println(Colors.ANSI_RED + "Invalid firstname");
            return false;
        }
        if (!fields[1].matches("[a-zA-Z ]+")) {
            System.out.println(Colors.ANSI_RED + "Invalid lastname");
            return false;
        }
        return true;
    }

}
