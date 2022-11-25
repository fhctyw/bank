package bank.service.impl;

import bank.dto.AccountDTO;
import bank.dto.CardDTO;
import bank.dto.MakeCardDTO;
import bank.dto.MakeCardResponseDTO;
import bank.entity.Account;
import bank.entity.Card;
import bank.entity.Client;
import bank.exception.ServiceException;
import bank.mapper.MapperCard;
import bank.mapper.MapperClient;
import bank.repository.CardRepository;
import bank.repository.ClientRepository;
import bank.service.AccountService;
import bank.service.CardService;
import bank.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    @Autowired
    private final MapperCard mapperCard;
    @Autowired
    private final CardRepository cardRepository ;
    @Autowired
    private  final AccountService accountService;
    @Autowired
    private  final ClientRepository clientRepository ;

    @Override
    public CardDTO create(final CardDTO dto) {
        Card card = mapperCard.toEntity(dto);
        cardRepository.add(card);
        card = cardRepository.findById(cardRepository.getId());
        return mapperCard.toDto(card);
    }
    public CardServiceImpl() {
        mapperCard = new MapperCard();
        cardRepository = new CardRepository();
        accountService = new AccountServiceImpl();
        clientRepository = new ClientRepository();
    }

    @Override
    public MakeCardResponseDTO createCard(MakeCardDTO dto) {// client  codeCurrency CardDTO
        AccountDTO accountDTO = new AccountDTO();
        clientRepository.findById(dto.getIdClient());

        if(accountService.getAll().stream().anyMatch(e -> e.getIdClient().equals(dto.getIdClient()))) {
            if( accountService.getAll().stream().anyMatch(e -> e.getCodeCurrency().equals(dto.getCodeCurrency()))) {
         accountDTO = accountService.getAll().stream().filter(e -> e.getCodeCurrency().equals(dto.getCodeCurrency())).findFirst().get();
            }}

       else {
            accountDTO.setId(null);
            accountDTO.setIdClient(dto.getIdClient());
            accountDTO.setAmount(BigDecimal.ZERO);
            accountDTO.setCodeCurrency(dto.getCodeCurrency());

            accountDTO = accountService.create(accountDTO);
        }

        CardDTO cardDTO = new CardDTO();
        cardDTO.setCardNumber(new Random().nextLong(0, 9999999999999999L));
        cardDTO.setIdAccount(accountDTO.getId());
        cardDTO.setAmount(BigDecimal.ZERO);
        cardDTO.setId(null);
        cardDTO.setIdClient(dto.getIdClient());

        cardDTO = create(cardDTO);

        return new MakeCardResponseDTO(accountDTO, cardDTO);
    }

    @Override
    public CardDTO read(final Long id) {
        return mapperCard.toDto(cardRepository.findById(id));
    }


    @Override
    public CardDTO update(final CardDTO dto) {
        cardRepository.setCard(dto.getId(), mapperCard.toEntity(dto));
        return mapperCard.toDto(cardRepository.findById(dto.getId()));
    }

    @Override
    public CardDTO delete(final Long id) {
        final Card card = cardRepository.findById(id);
        cardRepository.deleteCard(id);
        return mapperCard.toDto(card);
    }

    @Override
    public List<CardDTO> getAll() {
        return cardRepository.getCards().stream().map(mapperCard::toDto).collect(Collectors.toList());
    }

    @Override
    public CardDTO getByNumber(final Long number) {
        return mapperCard.toDto(cardRepository.findByNumber(number));
    }


}
