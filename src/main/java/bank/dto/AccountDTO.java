package bank.dto;

import java.util.List;

public class AccountDTO {

    private List<Integer> idCard;
    private String email;
    private Integer amount;
    private String password;

    public AccountDTO() { }

    public AccountDTO(List<Integer> idCard, Integer amount, String email, String password) {
        this.idCard = idCard;
        this.amount = amount;
        this.email = email;
        this.password = password;
    }

    public List<Integer> getIdCard() {
        return idCard;
    }

    public void setIdCard(List<Integer> idCard) {
        this.idCard = idCard;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
