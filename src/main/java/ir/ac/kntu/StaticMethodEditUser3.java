package ir.ac.kntu;

import java.util.Map;
import java.util.Scanner;

public class StaticMethodEditUser3 {
    public static void showFilteredUser(Map<User, String> users, String[] filters, Scanner scan) {
        while (true) {
            int index = 0;
            for (User user1 : users.keySet()) {
                if ((filters[0] == null
                        || StaticMethodSimilarityAlgorithm.isSimilarity(user1.getFirstName(), filters[0]))
                        && (filters[1] == null
                                || StaticMethodSimilarityAlgorithm.isSimilarity(user1.getLastName(), filters[1]))
                        && (filters[2] == null
                                || StaticMethodSimilarityAlgorithm.isSimilarity(user1.getPhoneNumber(), filters[2]))) {
                    System.out.println(index + " : " + user1);
                    index++;
                }
            }
            System.out.println("1. Enter index of your user to show it");
            System.out.println("2. quit");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    StaticMethodEditUser2.selectFilteredUser(scan, filters, users);
                    break;
                case "2":
                    return;
                default:
                    System.out.println("you should choose 1-2");
                    break;
            }

        }
    }

    public static void showUserList(Scanner scan, Map<User, String> users) {
        while (true) {
            int index = 0;
            for (User user1 : users.keySet()) {
                System.out.println(index + " : " + user1);
                index++;
            }
            System.out.println("1. Enter index of your user to show it");
            System.out.println("2. quit");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    StaticMethodEditUser2.selectAllUser(scan, users);
                    break;
                case "2":
                    return;
                default:
                    System.out.println("you should choose 1-2");
                    break;
            }

        }
    }

    public static void showUser(User user, Scanner scan) {
        while (true) {
            if (!user.getAuthentication()) {
                System.out.println(Colors.ANSI_GREEN
                        + "\n\nnote : this user account has not been authentication you will see just basic informations\n\n");
            }
            System.out.println(
                    "you can se this information\n1.Name\n2.Phone number\n3. Account Idocument\n4.User transactions\n5. quit\n");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    System.out.println(
                            "first name" + " : " + user.getFirstName() + "\nlast name" + " : " + user.getLastName());
                    break;
                case "2":
                    System.out.println("phone number" + " : " + user.getPhoneNumber());
                    break;
                case "3":
                    System.out.println("Account idocument" + " : " + user.getAccount().getIDocument());
                    break;
                case "4":
                    System.out.println("Account transactions" + " : " + user.getAccount().printTrans());
                    break;
                case "5":
                    return;
                default:
                    System.out.println("You should choose 1-5");
                    break;
            }
        }
    }
}
