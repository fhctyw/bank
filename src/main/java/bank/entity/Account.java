package bank.entity;

import java.util.List;

public class Account {
    private int id;
    private List<Integer> idCard;
    private Integer amount;
    private String email;
    private String password;

    public Account() { }

    public Account(int id, List<Integer> idCard, Integer amount, String email, String password) {
        this.id = id;
        this.idCard = idCard;
        this.amount = amount;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
