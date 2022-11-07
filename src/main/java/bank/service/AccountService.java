package bank.service;

import bank.dto.AccountDTO;
import bank.entity.Account;

public interface AccountService {
    void create(AccountDTO dto);
    Account read(int id);
    void update(AccountDTO dto);
    void delete(int id);
}
