package bank.repository;


import bank.dto.TransactionDTO;
import bank.entity.Transaction;
import bank.util.JacksonUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {
    private final String source = "transactions.txt";
   private List<Transaction> transactions = new ArrayList<>();
    private Long id;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime ignoreUntil;
   @PostConstruct
   public void postConstructor() {
        final Path file = Paths.get(source);
        try {
            transactions = JacksonUtil.deserialize(Files.readString(file, StandardCharsets.UTF_16), new TypeReference<List<Transaction>>() {
            });

            if (transactions == null) {
                transactions = new ArrayList<>();
                return;
            }

            final long maxId = transactions.stream().mapToLong(Transaction::getId).max().orElse(1);

            id = maxId + 1;

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get(source);

        try {
            Files.writeString(file, JacksonUtil.serialize(transactions), StandardCharsets.UTF_16);

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void add(final Transaction transaction) {
        final Transaction finalTransaction = new Transaction();
        finalTransaction.setId(++id);
        finalTransaction.setTime(LocalDateTime.now());
        finalTransaction.setAmount_of_transaction(transaction.getAmount_of_transaction());
        finalTransaction.setIdSender(transaction.getIdSender());
        finalTransaction.setIdReceiver(transaction.getIdReceiver());
        finalTransaction.setMessage(transaction.getMessage());
        transactions.add(finalTransaction);

    }

    public void update(final Long id, final TransactionDTO dto) {
        final Transaction update = findById(id);
        update.setId(dto.getId());
        update.setTime(dto.getTime());
        update.setAmount_of_transaction(dto.getAmount_of_transaction());
        update.setIdSender(dto.getIdSender());
        update.setIdReceiver(dto.getIdReceiver());
        update.setMessage(dto.getMessage());


    }
    public Transaction get(final Long id) {
        return findById(id);
    }

    public Transaction findById(final Long id) {
        return transactions.stream().filter(e -> e.getId().equals(id)).findFirst().orElseThrow();
    }
    public void setTransactions(final Long id, final Transaction transaction) {
        final Transaction newTrans = findById(id);
        newTrans.setIdSender(transaction.getIdSender());
        newTrans.setIdReceiver(transaction.getIdReceiver());
        newTrans.setAmount_of_transaction(transaction.getAmount_of_transaction());
        newTrans.setTime(transaction.getTime());
        newTrans.setMessage(transaction.getMessage());
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void delete(final Long id) {
        transactions.removeIf(e -> e.getId().equals(id));
    }
}
