package bank.repository;

import bank.entity.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepository {
    final  List<Client> clients = new ArrayList<>();
    private Long id = 0L;

    //final FileClient fileClient = new FileClient(this);

    public ClientRepository() {
        //fileClient.read();
    }
    public List<Client> getClients() {
        return clients;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void add(final Client client) {
        final Client finalClient = new Client();
        finalClient.setId(++id);
        finalClient.setFullName(client.getFullName());
        finalClient.setEmail(client.getEmail());
        finalClient.setPhoneNumber(client.getPhoneNumber());
        clients.add(finalClient);

        //fileClient.write();
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

        //fileClient.write();
    }

    public void deleteClient(final Long id) {
        clients.removeIf(e->e.getId().equals(id));

        //fileClient.write();
    }
}
