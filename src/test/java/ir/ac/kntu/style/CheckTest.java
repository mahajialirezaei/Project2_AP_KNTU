package ir.ac.kntu.style;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ir.ac.kntu.Contact;
import ir.ac.kntu.StaticMethodMoneyTransfer;
import ir.ac.kntu.StaticMethodSimilarityAlgorithm;
import ir.ac.kntu.User;

public class CheckTest {

        @Test
        public void testCheckCondition() {
                User user1 = new User("Firstname", "LastName", "09145211245",
                                "0254154141", "Aafef@@484ed");
                Map<User, String> users = new HashMap<>();
                String[] fields = { "Firstname", "LastName", "09014147412",
                                "0254114575", "Aafef@@484ed" };
                users.put(user1, "Aafef@@484ed");
                assertTrue(User.checkCondition(fields, users));

        }

        @Test
        public void testNotChangedIDocumentAfterChangePhoneNumber() {
                User user1 = new User("Firstname", "LastName", "09014147412",
                                "0251414125", "Aafef@@484ed");
                user1.setAuthentication(true);
                user1.setAccount("IR" + user1.getPhoneNumber().substring(1));
                user1.setPhoneNumber("09448215417");
                assertEquals(user1.getAccount().getIDocument(), "IR" + "9014147412");
        }

        @Test
        public void testUserToString() {
                User user1 = new User("Firstname", "LastName", "09014147412",
                                "0251414125", "Aafef@@484ed");
                assertEquals("{" +
                                " firstName='" + "Firstname" + "'" +
                                ", lastName='" + "LastName" + "'" +
                                ", iDocument='" + "0251414125" + "'" +
                                "}" + "{" +
                                " phoneNumber='" + "09014147412" + "'" +
                                "}", user1.toString());

        }

        @Test
        public void testisSimilitary() {
                User user1 = new User("Firstname", "LastName", "09014147412",
                                "0251414125", "Aafef@@484ed");
                assertTrue(StaticMethodSimilarityAlgorithm.isSimilarity(user1.getFirstName(), "First"));

        }

        @Test
        public void testIsCardNumberCorrect() {
                User user1 = new User("Firstname", "LastName", "09014147412",
                                "0251414125", "Aafef@@484ed");
                user1.setAccount("IR" + user1.getPhoneNumber().substring(1));
                assertEquals(user1.getAccount().getCard().getIDocument(), "9014147412");
        }

        @Test
        public void testIsCardNumberCorrectAfterChangePhoneNumber() {
                User user1 = new User("Firstname", "LastName", "09014147412",
                                "0251414125", "Aafef@@484ed");
                user1.setAccount("IR" + user1.getPhoneNumber().substring(1));
                user1.setPhoneNumber("09211414214");
                assertEquals(user1.getAccount().getCard().getIDocument(), "9014147412");

        }

        @Test
        public void testShowTransferedDetails() {
                User user1 = new User("Firstname", "LastName", "09014147412",
                                "0251414125", "Aafef@@484ed");
                user1.setAccount("IR" + user1.getPhoneNumber().substring(1));
                User user = new User("Firstname", "LastName", "09021414527",
                                "0251414125", "Aafef@@484ed");
                user.setAccount("IR" + user.getPhoneNumber().substring(1));
                user1.getUserContacts()
                                .add(new Contact(user.getFirstName(), user.getLastName(), user.getPhoneNumber()));
                user.getUserContacts()
                                .add(new Contact(user1.getFirstName(), user1.getLastName(), user1.getPhoneNumber()));
                HashMap<User, String> users = new HashMap<User, String>();
                users.put(user1, user1.getPhoneNumber());
                users.put(user, user.getPhoneNumber());
                String phoneNumber = "09021414527";
                assertTrue(StaticMethodMoneyTransfer.mutualRelContact(phoneNumber, users, user1));

        }
}
