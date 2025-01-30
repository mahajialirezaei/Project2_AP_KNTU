package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Account {
    private String iDocument;
    private double balance;
    private List<Transaction> transactions = new ArrayList<>();
    private Card card;
    private List<String> lastAcountNumber = new ArrayList<>();

    public String printLastAcountNumber() {
        String result = "";
        if (lastAcountNumber.size() > 0) {
            for (int i = 0; i < this.lastAcountNumber.size(); i++) {
                result += i + "." + lastAcountNumber.get(i) + "\n";
            }
        } else {
            result += "you have no last account number";
        }
        return result;
    }

    public List<String> getLastAcountNumbers() {
        return this.lastAcountNumber;
    }

    public void setLastAcountNumber(List<String> lastAcountNumber) {
        this.lastAcountNumber = lastAcountNumber;
    }

    public Account(String iDocument, int balance, List<Transaction> transactions, Card card) {
        setBalance(balance);
        setCard(card);
        setIDocument(iDocument);
        setTransactions(transactions);
    }

    public Account() {

    }

    public String getIDocument() {
        return this.iDocument;
    }

    public void setIDocument(String iDocument) {
        this.iDocument = iDocument;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public String printTrans() {
        String natije = "";
        for (int i = 0; i < this.getTransactions().size(); i++) {
            natije += getTransactions().get(i) + "\n";
        }
        return natije;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Card getCard() {
        return this.card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setCard(String iDocument) {
        this.card.setIDocument(iDocument);
    }

    @Override
    public String toString() {
        return "{" +
                " iDocument='" + getIDocument() + "'" +
                ", balance='" + getBalance() + "'" +
                ", transactions='" + getTransactions() + "'" +
                ", card='" + getCard() + "'" +
                "}";
    }

    public static void changeCardPassword(User user1, Scanner scan) {
        while (true) {
            System.out.println("enter your current card password or type 1 to quit");
            String order1 = scan.nextLine();
            if ("1".equals(order1)) {
                return;
            }
            if (user1.getAccount().getCard().getPassword().equals(order1)) {
                System.out.println("Enter a 4 digit number");
                order1 = scan.nextLine();
                if (order1.matches("\\d{4}")) {
                    user1.getAccount().getCard().setPassword(order1);
                    System.out.println("succesfully changed");
                    return;
                } else {
                    System.out.println("password structure is not correct");
                }
            } else {
                System.out.println("password is not correct");
            }
        }
    }

    public static void changePersonalPassword(User user1, Scanner scan) {
        while (true) {
            System.out.println("Enter Your currentpassword");
            String order1 = scan.nextLine();
            if (user1.getPassword() == Objects.hash(order1)) {
                System.out.println("Enter your new password");
                order1 = scan.nextLine();
                if (!(order1.matches(".*[A-Z]+.*") && order1.matches(".*[a-z]+.*") && order1.matches(".*[0-9]+.*")
                        && order1.matches(".*[!|@|#|$|%|^|&|*|(|)|_|+|-|?|\\|/]+.*"))) {
                    System.out.println("choose an strong password");
                } else {
                    user1.setPassword(order1);
                    System.out.println("succesfully changed");
                }
            } else {
                System.out.println("not correct");
            }
            System.out.println("do you want to continue");
            System.out.println("1.continue");
            System.out.println("2.quit");
            order1 = scan.nextLine();
            if ("2".equals(order1)) {
                return;
            }
        }
    }
}
