package bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private Long id;
    @NotNull
    private Long idAccount;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private Long cardNumber;
    @NotNull
    private Long idClient;

}
