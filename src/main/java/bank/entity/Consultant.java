package bank.entity;


public class Consultant {
    private int id;
    private String fullName;

    public Consultant(final int id, final String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Consultant() {

    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }
}
