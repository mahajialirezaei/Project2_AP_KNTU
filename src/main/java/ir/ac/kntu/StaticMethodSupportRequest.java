package ir.ac.kntu;

import java.util.Map;
import java.util.Scanner;

public class StaticMethodSupportRequest {
    public static void supportRequests(Scanner scan, Map<SupportRequest, User> supportRequests) {
        while (true) {
            System.out.println("1.without filter\n2.quit");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    StaticMethodSupportRequest2.supReqWthOutFltr(scan, supportRequests);
                    break;
                case "2":
                    return;
                default:
                    System.out.println("you should choose 1-2");
                    break;
            }
        }
    }

    public static void supReqWthFltr(Scanner scan, Map<SupportRequest, User> supportRequests) {
        while (true) {
            System.out.println(Colors.LAVENDER + "Support Filter Menu\n\n"
                    + "1.Filter by RequestSection\n2.Filter by Requestcondition\n3.Filter by user\n4.quit");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    filterByReqSec(supportRequests, scan);
                    break;
                case "2":
                    filterByReqCon(supportRequests, scan);
                    break;
                case "3":
                    filterByUser(supportRequests, scan);
                    break;
                case "4":
                    return;
                default:
                    break;
            }
        }
    }

    public static void filterByUser(Map<SupportRequest, User> supportRequests, Scanner scan) {
        while (true) {
            System.out.println("1.Enter your user phone number\n2. quit\n");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    order1 = scan.nextLine();
                    if (order1.matches("\\d{11}")) {
                        showFlitUser(supportRequests, scan, order1);
                    } else {
                        System.out.println("phone number structure is not correct");
                    }
                    break;
                case "2":
                    return;
                default:
                    System.out.println("You should choose 1-2");
                    break;
            }
        }
    }

    public static void showFlitUser(Map<SupportRequest, User> supportRequests, Scanner scan, String userPhoneNumber) {
        finalShowFiltUser(supportRequests, userPhoneNumber);
        while (true) {
            System.out.println("1.enter your request index\n2.quit\n");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    order1 = scan.nextLine();
                    if (order1.matches("\\d+")) {
                        int index1 = Integer.parseInt(order1);
                        getSupUserInd(index1, supportRequests, scan, userPhoneNumber);
                    } else {
                        System.out.println("enter a number");
                    }
                    break;
                case "2":
                    return;
                default:
                    System.out.println("you should choose 1-2");
                    break;
            }
        }
    }

    public static void finalShowFiltUser(Map<SupportRequest, User> supportRequests, String userPhoneNumber) {
        int index = 0;
        boolean check1 = true;
        for (SupportRequest supportRequest : supportRequests.keySet()) {
            if (supportRequest.getUserPhonenumber().equals(userPhoneNumber)) {
                System.out.println(index + " " + ":" + " " + supportRequest.toString());
                index++;
                check1 = false;
            }
        }
        if (check1) {
            System.out.println("have no any request");
        }
    }

    public static void getSupUserInd(int index1, Map<SupportRequest, User> supportRequests, Scanner scan,
            String userPhoneNumber) {
        int index2 = 0;
        for (SupportRequest supportRequest : supportRequests.keySet()) {
            if (index2 == index1 && supportRequest.getUserPhonenumber().equals(userPhoneNumber)) {
                System.out.println(supportRequest);
                System.out.println("Enter your answer or press enter");
                String order1 = scan.nextLine();
                supportRequest.setAnswer(order1);
                Support.setSupportRequestCondition(supportRequest, scan);
                return;
            }
            if (supportRequest.getUserPhonenumber().equals(userPhoneNumber)) {
                index2++;
            }
        }
    }

    public static void filterByReqCon(Map<SupportRequest, User> supportRequests, Scanner scan) {
        while (true) {
            System.out
                    .println("Condition Menu\n\n" + Colors.ANSI_BLUE + "1.REGISTERED\n2.INPROGRESS\n3.CLOSED\n4.quit");
            RequestCondition requestCondition;
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    requestCondition = RequestCondition.REGISTERED;
                    break;
                case "2":
                    requestCondition = RequestCondition.INPROGRESS;
                    break;
                case "3":
                    requestCondition = RequestCondition.CLOSED;
                    break;
                case "4":
                    return;
                default:
                    System.out.println("you should choose 1-4");
                    continue;
            }
            showFlitReqCon(supportRequests, scan, requestCondition);
        }
    }

    public static void filterByReqSec(Map<SupportRequest, User> supportRequests, Scanner scan) {
        while (true) {
            System.out.println("1.REPORT\n2.CONTACT\n3.TRANSFER\n4.SETTING\n5.quit");
            RequestSection requestSection;
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    requestSection = RequestSection.REPORT;
                    break;
                case "2":
                    requestSection = RequestSection.CONTACT;
                    break;
                case "3":
                    requestSection = RequestSection.TRANSFER;
                    break;
                case "4":
                    requestSection = RequestSection.SETTING;
                    break;
                case "5":
                    return;
                default:
                    System.out.println("you should choose 1-5");
                    continue;
            }
            StaticMethodSupportRequest2.showFlitReqSec(supportRequests, scan, requestSection);
        }
    }

    public static void showFlitReqCon(Map<SupportRequest, User> supportRequests, Scanner scan,
            RequestCondition requestCondition) {
        StaticMethodSupportRequest2.finalShowFiltCon(supportRequests, requestCondition);
        while (true) {
            System.out.println("1.enter your request index\n2.quit");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    order1 = scan.nextLine();
                    int index1 = 0;
                    if (order1.matches("\\d+")) {
                        index1 = Integer.parseInt(order1);
                        StaticMethodSupportRequest2.getSupReqConInd(index1, supportRequests, scan, requestCondition);
                    } else {
                        System.out.println("enter a number");
                    }
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
