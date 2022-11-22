package bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDTO {
    private ClientDTO client;
    private AccountDTO account;
    private CardDTO card;
}
