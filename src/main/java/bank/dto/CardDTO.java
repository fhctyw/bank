package bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    @NotNull
    private Long id;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private Long cardNumber;
    @NotNull
    private Long idClient;

}
