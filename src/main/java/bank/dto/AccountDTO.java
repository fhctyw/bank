package bank.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private  UUID id;
    @NotNull
    private  Long idClient;
    @NotBlank
    private String codeCurrency;
    @NotNull
    @Positive
    private BigDecimal amount;

}
