package bank.dto;

import bank.annotations.CodeCurrency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDTO {
    @CodeCurrency
    private String code;
    @Positive
    private BigDecimal value;
}
