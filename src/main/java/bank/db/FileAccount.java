package bank.db;

import bank.entity.Account;
import bank.entity.Consultant;
import bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileAccount implements BankDB {
    final private String name = "accounts.txt";

    @Autowired
    final private AccountRepository accountRepository;

    public FileAccount(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void write() {
        try {
            final BufferedWriter writer = new BufferedWriter(new FileWriter(name));

            for (final Account account : accountRepository.getAccounts()) {
                writer.write(account.toString() + "\n");
            }

            writer.close();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Account convert(final String stringAccount) {
        final String[] fields = stringAccount.split(" ");
        final UUID id = UUID.fromString(fields[0]);
        final Long idClient = Long.parseLong(fields[1]);
        final Long idCurrency = Long.parseLong(fields[2]);
        final List<Long> idCards = Stream.of(Long.parseLong(fields[3])).toList();
        final BigDecimal amount = new BigDecimal(Long.parseLong(fields[4]));

        return new Account(id, idClient, idCurrency, idCards, amount);
    }

    @Override
    public void read() {
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(name));

            String line;
            while ((line = reader.readLine()) != null) {
                accountRepository.getAccounts().add(convert(line));
            }

            reader.close();

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
