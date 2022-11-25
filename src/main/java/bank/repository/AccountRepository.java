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
import java.util.Objects;
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
            Files.writeString(file, Objects.requireNonNull(JacksonUtil.serialize(accounts)), StandardCharsets.UTF_16);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void add(final Account account) {
        final Account finalAccount = new Account();
        finalAccount.setId(UUID.randomUUID());
        finalAccount.setIdClient(account.getIdClient());
        finalAccount.setAmount(account.getAmount());
        finalAccount.setCodeCurrency(account.getCodeCurrency());
        accounts.add(finalAccount);

    }

    public Account findById(final UUID id) {
        return accounts.stream().filter(e -> e.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ServiceException("No such id when finding"));
    }

    public Account findByClientId(final Long idClient) {
        return accounts
                .stream()
                .filter(e -> e.getIdClient().equals(idClient))
                .findFirst()
                .orElseThrow(() -> new ServiceException("No Account was found with this Client id:"+idClient));
    }
    public Account findByCurrencyCode(final String currencyCode) {
        return accounts
                .stream()
                .filter(e -> e.getCodeCurrency().equals(currencyCode))
                .findFirst()
                .orElseThrow(() -> new ServiceException("No Account was found with this currency code:"+currencyCode));
    }

    //    public Account get(final Long id) {
//        return findById(id);
//    }
    public void update(final UUID id, final AccountDTO dto) {
        final Account update = findById(id);
        update.setId(dto.getId());
        update.setIdClient(dto.getIdClient());
        update.setAmount(dto.getAmount());
        update.setCodeCurrency(dto.getCodeCurrency());
    }

    public void delete(final UUID id) {
        setAccounts(accounts.stream().filter(e -> !e.getId().equals(id)).collect(Collectors.toList()));
    }

    public void deleteUUID(final Long id) {
        setAccounts(accounts.stream().filter(e -> !e.getId().equals(id)).collect(Collectors.toList()));
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
