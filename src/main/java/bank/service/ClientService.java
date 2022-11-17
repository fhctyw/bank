package bank.service;

import bank.dto.ClientDTO;

public interface ClientService {
    void create(ClientDTO dto);
    ClientDTO read(Long id);
    void update(ClientDTO dto);
    void delete(Long id);
}
