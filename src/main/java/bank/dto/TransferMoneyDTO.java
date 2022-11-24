package bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferMoneyDTO {
    @NotNull
    private Long numberCardSender;
    @NotNull
    private Long numberCardReceiver;
    @Positive
    private BigDecimal amount;
    @NotBlank
    private String message;
}
