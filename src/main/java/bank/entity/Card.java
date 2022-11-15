package bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private Long id;
    private BigDecimal amount;
    private Long cardNumber;
    private Long idClient;

    @Override
    public String toString() {
        return id + " " + amount + " " + cardNumber + " " + idClient;
    }

}

