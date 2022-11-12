package bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private  UUID id;
    private  Long idClient;
    private Long idCurrency;
    private List<Long> idCards;
    private BigDecimal amount;

    @Override
    public String toString() {
        return  id + " " + idClient + " " +idCurrency +"  "+ idCards+" "+ amount;
    }
}
