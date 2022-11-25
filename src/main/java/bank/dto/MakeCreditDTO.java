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
public class MakeCreditDTO {
    @NotNull
    private Long idCard;
    @Positive
    private BigDecimal amount;
    @Percentage
    private double percent;
    @NotNull
    private LocalDateTime payTime;
}
