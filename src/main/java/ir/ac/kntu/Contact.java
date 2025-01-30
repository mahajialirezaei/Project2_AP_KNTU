package ir.ac.kntu;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String iDocument;

    public String getIDocument() {
        return this.iDocument;
    }

    public void setIDocument(String phonenumber) {
        this.iDocument = "IR" + phonenumber.substring(1);
    }

    public Contact(String firstName, String lastName, String phoneNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setIDocument(phoneNumber);
    }

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

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "{" +
                " firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", phoneNumber='" + getPhoneNumber() + "'" +
                "}";
    }

    public String privateInfo() {
        return "{" +
                " firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                "}";
    }
}
