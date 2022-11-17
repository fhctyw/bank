package bank.repository;

import bank.entity.Client;
import bank.entity.Consultant;
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

@Repository
public class ClientRepository {
    private final String source = "clients.txt";
    private List<Client> clients = new ArrayList<>();
    private Long id;

    public List<Client> getClients() {
        return clients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void postConstructor() {
        final Path file = Paths.get(source);
        try {
            clients = JacksonUtil.deserialize(Files.readString(file, StandardCharsets.UTF_16), new TypeReference<List<Client>>() {
            });

            if (clients == null) {
                clients = new ArrayList<>();
                return;
            }

            final long maxId = clients.stream().mapToLong(Client::getId).max().orElse(1);

            id = maxId;

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get(source);

        try {
            Files.writeString(file, JacksonUtil.serialize(clients), StandardCharsets.UTF_16);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void add(final Client client) {
        final Client finalClient = new Client();
        finalClient.setId(++id);
        finalClient.setFullName(client.getFullName());
        finalClient.setEmail(client.getEmail());
        finalClient.setPhoneNumber(client.getPhoneNumber());
        clients.add(finalClient);
    }

    public Client findById(final Long id) {
        return clients.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public void setClient(final Long id, final Client client) {
        final Client c = findById(id);
        c.setId(client.getId());
        c.setFullName(client.getFullName());
        c.setPhoneNumber(client.getPhoneNumber());
        c.setEmail(client.getEmail());
    }

    public void deleteClient(final Long id) {
        clients.removeIf(e -> e.getId().equals(id));
    }
}
