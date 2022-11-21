package bank.service;

import bank.dto.AccountDTO;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountDTO create(AccountDTO dto);

    AccountDTO read(UUID id);

    AccountDTO update(AccountDTO dto);

    AccountDTO delete(UUID id);

    List<AccountDTO> getAll();

}
