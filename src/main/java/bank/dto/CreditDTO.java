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
    @NotNull
    private Long idConsultant;
    @NotNull
    private Long id;
    @NotNull
    private Long idClient;
    @NotNull
    private BigDecimal amount;
    @Percentage
    private int percent;
    @NotNull
    private LocalDateTime createDate;
    @NotNull
    private LocalDateTime deadlineDate;
}
