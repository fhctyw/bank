package bank.entity;

import jdk.jfr.Percentage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deposit {
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
    private LocalDate putTime;
    private LocalDate withdrawTime;
    @Percentage
    private double percentage;

    @Override
    public String toString() {
        return client + " " + amount + " " + depositId + " " + cardId + " " + consultantId + " " + putTime + " " + withdrawTime + " " + percentage;
    }
}
