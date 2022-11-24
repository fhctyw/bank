package bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credit {

    private Long id;
    private Long idClient;
    private BigDecimal amount;
    private Long cardNumber;
    private double percent;
    private LocalDateTime createTime;
    private LocalDateTime payTime;
}
