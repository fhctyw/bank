package bank.service.impl;

import bank.dto.AccountDTO;
import bank.entity.Account;
import bank.entity.Consultant;
import bank.mapper.MapperAccount;
import bank.repository.AccountRepository;
import bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    final MapperAccount mapperAccount= new MapperAccount();
    @Autowired
    final AccountRepository AccountRepository = new AccountRepository();

    @Override
    public void create(AccountDTO dto) {
        final Account account = mapperAccount.toEntity(dto);
        AccountRepository.add(account);
    }

    @Override
    public Account read(int id) {
        return null;
    }

    @Override
    public void update(AccountDTO dto) {

    }

    @Override
    public void delete(int id) {

    }
}
