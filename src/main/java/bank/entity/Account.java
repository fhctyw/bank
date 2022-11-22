package bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private UUID id;
    private Long idClient;
    private String codeCurrency;
    private BigDecimal amount;
}
