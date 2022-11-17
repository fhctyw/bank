package bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditDTO {
    @NotNull
    private Long id;
    @NotNull
    private Long idClient;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private int percent;
    private Date firstDate;
    private Date secondDate;
}
