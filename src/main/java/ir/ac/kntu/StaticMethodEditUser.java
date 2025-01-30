package ir.ac.kntu;

import java.util.Map;
import java.util.Scanner;

public class StaticMethodEditUser {
    public static void edit(User user1, Map<User, Request> requests, Scanner scan, Map<User, String> users) {
        while (true) {
            String order1 = new String();
            System.out.println(requests.get(user1).getAnswer());
            System.out.println("You could edit your information to set a new request");
            System.out.println("1.edit");
            System.out.println("2.quit");
            order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    requests.get(user1).setAuthentication(AuthenticationType.INPROGRESS);
                    editInformation(user1, scan, users);
                    break;
                case "2":
                    return;
                default:
                    System.out.println("you should choose 1-2");
                    break;
            }
        }
    }

    public static void editInformation(User user1, Scanner scan, Map<User, String> users) {
        while (true) {
            String[] fields = new String[5];
            System.out.println(
                    "choose 1-6\n1.edit firstname\n2.edit lastname\n3.edit phonenumber\n4.edit Id\n5.edit password\n6.quit");
            String order1 = scan.nextLine();
            if ("6".equals(order1)) {
                return;
            }
            boolean check1 = editInformationSwitch(fields, order1, scan), check2 = User.checkCondition(fields, users);
            setUserFields(user1, order1, check1 && check2, fields);
        }
    }

    public static void setUserFields(User user1, String order1, boolean check2, String[] fields) {
        if (check2) {
            switch (order1) {
                case "1":
                    user1.setFirstName(fields[0]);
                    break;
                case "2":
                    user1.setLastName(fields[1]);
                    break;
                case "3":
                    user1.setPhoneNumber(fields[2]);
                    break;
                case "4":
                    user1.setIDocument(fields[3]);
                    break;
                case "5":
                    user1.setPassword(fields[4]);
                    break;
                default:
                    break;
            }
            System.out.println("user has been saved");
        }
    }

    public static boolean editInformationSwitch(String[] fields, String order1, Scanner scan) {
        int index = 0;
        if (order1.matches("[1-5]")) {
            index = Integer.parseInt(order1) - 1;
            fields[index] = scan.nextLine();
        } else {
            System.out.println("You should choose 1-6");
            return false;
        }
        return !StaticMethodCheckCondition.checkEmptyField(fields[index]);
    }

    public static void users(Scanner scan, Map<User, String> users) {
        while (true) {
            System.out.println(
                    "users list menu" + "\n\n---------------------\n\n" + "1.Show User List\n2. Filter\n 3.quit");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    StaticMethodEditUser3.showUserList(scan, users);
                    break;
                case "2":
                    StaticMethodEditUser2.filterUsers(scan, users);
                    break;
                case "3":
                    return;
                default:
                    System.out.println(Colors.ANSI_RED + "you should choose 1-3");
                    break;
            }
        }
    }

}
