package bank.service;

import bank.dto.AccountDTO;
import bank.entity.Account;

public interface AccountService {
    void create(AccountDTO dto);
    AccountDTO read(Long id);
    void update(AccountDTO dto);
    void delete(Long id);
}
