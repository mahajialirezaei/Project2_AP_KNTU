package ir.ac.kntu;

import java.util.Map;
import java.util.Scanner;

public class StaticMethodSignUp {
    public static boolean signUp(Map<User, String> users, Map<User, Request> requests,
            Map<SupportRequest, User> supportRequests, Scanner scan) {
        while (true) {
            String[] fields = new String[5];
            System.out.println(Colors.LAVENDER + "Enter Your firstname");
            fields[0] = scan.nextLine();
            System.out.println("Enter Your lastname");
            fields[1] = scan.nextLine();
            System.out.println("Enter Your phonenumber");
            fields[2] = scan.nextLine();
            System.out.println("Enter Your id");
            fields[3] = scan.nextLine();
            System.out.println("Enter Your password");
            fields[4] = scan.nextLine();
            boolean check1 = StaticMethodCheckCondition.checkEmptyUser(fields),
                    check2 = User.checkCondition(fields, users), check3 = User.checkName(fields);
            if (!(check2 && !check1 && check3)) {
                return false;
            }
            User tmp = new User(fields[0], fields[1], fields[2], fields[3], fields[4]);
            users.put(tmp, fields[4]);
            Request tmpRequest = new Request(AuthenticationType.INPROGRESS, "");
            requests.put(tmp, tmpRequest);
            System.out.println(
                    Colors.AQUA + "Successfully Signed up\n"
                            + "your card password after authentication will be 1111");
            return true;
        }
    }
}
