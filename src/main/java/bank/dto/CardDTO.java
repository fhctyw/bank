package bank.dto;

public class CardDTO {
    private int idCard;
    private double amount;
    private long cardNumber;
    private int idClient;

    public CardDTO(int idCard, double amount, long cardNumber, int idClient) {
        this.idCard = idCard;
        this.amount = amount;
        this.cardNumber = cardNumber;
        this.idClient = idClient;
    }

    public CardDTO() {

    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

}
