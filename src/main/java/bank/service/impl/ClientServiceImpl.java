package bank.service.impl;

import bank.dto.ClientDTO;
import bank.entity.Client;
import bank.mapper.MapperClient;
import bank.repository.ClientRepository;
import bank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    final MapperClient mapperClient = new MapperClient();

    @Autowired
    final ClientRepository clientRepository = new ClientRepository();

    @Override
    public void create(ClientDTO dto) {
        final Client client = mapperClient.toEntity(dto);
        clientRepository.add(client);
    }

    @Override
    public Client read(int id) {
        return null;
    }

    @Override
    public void update(ClientDTO dto) {

    }

    @Override
    public void delete(int id) {

    }
}
