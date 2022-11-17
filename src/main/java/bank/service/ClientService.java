package bank.service;

import bank.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    void create(ClientDTO dto);
    ClientDTO read(Long id);
    void update(ClientDTO dto);
    void delete(Long id);
    List<ClientDTO> getAll();
}
