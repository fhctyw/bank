package bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private int percent;
    private LocalDateTime firstDate;
    private LocalDateTime secondDate;

    @Override
    public String toString() {
        return id + " " + idClient + " " + amount + " " + percent +
                " " + firstDate + " " + secondDate;
    }
}
