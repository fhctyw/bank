package bank.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    @NotBlank
    private  UUID id;
    @NotNull
    private  Long idClient;
    @NotNull
    private Long idCurrency;
    @NotBlank
    private List<Long> idCards;
    @NotNull
    private BigDecimal amount;

}
