package bank.dto;

import jdk.jfr.Percentage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositDTO {
    @NotBlank
    private String client;
    private BigDecimal amount;
    @NotNull
    private Long depositId;
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
