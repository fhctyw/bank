package bank.dto;

import jdk.jfr.Percentage;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MakeCreditDTO {
    @NotNull
    private Long idCard;
    @Positive
    private BigDecimal amount;
    @Percentage
    private int percent;
    @NotNull
    private LocalDateTime deadlineDate;
}
