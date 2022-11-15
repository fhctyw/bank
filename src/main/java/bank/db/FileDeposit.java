package bank.db;

import bank.entity.Deposit;
import bank.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class FileDeposit implements BankDB{
    final private String name = "deposit.txt";
    @Autowired
    final private DepositRepository depositRepository;

    public FileDeposit(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @Override
    public void write() {
        try {
            final BufferedWriter writer = new BufferedWriter(new FileWriter(name));

            for (final Deposit client : depositRepository.getDeposits()) {
                writer.write(client.toString() + "\n");
            }

            writer.close();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Deposit convert(final String stringClient) {
        final String[] fields = stringClient.split(" ");
        final String client = fields[0];
        final BigDecimal amount = new BigDecimal(fields[1]);
        final Long depositId = Long.parseLong(fields[2]);
        final Long cardId = Long.parseLong(fields[3]);
        final Long consultantId = Long.parseLong(fields[4]);
        final LocalDate putTime = LocalDate.parse(fields[5]);
        final LocalDate withdrawTime = LocalDate.parse(fields[6]);
        final double percentage = Double.parseDouble(fields[7]);
        depositRepository.setId(depositId);
        return new Deposit(client, amount, depositId, cardId, consultantId, putTime, withdrawTime, percentage);
    }

    @Override
    public void read() {
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(name));

            String line;
            while ((line = reader.readLine()) != null) {
                depositRepository.getDeposits().add(convert(line));
            }

            reader.close();

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
