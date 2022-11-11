package bank.db;
import bank.entity.Transaction;
import bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FileTransaction implements BankDB {
    final private String name = "transactions.txt";

    @Autowired
    final private TransactionRepository transactionRepository;

    public FileTransaction(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void write() {
        try {
            final BufferedWriter writer = new BufferedWriter(new FileWriter(name));

            for (final Transaction transaction : transactionRepository.getTransactions()) {
                writer.write(transaction.toString() + "\n");
            }

            writer.close();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Transaction convert(final String stringAccount) {
        final String[] fields = stringAccount.split(" ");
        final Long id = Long.parseLong(fields[0]);
        final LocalDateTime time = LocalDateTime.parse(fields[1]);
        final BigDecimal amount_of_transaction = new BigDecimal(fields[2]);
        final Long idReceiver = Long.parseLong(fields[3]);
        final Long idSender = Long.parseLong(fields[4]);
        final String message = fields[5];

        return new Transaction(id, time, amount_of_transaction, idReceiver, idSender, message);
    }


    @Override
    public void read() {
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(name));

            String line;
            while ((line = reader.readLine()) != null) {
                transactionRepository.getTransactions().add(convert(line));
            }

            reader.close();

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
