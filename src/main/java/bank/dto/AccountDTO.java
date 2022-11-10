package bank.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private  UUID id;
    private  Long idClient;
    private Long idCurrency;
    private List<Long> idCards;
    private BigDecimal amount;

}
