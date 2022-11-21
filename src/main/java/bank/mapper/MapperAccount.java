package bank.mapper;

import bank.dto.AccountDTO;
import bank.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class MapperAccount {

    public AccountDTO toDto(final Account account) {
        final AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setIdClient(account.getIdClient());
        dto.setCodeCurrency(account.getCodeCurrency());
        dto.setAmount(account.getAmount());
        return dto;
    }

    public Account toEntity(final AccountDTO dto) {
        final Account account = new Account();
        account.setId(dto.getId());
        account.setIdClient(dto.getIdClient());
        account.setCodeCurrency(dto.getCodeCurrency());
        account.setAmount(dto.getAmount());
        return account;
    }
}
