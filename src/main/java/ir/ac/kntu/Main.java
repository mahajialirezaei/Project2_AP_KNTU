package ir.ac.kntu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import ir.ac.kntu.fantesic.RunningManBye;
import ir.ac.kntu.fantesic.RunningManHello;

public class Main {

    public static void main(String[] args) {
        RunningManHello.main(args);
        Map<User, String> users = new HashMap<>();
        Map<Support, String> supports = new HashMap<>();
        Map<User, Request> requests = new HashMap<>();
        Map<SupportRequest, User> supportRequest = new HashMap<>();
        supports.put(new Support("sample", "samplefamily", "1234564578", "fefdfrf%###R74"), "fefdfrf%###R74");
        supports.put(new Support("dsdsd", "samplefamily", "48488484", "fefdfrf%###R74"), "fefdfrf%###R74");
        supports.put(new Support("sample1", "samplefamily", "554848484", "fefdfrf%###R74"), "fefdfrf%###R74");

        // String order = new String();
        menu(users, supports, requests, supportRequest);
        RunningManBye.main(args);
    }

    public static void menu(Map<User, String> users, Map<Support, String> supports,
            Map<User, Request> requests, Map<SupportRequest, User> supportRequest) {
        String order = new String();
        Scanner scan = new Scanner(System.in);
        try {
            while (true) {
                System.out.println(Colors.ANSI_RED + "---------------------\n\n" + "Main Menu\n\n" +
                        Colors.ANSI_BLUE + "Choose Your Access" + Colors.ANSI_GREEN + "\n1.User\n2.Support\n3.quit\n");
                order = scan.nextLine();
                switch (order) {
                    case "1":
                        user(users, requests, supportRequest, scan);
                        break;
                    case "2":
                        Object[] reqsSupreq = { requests, supportRequest };
                        support(supports, reqsSupreq, users, scan);
                        break;
                    case "3":
                        return;
                    default:
                        System.out.println(Colors.colorString() + "You should choose 1-3\n");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(Colors.colorString() + "Enter a valid index");
            menu(users, supports, requests, supportRequest);
        } finally {
            scan.close();
        }
    }

    public static void support(Map<Support, String> supports, Object[] reqsSupreq,
            Map<User, String> users, Scanner scan) {
        String order2 = new String();
        while (true) {
            System.out.println(
                    "Support Menu\n\n" + "---------------------\n\n" + Colors.colorString() + "1.Log in\n2.quit\n");
            order2 = scan.nextLine();

            switch (order2) {
                case "1":

                    Support.logIn(supports, scan, reqsSupreq, users);
                    break;
                case "2":
                    return;
                default:
                    System.out.println(Colors.colorString() + "You should choose 1-2");
                    break;
            }
        }
    }

    public static void user(Map<User, String> users, Map<User, Request> requests,
            Map<SupportRequest, User> supportRequest, Scanner scan) {
        String order2 = new String();
        while (true) {
            System.out
                    .println(Colors.ANSI_BLUE + "User Menu\n\n" + Colors.colorString() + "1.Sign Up\n2.Log in\n3.quit"
                            + "\n\n---------------------\n\n");
            order2 = scan.nextLine();
            switch (order2) {
                case "1":
                    StaticMethodSignUp.signUp(users, requests, supportRequest, scan);
                    break;
                case "2":
                    StaticMethodLogInAndLogedIn.logIn(users, requests, supportRequest, scan);
                    break;
                case "3":
                    return;
                default:
                    System.out.println(Colors.ANSI_RED + "You should choose 1-3");
                    break;
            }
        }
    }
}
