package utility;

public class Form {
    private String applicantName;
    private String address;
    private int phoneNumber;

    public void fillForm(String name, String address, int phone) {
        this.applicantName = name;
        this.address = address;
        this.phoneNumber = phone;
    }

    public void printForm() {
        System.out.println("Applicant Name: " + applicantName);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
    }
}
