package bank.service.impl;

import bank.dto.AccountDTO;
import bank.entity.Account;
import bank.exception.ServiceException;
import bank.mapper.MapperAccount;
import bank.repository.AccountRepository;
import bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    final MapperAccount mapperAccount = new MapperAccount();
    @Autowired
    final AccountRepository accountRepository = new AccountRepository();

    @Override
    public AccountDTO create(final AccountDTO dto) {
        accountRepository.add(mapperAccount.toEntity(dto));
        final Account accountInRepos = accountRepository.getAccounts().get(accountRepository.getAccounts().size() - 1);
        return mapperAccount.toDto(accountInRepos);
    }

    @Override
    public AccountDTO read(final UUID id) {
        return mapperAccount.toDto(accountRepository.findById(id));
    }

    @Override
    public AccountDTO update(final AccountDTO dto) {
        accountRepository.update(dto.getId(), dto);
        return mapperAccount.toDto(accountRepository.findById(dto.getId()));
    }

    @Override
    public AccountDTO delete(final UUID id) {
        final Account account = accountRepository.findById(id);
        accountRepository.delete(id);
        return mapperAccount.toDto(account);
    }

    @Override
    public List<AccountDTO> getAll() {
        return accountRepository.getAccounts().stream().map(mapperAccount::toDto).collect(Collectors.toList());
    }

}
