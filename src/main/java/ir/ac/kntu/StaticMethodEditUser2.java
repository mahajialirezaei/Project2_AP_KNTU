package ir.ac.kntu;

import java.util.Map;
import java.util.Scanner;

public class StaticMethodEditUser2 {

    public static void filterUsers(Scanner scan, Map<User, String> users) {
        String[] filters = new String[3];
        while (true) {
            System.out.println(
                    "you can choose field to filter it after choose press 5\n1.First Name\n2.last name\n3. phone number\n4. quit\n5. apply and see users");
            String order1 = scan.nextLine();
            if ("4".equals(order1)) {
                return;
            }
            filterUsersSwitch(scan, users, filters, order1);
        }
    }

    public static void filterUsersSwitch(Scanner scan, Map<User, String> users, String[] filters, String order1) {
        switch (order1) {
            case "1":
                System.out.println(Colors.ANSI_RED + "Enter Your First Name");
                filters[0] = scan.nextLine();
                break;
            case "2":
                System.out.println("Enter Your last name");
                filters[1] = scan.nextLine();
                break;
            case "3":
                System.out.println("Enter Your phone number");
                filters[2] = scan.nextLine();
                break;
            case "4":
                return;
            case "5":
                StaticMethodEditUser3.showFilteredUser(users, filters, scan);
            default:
                System.out.println(Colors.colorString() + "You should choose 1-5");
                break;
        }
    }

    public static void selectFilteredUser(Scanner scan, String[] filters, Map<User, String> users) {
        while (true) {
            System.out.println("Enter index or type quit\n\n");
            String order1 = scan.nextLine();
            if ("quit".equals(order1)) {
                return;
            }
            if (!order1.matches("\\d+")) {
                System.out.println("enter a number");
                continue;
            }
            int index = 0;
            for (User user : users.keySet()) {
                if ((filters[0] == null
                        || StaticMethodSimilarityAlgorithm.isSimilarity(user.getFirstName(), filters[0]))
                        && (filters[1] == null
                                || StaticMethodSimilarityAlgorithm.isSimilarity(user.getLastName(), filters[1]))
                        && (filters[2] == null
                                || StaticMethodSimilarityAlgorithm.isSimilarity(user.getPhoneNumber(), filters[2]))) {
                    if (index == Integer.parseInt(order1)) {
                        StaticMethodEditUser3.showUser(user, scan);
                    }
                    index++;
                }
            }
        }
    }

    public static void selectAllUser(Scanner scan, Map<User, String> users) {
        while (true) {
            System.out.println("Enter index or type quit\n\n");
            String order1 = scan.nextLine();
            if ("quit".equals(order1)) {
                return;
            }
            if (!order1.matches("\\d+")) {
                System.out.println("enter a number");
                continue;
            }
            int index = 0;
            for (User user : users.keySet()) {
                if (index == Integer.parseInt(order1)) {
                    StaticMethodEditUser3.showUser(user, scan);
                }
                index++;
            }
        }
    }

}
