package bank.repository;

import bank.db.FileTransaction;
import bank.dto.TransactionDTO;
import bank.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {
    final List<Transaction> transactions = new ArrayList<>();

    final FileTransaction fileTransaction = new FileTransaction(this);

    public TransactionRepository() {
        fileTransaction.read();
        //add(new Transaction(null, LocalDateTime.now(), new BigDecimal(0), 0L, 0L, ""));
    }

    public void add(final Transaction transaction) {
        final Transaction finalTransaction = new Transaction();
        finalTransaction.setId(transaction.getId());
        finalTransaction.setTime(transaction.getTime());
        finalTransaction.setAmount_of_transaction(transaction.getAmount_of_transaction());
        finalTransaction.setIdSender(transaction.getIdSender());
        finalTransaction.setIdReceiver(transaction.getIdReceiver());
        finalTransaction.setMessage(transaction.getMessage());
        transactions.add(finalTransaction);

        fileTransaction.write();
    }

    public void update(final Long id, final TransactionDTO dto) {
        final Transaction update = findById(id);
        update.setId(dto.getId());
        update.setTime(dto.getTime());
        update.setAmount_of_transaction(dto.getAmount_of_transaction());
        update.setIdSender(dto.getIdSender());
        update.setIdReceiver(dto.getIdReceiver());
        update.setMessage(dto.getMessage());

        fileTransaction.write();

    }

    public Transaction get(final Long id) {
        return findById(id);
    }

    public Transaction findById(final Long id) {
        return transactions.stream().filter(e -> e.getId().equals(id)).findFirst().orElseThrow();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void delete(final Long id) {
        transactions.removeIf(e -> e.getId().equals(id));

        fileTransaction.write();
    }

}
