package bank.db;

import bank.entity.Consultant;
import bank.repository.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

public class FileConsultant implements BankDB {
    final private String name = "consultant.txt";
    @Autowired
    final private ConsultantRepository consultantRepository;

    public FileConsultant(final ConsultantRepository consultantRepository) {
        this.consultantRepository = consultantRepository;
    }

    @Override
    public void write() {
        try {
            final BufferedWriter writer = new BufferedWriter(new FileWriter(name));

            for (final Consultant consultant : consultantRepository.getConsultants()) {
                writer.write(consultant.toString() + "\n");
            }

            writer.close();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Consultant convert(final String stringConsultant) {
        final String[] fields = stringConsultant.split(" ");
        final Long id =  Long.parseLong(fields[0]);
        consultantRepository.setId(id);
        return new Consultant(id, fields[1]);
    }

    @Override
    public void read() {
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(name));

            String line;
            while ((line = reader.readLine()) != null) {
                consultantRepository.getConsultants().add(convert(line));
           }

            reader.close();

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
