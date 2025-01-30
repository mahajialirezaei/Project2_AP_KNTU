package ir.ac.kntu;

import java.util.Objects;

public class Person {
    private String firstName = new String();
    private String lastName = new String();
    private String iDocument = new String();
    private int password;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIDocument() {
        return this.iDocument;
    }

    public void setIDocument(String iDocument) {
        this.iDocument = iDocument;
    }

    public int getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = Objects.hash(password);
    }

    public Person() {

    }

    public Person(String firstName, String lastName, String iDocument, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setIDocument(iDocument);
        setPassword(password);
    }

    @Override
    public String toString() {
        return "{" +
                " firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", iDocument='" + getIDocument() + "'" +
                "}";
    }


}
