package bank.db;

import bank.entity.Client;
import bank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

public class FileClient implements BankDB {
    final private String name = "client.txt";

    @Autowired
    final private ClientRepository clientRepository;

    public FileClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void write() {
        try {
            final BufferedWriter writer = new BufferedWriter(new FileWriter(name));

            for (final Client client : clientRepository.getClients()) {
                writer.write(client.toString() + "\n");
            }

            writer.close();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Client convert(final String stringClient) {
        final String[] fields = stringClient.split(" ");
        final String fullName = fields[0];
        final String email = fields[1];
        final Long id =  Long.parseLong(fields[2]);
        final String phoneNumber = fields[3];
        clientRepository.setId(id);
        return new Client(fullName, email, id, phoneNumber);
    }

    @Override
    public void read() {
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(name));

            String line;
            while ((line = reader.readLine()) != null) {
                clientRepository.getClients().add(convert(line));
            }

            reader.close();

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
