package bank.repository;

import bank.entity.Client;
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
public class ClientRepository {
    private final String source = "clients.txt";
    private List<Client> clients = new ArrayList<>();
    private Long id = 0L;

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(final List<Client> clients) {
        this.clients = clients;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @PostConstruct
    public void postConstructor() {
        final Path file = Paths.get(source);
        try {
            clients = JacksonUtil.deserialize(Files.readString(file, StandardCharsets.UTF_16), new TypeReference<>() {
            });

            if (clients == null) {
                clients = new ArrayList<>();
                return;
            }

            final long maxId = clients.stream().mapToLong(Client::getId).max().orElse(1);

            id = maxId + 1;

        } catch (final IOException e) {
            System.out.println("file " + source + " doesn't exist");
        }
    }

    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get(source);

        try {
            Files.writeString(file, Objects.requireNonNull(JacksonUtil.serialize(clients)), StandardCharsets.UTF_16);
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
                .orElseThrow(() -> new ServiceException("No such Client id when finding"));
    }

    public void setClient(final Long id, final Client client) {
        final Client c = findById(id);
        c.setId(client.getId());
        c.setFullName(client.getFullName());
        c.setPhoneNumber(client.getPhoneNumber());
        c.setEmail(client.getEmail());
    }

    public void deleteClient(final Long id) {
        setClients(clients.stream().filter(e -> !e.getId().equals(id)).collect(Collectors.toList()));
    }
}
