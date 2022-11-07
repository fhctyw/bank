package bank.repository;

import bank.entity.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientRepository {
    final  List<Client> clients = new ArrayList<>();
    int id = 0;

    public ClientRepository() {
        add(new Client("Roman Khrapchun", "hdgdfghdfgh@gmail.com", 0, 123456789));
        add(new Client("Oleksandr Hamayunov","iohgfjmsdfhpiog@gmail.com", 0, 987654321));
    }

    public void add(final Client client) {
        final Client finalClient = new Client();
        finalClient.setId(id++);
        finalClient.setFullName(client.getFullName());
        clients.add(finalClient);
    }
}
