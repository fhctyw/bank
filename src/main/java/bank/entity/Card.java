package bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private Long id;
    private BigDecimal amount;
    private Long cardNumber;
    private Long idClient;
}

