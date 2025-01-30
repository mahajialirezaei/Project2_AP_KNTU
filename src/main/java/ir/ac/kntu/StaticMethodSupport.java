package ir.ac.kntu;

import java.util.Map;
import java.util.Scanner;

public class StaticMethodSupport {
    public static void support(User user1, Map<SupportRequest, User> supportRequests, Scanner scan) {
        boolean registered = false;
        while (true) {
            System.out.println("Support Menu" + Colors.ANSI_GREEN +
                    "choose 1-5\n1.Report\n2.Contacts\n3.Transfer\n4.Setting\n5. quit\n6.Review supportRequests");
            String order1 = scan.nextLine();
            if ("5".equals(order1)) {
                return;
            }
            if (order1.matches("[1-4]")) {
                registered = true;
            }
            SupportRequest supportRequest = supportSwitch(order1, supportRequests, scan, user1);
            if (registered) {
                supportRequest.setRequestCondition(RequestCondition.REGISTERED);
                supportRequest.setUserPhonenumber(user1.getPhoneNumber());
                System.out.println("Enter your request text");
                order1 = scan.nextLine();
                supportRequest.setRequestText(order1);
                supportRequests.put(supportRequest, user1);
                user1.getSupReqUser().put(supportRequest, user1);
                registered = false;
            }
        }
    }

    public static SupportRequest supportSwitch(String order1, Map<SupportRequest, User> supportRequests, Scanner scan,
            User user1) {
        SupportRequest supportRequest = new SupportRequest();
        switch (order1) {
            case "1":
                supportRequest.setRequestSection(RequestSection.REPORT);
                break;
            case "2":
                supportRequest.setRequestSection(RequestSection.CONTACT);
                break;
            case "3":
                supportRequest.setRequestSection(RequestSection.TRANSFER);
                break;
            case "4":
                supportRequest.setRequestSection(RequestSection.SETTING);
                break;
            case "6":
                reviewSupportRequsets(supportRequests, user1, scan);
                break;
            default:
                System.out.println("you should choose 1-5");
                break;
        }
        return supportRequest;
    }

    public static void reviewSupportRequsets(Map<SupportRequest, User> supportRequests, User user1, Scanner scan) {
        int index = 0;
        for (SupportRequest supportRequest : supportRequests.keySet()) {
            if (supportRequests.get(supportRequest).equals(user1)) {
                System.out.println(index + "." + " " + supportRequest.getRequestText());
            }
            index++;
        }
        while (true) {
            System.out.println("do you want to see any supportReport");
            System.out.println("1.yes");
            System.out.println("2.exit");
            String order1 = scan.nextLine();
            switch (order1) {
                case "1":
                    seeSupportRequestInformation(supportRequests, user1, scan);
                    break;
                case "2":

                    return;
                default:
                    System.out.println(Colors.ANSI_RED + "you should choose 1-2");
                    break;
            }
        }
    }

    public static void seeSupportRequestInformation(Map<SupportRequest, User> supportRequests, User user1,
            Scanner scan) {
        while (true) {
            System.out.println("Enter index of you supportRequest object or type quit");
            String order1 = scan.nextLine();
            if ("quit".equals(order1)) {
                return;
            }
            if (!order1.matches("\\d+")) {
                System.out.println("Enter a number");
                continue;
            }
            if (Integer.parseInt(order1) >= supportRequests.size()) {
                System.out.println(Colors.ANSI_RED + "You should choose 0-" + (user1.getSupReqUser().size() - 1));
            } else {
                int index = Integer.parseInt(order1);
                int index1 = 0;
                for (SupportRequest supportRequest : user1.getSupReqUser().keySet()) {
                    if (index1 == index) {
                        System.out.println(supportRequest);
                    }
                    index1++;
                }
            }
        }
    }

}
