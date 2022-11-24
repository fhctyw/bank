package bank.dto;

import jdk.jfr.Percentage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakeDepositDTO {
    @Positive
    private BigDecimal amount;
    @NotNull
    private Long cardNumber;
    @NotNull
    private LocalDateTime putTime;
    @NotNull
    private LocalDateTime withdrawTime;
    @Percentage
    private double percentage;
}
