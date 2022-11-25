package bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakeCardResponseDTO {
    private AccountDTO account;
    private CardDTO card;
}
