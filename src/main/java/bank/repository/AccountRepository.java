package bank.repository;

import bank.entity.Account;
import bank.entity.Consultant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountRepository {
    final List<Account> accounts = new ArrayList<>();
    int id = 0;

    public AccountRepository() {
        add(new Account(0, new ArrayList<>(), 0, "Adam Whitman", "12345"));
        add(new Account(0, new ArrayList<>(), 0, "Roman Blue", "5555"));
    }

    public void add(final Account account) {
        final Account finalAccount = new Account();
        finalAccount.setId(id++);
        finalAccount.setEmail(account.getEmail());
        finalAccount.setIdCard(account.getIdCard());// new ArrayList<>();
        finalAccount.setAmount(account.getAmount());
        finalAccount.setPassword(account.getPassword());
        accounts.add(finalAccount);
    }

    public Account read(final int id) {
        for (final Account account : accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }
}
