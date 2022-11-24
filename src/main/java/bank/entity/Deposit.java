package bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deposit {
    private BigDecimal amount;
    private Long Id;
    private Long cardId;
    private LocalDateTime putTime;
    private LocalDateTime withdrawTime;
    private double percentage;

    @Override
    public String toString() {
        return amount + " " + Id + " " + cardId + " "  + putTime + " " + withdrawTime + " " + percentage;
    }
}
