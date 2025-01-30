package ir.ac.kntu;

public class StaticMethodCheckCondition {
    public static boolean checkEmptyPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 0) {
            System.out.println("phoneNumber field hasn't itialized");
            return true;
        }
        return false;
    }

    public static boolean checkEmptyLastName(String lastName) {
        if (lastName.length() == 0) {
            System.out.println("lastname field hasn't itialized");
            return true;
        }
        return false;
    }

    public static boolean checkEmptyFirstName(String firstName) {
        if (firstName.length() == 0) {
            System.out.println("firstname field hasn't itialized");
            return true;
        }
        return false;
    }

    public static boolean checkEmptyIDocument(String iDocument) {
        if (iDocument.length() == 0) {
            System.out.println("iDocument field hasn't itialized");
            return true;
        }
        return false;
    }

    public static boolean checkEmptyPassword(String password) {
        if (password.length() == 0) {
            System.out.println("password field hasn't itialized");
            return true;
        }
        return false;
    }

    public static boolean checkCondition(String[] fields,
            User user1) {
        if (fields[2].length() > 0) {
            if (!fields[2].matches("\\d{11}")) {
                System.out.println("Invalid phonenumber");
                return false;
            }
        }
        if (fields[2].length() > 0) {
            for (Contact contact1 : user1.getUserContacts()) {
                if (contact1.getPhoneNumber().equals(fields[2])) {
                    System.out.println("Repititous phonenumber");
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean checkEmptyContact(String[] fields) {
        return checkEmptyFirstName(fields[0]) || checkEmptyLastName(fields[1]) || checkEmptyPhoneNumber(fields[2]);
    }

    public static boolean checkEmptyUser(String[] fields) {
        return checkEmptyFirstName(fields[0]) || checkEmptyLastName(fields[1]) || checkEmptyPhoneNumber(fields[2])
                || checkEmptyIDocument(fields[3]) || checkEmptyPassword(fields[4]);

    }

    public static boolean checkEmptyField(String string) {
        if (string.length() == 0) {
            System.out.println("field hasn't itialized");
            return true;
        }
        return false;
    }
}
