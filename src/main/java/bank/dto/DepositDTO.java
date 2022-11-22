package bank.dto;

import jdk.jfr.Percentage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositDTO {
    @NotNull
    @Positive
    private BigDecimal amount;
    @NotNull
    private Long Id;
    @NotNull
    private Long cardId;
    @NotNull
    private Long consultantId;
    @NotNull
    private LocalDateTime putTime;
    private LocalDateTime withdrawTime;
    @Percentage
    private double percentage;
}
