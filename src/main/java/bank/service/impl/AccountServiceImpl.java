package bank.service.impl;

import bank.dto.AccountDTO;
import bank.entity.Account;
import bank.mapper.MapperAccount;
import bank.repository.AccountRepository;
import bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    final MapperAccount mapperAccount = new MapperAccount();
    @Autowired
    final AccountRepository accountRepository = new AccountRepository();

    @Override
    public void create(final AccountDTO dto) {
        final Account account = mapperAccount.toEntity(dto);
        accountRepository.add(account);
    }

    @Override
    public AccountDTO read(final Long id) {
        final Account account = accountRepository.get(id);
        final AccountDTO dto = mapperAccount.toDto(account);
        return dto;
    }

    @Override
    public void update(final AccountDTO dto) {
        accountRepository.update(dto.getIdClient(), dto);
    }

    @Override
    public void delete(final Long id) { ///idClient
        accountRepository.deleteByClientId(id);
    }

    @Override
    public List<AccountDTO> getAll() {
        return accountRepository.getAccounts().stream().map(mapperAccount::toDto).collect(Collectors.toList());
    }

}
