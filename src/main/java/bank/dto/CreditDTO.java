package bank.dto;

import jdk.jfr.Percentage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditDTO {
    private Long id;
    @NotNull
    private Long idClient;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private Long cardNumber;
    @Percentage
    private double percent;
    @NotNull
    private LocalDateTime createTime;
    @NotNull
    private LocalDateTime payTime;
}
