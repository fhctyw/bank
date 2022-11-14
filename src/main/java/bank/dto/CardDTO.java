package bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private Long idCard;
    private BigDecimal amount;
    private Long cardNumber;
    private Long idClient;

}
