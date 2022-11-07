package bank.entity;

public class Client {
    private String fullName;
    private String email;
    private int id;
    private int phoneNumber;

    public Client() {
    }

    public Client(String fullName, String email, int id, int phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
