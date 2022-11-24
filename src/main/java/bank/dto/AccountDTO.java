package bank.dto;

import bank.annotations.CodeCurrency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private UUID id;
    @NotNull
    private Long idClient;
    @CodeCurrency
    private String codeCurrency;
    @Positive
    private BigDecimal amount;

}
