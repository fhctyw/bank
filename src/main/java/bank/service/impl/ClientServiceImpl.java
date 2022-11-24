package bank.service.impl;

import bank.dto.*;
import bank.entity.Client;
import bank.mapper.MapperClient;
import bank.repository.ClientRepository;
import bank.service.AccountService;
import bank.service.CardService;
import bank.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    private final MapperClient mapperClient;

    @Autowired
    private final ClientRepository clientRepository;

    @Autowired
    private final CardService cardService;

    @Autowired
    private final AccountService accountService;

    public ClientServiceImpl() {
        mapperClient = new MapperClient();
        clientRepository = new ClientRepository();
        cardService = new CardServiceImpl();
        accountService = new AccountServiceImpl();
    }

    @Override
    public ClientDTO create(final ClientDTO dto) {
        clientRepository.add(mapperClient.toEntity(dto));
        final Client clientInRepos = clientRepository.findById(clientRepository.getId());
        return mapperClient.toDto(clientInRepos);
    }

    @Override
    public ClientDTO read(final Long id) {
        return mapperClient.toDto(clientRepository.findById(id));
    }

    @Override
    public ClientDTO update(final ClientDTO dto) {
        clientRepository.setClient(dto.getId(), mapperClient.toEntity(dto));
        return mapperClient.toDto(clientRepository.findById(dto.getId()));
    }

    @Override
    public ClientDTO delete(final Long id) {
        final Client client = clientRepository.findById(id);
        clientRepository.deleteClient(id);
        return mapperClient.toDto(client);
    }

    @Override
    public List<ClientDTO> getAll() {
        return clientRepository.getClients().stream().map(mapperClient::toDto).collect(Collectors.toList());
    }

    @Override
    public RegisterResponseDTO register(final RegisterDTO registerDTO) {

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(null);
        clientDTO.setFullName(registerDTO.getFullName());
        clientDTO.setEmail(registerDTO.getEmail());
        clientDTO.setPhoneNumber(registerDTO.getPhoneNumber());

        clientDTO = create(clientDTO);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(null);
        accountDTO.setIdClient(clientDTO.getId());
        accountDTO.setAmount(BigDecimal.ZERO);
        accountDTO.setCodeCurrency(registerDTO.getCodeCurrency());

        accountDTO = accountService.create(accountDTO);

        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(null);
        cardDTO.setAmount(BigDecimal.ZERO);
        cardDTO.setIdClient(clientDTO.getId());
        cardDTO.setIdAccount(accountDTO.getId());
        cardDTO.setCardNumber(new Random().nextLong(1000000000000000L, 9999999999999999L));

        cardDTO = cardService.create(cardDTO);

        return new RegisterResponseDTO(clientDTO, accountDTO, cardDTO);
    }
}
