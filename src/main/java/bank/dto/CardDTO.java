package bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private Long id;
    @NotNull
    private UUID idAccount;
    @NotNull
    @Positive
    private BigDecimal amount;
    @NotNull
    @Positive
    private Long cardNumber;
    @NotNull
    private Long idClient;

}
