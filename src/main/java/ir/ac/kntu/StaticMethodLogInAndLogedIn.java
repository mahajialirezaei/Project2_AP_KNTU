package ir.ac.kntu;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class StaticMethodLogInAndLogedIn {
    public static void logIn(Map<User, String> users, Map<User, Request> requests,
            Map<SupportRequest, User> supportRequests, Scanner scan) {
        while (true) {
            String phoneNumber, password;
            System.out.println(Colors.colorString() + "Enter Your phoneNumber");
            phoneNumber = scan.nextLine();
            System.out.println("Enter Your password");
            password = scan.nextLine();
            for (User user1 : users.keySet()) {
                Object[] reqSupReq = { requests, supportRequests };
                if (user1.getPhoneNumber().equals(phoneNumber) && user1.getPassword() == Objects.hash(password)) {
                    System.out.println("Wellcome");
                    logedIn(user1, reqSupReq, users, scan);
                    return;
                }
            }
            System.out.println(Colors.colorString()
                    + "phonenumber or password is incorrect\ndo you want to continue\n1.Yes\n2.quit");
            String order1 = scan.nextLine();
            if ("2".equals(order1)) {
                return;
            } else if (!"1".equals(order1)) {
                System.out.println("You should choose 1-2");
            }
        }
    }

    public static void logedIn(User user1, Object[] reqSupReq, Map<User, String> users,
            Scanner scan) {
        Map<SupportRequest, User> supportRequests = (Map<SupportRequest, User>) reqSupReq[1];
        Map<User, Request> requests = (Map<User, Request>) reqSupReq[0];
        if (requests.get(user1).getAuthentication() == AuthenticationType.ACCEPT) {
            while (true) {
                String order = "";
                System.out.println(Colors.colorString()
                        +
                        "1.Account Management\n2.Contacts\n3.Money Transfer\n4.Support\n5.Settings\n6. quit");
                order = scan.nextLine();
                if ("6".equals(order)) {
                    return;
                }
                Object[] userScan = { users, scan };
                setAfterLogedIn(order, userScan, supportRequests, user1);
            }
        } else if (requests.get(user1).getAuthentication() == AuthenticationType.REJECT) {
            StaticMethodEditUser.edit(user1, requests, scan, users);
        } else if (requests.get(user1).getAuthentication() == AuthenticationType.INPROGRESS) {
            System.out.println(Colors.colorString() + "Support has not been checked your request");
            System.out.println(Colors.colorString()
                    + "Do you want to edit your information\nyour password card after authentication will be 1111");
            StaticMethodEditUser.edit(user1, requests, scan, users);
        }
    }

    public static void setAfterLogedIn(String order, Object[] userScan,
            Map<SupportRequest, User> supportRequests, User user1) {
        Map<User, String> users = (Map<User, String>) userScan[0];
        switch (order) {
            case "1":
                StaticMethodAccount.accountManagement(user1, (Scanner) userScan[1]);
                break;
            case "2":
                StaticMethodContacts.contacts(user1, (Scanner) userScan[1], users);
                break;
            case "3":
                StaticMethodTransfer.moneyTransfer(user1, (Scanner) userScan[1], users);
                break;
            case "4":
                StaticMethodSupport.support(user1, supportRequests, (Scanner) userScan[1]);
                break;
            case "5":
                User.settings(user1, (Scanner) userScan[1]);
                break;
            default:
                System.out.println(Colors.colorString() + "You should choice 1-6");
                break;
        }
    }

}
