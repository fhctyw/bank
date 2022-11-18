package bank.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private  UUID id;
    @NotNull
    private  Long idClient;
    @NotNull
    private Long idCurrency;
    @NotNull
    private BigDecimal amount;

}
