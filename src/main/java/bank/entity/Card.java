package bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    private Long id;
    private UUID idAccount;
    private BigDecimal amount;
    private Long cardNumber;
    private Long idClient;
}

