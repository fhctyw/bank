package bank.service;

import bank.dto.ClientDTO;
import bank.dto.RegisterDTO;
import bank.dto.RegisterResponseDTO;

import java.util.List;

public interface ClientService {
    ClientDTO create(ClientDTO dto);

    ClientDTO read(Long id);

    ClientDTO update(ClientDTO dto);

    ClientDTO delete(Long id);

    List<ClientDTO> getAll();

    RegisterResponseDTO register(RegisterDTO registerDTO);
}
