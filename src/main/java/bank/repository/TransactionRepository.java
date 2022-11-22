package bank.repository;


import bank.dto.TransactionDTO;
import bank.entity.Transaction;
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
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {
    private final String source = "transactions.txt";
    private List<Transaction> transactions = new ArrayList<>();
    private Long id = 0L;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(final List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Long getId() {
        return id;
    }

    @PostConstruct
    public void postConstructor() {
        final Path file = Paths.get(source);
        try {
            transactions = JacksonUtil.deserialize(Files.readString(file, StandardCharsets.UTF_16), new TypeReference<>() {
            });

            if (transactions == null) {
                transactions = new ArrayList<>();
                return;
            }

            final long maxId = transactions.stream().mapToLong(Transaction::getId).max().orElse(1);

            id = maxId + 1;

        } catch (final IOException e) {
            System.out.println("file " + source + " doesn't exist");
        }
    }

    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get(source);

        try {
            Files.writeString(file, Objects.requireNonNull(JacksonUtil.serialize(transactions)), StandardCharsets.UTF_16);

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void add(final Transaction transaction) {
        final Transaction finalTransaction = new Transaction();
        finalTransaction.setId(++id);
        finalTransaction.setTime(transaction.getTime());
        finalTransaction.setAmount(transaction.getAmount());
        finalTransaction.setIdSender(transaction.getIdSender());
        finalTransaction.setIdReceiver(transaction.getIdReceiver());
        finalTransaction.setMessage(transaction.getMessage());
        transactions.add(finalTransaction);
    }

    public void update(final Long id, final TransactionDTO dto) {
        final Transaction update = findById(id);
        update.setId(dto.getId());
        update.setTime(dto.getTime());
        update.setAmount(dto.getAmount());
        update.setIdSender(dto.getIdSender());
        update.setIdReceiver(dto.getIdReceiver());
        update.setMessage(dto.getMessage());
    }
    public Transaction get(final Long id) {
        return findById(id);
    }

    public Transaction findById(final Long id) {
        return transactions.stream().filter(e -> e.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ServiceException("No such id when finding"));
    }
    public void set(final Long id, final Transaction transaction) {
        final Transaction newTrans = findById(id);
        newTrans.setIdSender(transaction.getIdSender());
        newTrans.setIdReceiver(transaction.getIdReceiver());
        newTrans.setAmount(transaction.getAmount());
        newTrans.setTime(transaction.getTime());
        newTrans.setMessage(transaction.getMessage());
    }


    public void delete(final Long id) {
        setTransactions(transactions.stream().filter(e -> !e.getId().equals(id)).collect(Collectors.toList()));
    }
}
