package bank.dto;

public class ConsultantDto {
    private int id;
    private String fullName;

    public ConsultantDto(final int id, final String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public ConsultantDto() {

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
