package ir.ac.kntu;

import java.util.Map;
import java.util.Scanner;

public class StaticMethodSupportRequest2 {
    public static void showFlitReqSec(Map<SupportRequest, User> supportRequests, Scanner scan,
            RequestSection requestSection) {
        finalShowFlirReqSec(supportRequests, requestSection);
        while (true) {
            System.out.println("1.enter your request index\n2.quit");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    order1 = scan.nextLine();
                    int index1 = 0;
                    if (order1.matches("\\d+")) {
                        index1 = Integer.parseInt(order1);
                        getSupReqSecInd(index1, supportRequests, scan, requestSection);
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

    public static void finalShowFlirReqSec(Map<SupportRequest, User> supportRequests, RequestSection requestSection) {
        int index = 0;
        boolean check1 = true;
        for (SupportRequest supportRequest : supportRequests.keySet()) {
            if (supportRequest.getRequestSection() == requestSection) {
                System.out.println(index + " " + ":" + " " + supportRequest.getRequestText()
                        + supportRequests.get(supportRequest));
                check1 = false;
                index++;
            }
        }
        if (check1) {
            System.out.println("have no any request");
        }
    }

    public static void finalShowFiltCon(Map<SupportRequest, User> supportRequests, RequestCondition requestCondition) {
        int index = 0;
        boolean check1 = true;
        for (SupportRequest supportRequest : supportRequests.keySet()) {
            if (supportRequest.getRequestCondition() == requestCondition) {
                System.out.println(index + " " + ":" + " " + supportRequest.getRequestText()
                        + supportRequests.get(supportRequest));
                check1 = false;
                index++;
            }
        }
        if (check1) {
            System.out.println("have no any request");
        }
    }

    public static void getSupReqConInd(int index1, Map<SupportRequest, User> supportRequests, Scanner scan,
            RequestCondition requestCondition) {
        int index2 = 0;
        for (SupportRequest supportRequest : supportRequests.keySet()) {
            if (index2 == index1 && supportRequest.getRequestCondition().equals(requestCondition)) {
                System.out.println(supportRequest);
                System.out.println(Colors.colorString() + "Enter your answer or press enter");
                String order1 = scan.nextLine();
                supportRequest.setAnswer(order1);
                Support.setSupportRequestCondition(supportRequest, scan);
                return;
            }
            if (supportRequest.getRequestCondition().equals(requestCondition)) {
                index2++;
            }
        }
    }

    public static void getSupReqSecInd(int index1, Map<SupportRequest, User> supportRequests, Scanner scan,
            RequestSection requestSection) {
        int index2 = 0;
        for (SupportRequest supportRequest : supportRequests.keySet()) {
            if (index2 == index1 && supportRequest.getRequestSection().equals(requestSection)) {
                System.out.println(supportRequest);
                System.out.println("Enter your answer or press enter");
                String order1 = scan.nextLine();
                supportRequest.setAnswer(order1);
                Support.setSupportRequestCondition(supportRequest, scan);
                return;
            }
            if (supportRequest.getRequestSection().equals(requestSection)) {
                index2++;
            }
        }
    }

    public static void supReqWthOutFltr(Scanner scan, Map<SupportRequest, User> supportRequests) {
        Support.finalShowWthOutFltr(supportRequests);
        while (true) {
            System.out.println("1. Enter index of your supportrequest\n2.Filter\n3.quit");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    order1 = scan.nextLine();
                    if (order1.matches("\\d+")) {
                        int index1 = Integer.parseInt(order1);
                        Support.getSupReqInd(index1, supportRequests, scan);
                    } else {
                        System.out.println("enter a number");
                    }
                    break;
                case "2":
                    StaticMethodSupportRequest.supReqWthFltr(scan, supportRequests);
                case "3":
                    return;
                default:
                    System.out.println("you should choose 1-3");
                    break;
            }
        }
    }
}
