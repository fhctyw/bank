package bank.mapper;

import bank.dto.AccountDTO;
import bank.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class MapperAccount {

    public AccountDTO toDTO(final Account account) {
        final AccountDTO dto = new AccountDTO();
        dto.setEmail(account.getEmail());
        dto.setPassword(account.getPassword());
        dto.setAmount(account.getAmount());
        dto.setIdCard(account.getIdCard());
        return dto;
    }
    public Account toEntity(final AccountDTO dto) {
        final Account account = new Account();
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());
        account.setAmount(dto.getAmount());
        account.setIdCard(dto.getIdCard());
        return account;
    }
}
