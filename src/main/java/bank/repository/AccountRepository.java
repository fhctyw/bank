package bank.repository;

import bank.dto.AccountDTO;
import bank.entity.Account;
import bank.exception.ServiceException;
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
import java.util.stream.Collectors;

@Repository
public class AccountRepository {
    private final String source = "accounts.txt";
     List<Account> accounts = new ArrayList<>();

    public void setAccounts(final List<Account> accounts) {
        this.accounts = accounts;
    }

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
            System.out.println("file " + source + " doesn't exist");
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
        finalAccount.setIdClient(account.getIdClient());
        finalAccount.setAmount(account.getAmount());
        finalAccount.setIdCurrency(account.getIdCurrency());
        accounts.add(finalAccount);

    }

    public Account findById(final Long id) {
        return accounts.stream().filter(e->e.getIdClient().equals(id)).findFirst()
                .orElseThrow(() -> new ServiceException("No such id when finding"));
    }
    public Account get(final Long id) {
        return findById(id);
    }
    public void update(final Long id,final AccountDTO dto) {
       final Account update = findById(id);
       update.setId(dto.getId());
       update.setIdClient(dto.getIdClient());
       update.setAmount(dto.getAmount());
       update.setIdCurrency(dto.getIdCurrency());
    }
    public void deleteByClientId(final Long id) {
        setAccounts(accounts.stream().filter(e -> !e.getIdClient().equals(id)).collect(Collectors.toList()));
    }
    public void deleteUUID(final Long id) {
        setAccounts(accounts.stream().filter(e -> !e.getId().equals(id)).collect(Collectors.toList()));
    }
    public List<Account> getAccounts() {
        return accounts;
    }
}
