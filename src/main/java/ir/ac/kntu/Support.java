package ir.ac.kntu;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Support extends Person {
    Support() {
        super();
    }

    Support(String firstName, String lastName, String iDocument, String password) {
        super(firstName, lastName, iDocument, password);
    }

    public static void logIn(Map<Support, String> supports, Scanner scan, Object[] reqSupreq, Map<User, String> users) {
        while (true) {
            String iDocument;
            String password;
            Map<User, Request> requests = (Map<User, Request>) reqSupreq[0];
            Map<SupportRequest, User> supportRequests = (Map<SupportRequest, User>) reqSupreq[1];
            System.out.println(Colors.colorString()
                    + "Enter Your id");
            iDocument = scan.nextLine();
            System.out.println(Colors.colorString()
                    + "Enter Your password");
            password = scan.nextLine();
            for (Support support1 : supports.keySet()) {
                if (support1.getIDocument().equals(iDocument) && support1.getPassword() == Objects.hash(password)) {
                    System.out.println(Colors.colorString()
                            + "Wellcome\n\n" + "---------------------\n\n");
                    logedIn(scan, requests, supportRequests, users);
                    return;
                }
            }
            System.out.println(Colors.colorString()
                    + "id or password is incorrect\ndo you want to continue\n1.Yes\n2.quit");
            String order1 = scan.nextLine();
            if ("2".equals(order1)) {
                return;
            } else if (!"1".equals(order1)) {
                System.out.println(Colors.colorString()
                        + "You should choose 1-2");
            }
        }
    }

    public static void logedIn(Scanner scan, Map<User, Request> requests, Map<SupportRequest, User> supportRequests,
            Map<User, String> users) {
        while (true) {
            String order = "";
            System.out.println(
                    Colors.colorString() + "Support Menu\n" + "---------------------\n\n" + "1.Authentication");
            System.out.println("2.Requests");
            System.out.println("3.Users");
            System.out.println("4. quit");
            order = scan.nextLine();
            switch (order) {
                case "1":
                    authentication(requests, scan);
                    break;
                case "2":
                    StaticMethodSupportRequest.supportRequests(scan, supportRequests);
                    break;
                case "3":
                    StaticMethodEditUser.users(scan, users);
                    break;
                case "4":
                    return;
                default:
                    System.out.println(Colors.ANSI_RED + "You should choice 1-4");
                    break;
            }
        }
    }

    public static void finalShowWthOutFltr(Map<SupportRequest, User> supportRequests) {
        int index = 0;
        boolean check1 = true;
        for (SupportRequest supportRequest : supportRequests.keySet()) {
            System.out.println(
                    index + " " + ":" + " " + supportRequest.getRequestText() + supportRequests.get(supportRequest));
            check1 = false;
            index++;
        }
        if (check1) {
            System.out.println("have no any request");
        }
    }

    public static void getSupReqInd(int index1, Map<SupportRequest, User> supportRequests, Scanner scan) {
        int index2 = 0;
        for (SupportRequest supportRequest : supportRequests.keySet()) {
            if (index2 == index1) {
                System.out.println(supportRequest);
                System.out.println("Enter your answer or press enter");
                String order1 = scan.nextLine();
                setSupportRequestCondition(supportRequest, scan);
                supportRequest.setAnswer(order1);
                return;
            }
            index2++;
        }
    }

    public static void setSupportRequestCondition(SupportRequest supportRequest, Scanner scan) {
        while (true) {
            System.out
                    .println(Colors.colorString() + "\n\n---------------------\n\n"
                            + "1.REGESTRED\n2.IN PROGRESS\n3. CLOSED");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    supportRequest.setRequestCondition(RequestCondition.REGISTERED);
                    return;
                case "2":
                    supportRequest.setRequestCondition(RequestCondition.INPROGRESS);
                    return;
                case "3":
                    supportRequest.setRequestCondition(RequestCondition.CLOSED);
                    return;
                default:
                    System.out.println("you should choose 1-3");
                    break;
            }
        }
    }

    public static void authentication(Map<User, Request> requests, Scanner scan) {
        for (User user1 : requests.keySet()) {
            System.out.println(user1.getPhoneNumber() + requests.get(user1).toString());
        }
        while (true) {
            System.out.println(Colors.colorString() + "do you want to see information of any user");
            System.out.println("1.yes");
            System.out.println("2.quit");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    for (User user1 : requests.keySet()) {
                        System.out.println(user1.getPhoneNumber() + requests.get(user1).toString());
                    }
                    coniformationUser(requests, scan);
                    break;
                case "2":
                    return;
                default:
                    System.out.println("You should choose 1-2");
                    break;
            }
        }

    }

    public static void coniformationUser(Map<User, Request> requests, Scanner scan) {
        while (true) {
            System.out.println(Colors.colorString() + "Enter you object user phonenumber or enter 0 to quit");
            String order1 = new String();
            order1 = scan.nextLine();
            if (order1.matches("\\d{11}")) {
                for (User user1 : requests.keySet()) {
                    if (user1.getPhoneNumber().equals(order1)) {
                        System.out.println(user1.toString());
                        finalConiform(user1, requests, scan);
                        return;
                    }
                }
                System.out.println("not found");
            } else if ("0".equals(order1)) {
                return;
            } else {
                System.out.println("phone number structure is incorrect");
            }
        }

    }

    public static void finalConiform(User user1, Map<User, Request> requests, Scanner scan) {
        while (true) {
            System.out.println(Colors.colorString() + "1. coniform\n2.reject\n3.quit");
            String order = scan.nextLine();
            switch (order) {
                case "1":
                    requests.get(user1).setAuthentication(AuthenticationType.ACCEPT);
                    user1.setAuthentication(true);
                    requests.get(user1).setAnswer("");
                    user1.setAccount("IR" + user1.getPhoneNumber().substring(1));
                    return;
                case "2":
                    requests.get(user1).setAuthentication(AuthenticationType.REJECT);
                    user1.setAuthentication(false);
                    System.out.println("enter your answer");
                    order = scan.nextLine();
                    requests.get(user1).setAnswer(order);
                    return;
                case "3":
                    return;
                default:
                    System.out.println(Colors.colorString() + "You should choose 1-3");
                    break;
            }
        }
    }

}
