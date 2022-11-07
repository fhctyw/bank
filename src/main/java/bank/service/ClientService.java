package bank.service;

import bank.dto.ClientDTO;
import bank.entity.Client;

public interface ClientService {
    void create(ClientDTO dto);
    Client read(int id);
    void update(ClientDTO dto);
    void delete(int id);
}
