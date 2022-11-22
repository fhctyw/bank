package bank.mapper;

import bank.dto.ClientDTO;
import bank.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class MapperClient {
    public ClientDTO toDto(final Client client){
        final ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setFullName(client.getFullName());
        dto.setEmail(client.getEmail());
        dto.setPhoneNumber(client.getPhoneNumber());
        return dto;
    }

    public Client toEntity(final ClientDTO dto){
        final Client client = new Client();
        client.setId(dto.getId());
        client.setFullName(dto.getFullName());
        client.setEmail(dto.getEmail());
        client.setPhoneNumber(dto.getPhoneNumber());
        return client;
    }
}
