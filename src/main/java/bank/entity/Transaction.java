package bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long id;
    private LocalDateTime time;
    private BigDecimal amount_of_transaction;
    private Long idReceiver;
    private Long idSender;
    private String message;

    @Override
    public String toString() {
        return  id + " " + time + " " + amount_of_transaction + " " + idReceiver + " " + idSender + " " + message;
    }
}
