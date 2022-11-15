package bank.repository;

import bank.dto.AccountDTO;
import bank.entity.Account;
import bank.util.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class AccountRepository {
    private final String source = "accounts.txt";
     List<Account> accounts = new ArrayList<>();


    @PostConstruct
    public void postConstructor() {
        final Path file = Paths.get(source);
        try {
            accounts = JacksonUtil.deserialize(Files.readString(file, StandardCharsets.UTF_16), new TypeReference<List<Account>>() {
            });

            if (accounts == null) {
                accounts = new ArrayList<>();
                return;
            }

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get(source);

        try {
            Files.writeString(file, JacksonUtil.serialize(accounts), StandardCharsets.UTF_16);
        } catch (final IOException e) {
            e.printStackTrace();
        }
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

    }
    public void deleteByClientId(final Long id) {
        accounts.removeIf(e->e.getIdClient().equals(id));
    }
    public void deleteUUID(final Long id) {
        accounts.removeIf(e->e.getId().equals(id));
    }
    public List<Account> getAccounts() {
        return accounts;
    }
}
