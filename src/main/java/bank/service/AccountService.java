package bank.service;

import bank.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    void create(AccountDTO dto);
    AccountDTO read(Long id);
    void update(AccountDTO dto);
    void delete(Long id);
    List<AccountDTO> getAll();
}
