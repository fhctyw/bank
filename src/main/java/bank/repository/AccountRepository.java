package bank.repository;

//import bank.db.FileAccount;
import bank.dto.AccountDTO;
import bank.entity.Account;
import bank.entity.Consultant;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountRepository {
    /*final List<Account> accounts = new ArrayList<>();

    final FileAccount fileAccount = new FileAccount(this);
    public AccountRepository() {
        add(new Account(null, 0L, 0L, new ArrayList<>(), new BigDecimal(0)));
    }

    public void add(final Account account) {
        final Account finalAccount = new Account();
        finalAccount.setId(UUID.randomUUID());
        finalAccount.setIdClient(account.getIdClient());//??????????????????????????????
        finalAccount.setIdCards(account.getIdCards());
        finalAccount.setAmount(account.getAmount());
        finalAccount.setIdCurrency(account.getIdCurrency());
        accounts.add(finalAccount);
    }

    public Account findById(final Long id) {
        return accounts.stream().filter(e->e.getIdClient().equals(id)).findFirst().orElseThrow();
    }
    public Account get(final Long id) {
        return findById(id);
    }
    public void update(final Long id,final AccountDTO dto) {
       final Account update = findById(id);
       update.setId(dto.getId());//??????
       update.setIdClient(dto.getIdClient());
       update.setAmount(dto.getAmount());
       update.setIdCurrency(dto.getIdCurrency());
       update.setIdCards(dto.getIdCards());

       fileAccount.write();
    }
    public void delete(final Long id) {
        accounts.removeIf(e->e.getIdClient().equals(id));

        fileAccount.write();
    }
    public List<Account> getAccounts() {
        return accounts;
    }*/
}
