package bank.service.impl;

import bank.Bank;
import bank.dto.*;
import bank.entity.Account;
import bank.entity.Card;
import bank.entity.Client;
import bank.mapper.MapperCard;
import bank.repository.CardRepository;
import bank.repository.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Bank.class)
@AutoConfigureMockMvc
public class CardServiceImplTest {
    @Test
    public void test_makeCard() throws Exception {
        final MapperCard mapperCard = Mockito.mock(MapperCard.class);
        final ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
        final CardRepository cardRepository = Mockito.mock(CardRepository.class);
        final AccountServiceImpl accountService = Mockito.mock(AccountServiceImpl.class);

        final CardServiceImpl cardService = new CardServiceImpl(mapperCard,cardRepository,accountService,clientRepository);

        final Long idClient = 3L;
        final String codeCurrency = "ADA";

        final UUID  uuid = UUID.fromString("26bb29ea-8284-487d-bca6-44c64b98e80b");
        final BigDecimal amount = BigDecimal.valueOf(100);

        final String fullName = "Markus Eduard";
        final String phoneNumber = "0953221430";
        final String email = "markedu211@gmail.com";

        final Client client = new Client();
        client.setId(idClient);
        client.setFullName(fullName);
        client.setEmail(email);
        client.setPhoneNumber(phoneNumber);

        final Account account = new Account();
        account.setId(uuid);
        account.setIdClient(idClient);
        account.setCodeCurrency(codeCurrency);
        account.setAmount(amount);

        final AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(uuid);
        accountDTO.setIdClient(idClient);
        accountDTO.setCodeCurrency(codeCurrency);
        accountDTO.setAmount(amount);

        final Long idCard = 1L;
        final Long cardNumber = 8282973914177809L;

        final Card card = new Card();
        card.setId(idCard);
        card.setIdClient(idClient);
        card.setAmount(amount);
        card.setIdAccount(uuid);
        card.setCardNumber(cardNumber);

        final CardDTO cardDTO = new CardDTO();
        cardDTO.setId(idCard);
        cardDTO.setIdClient(idClient);
        cardDTO.setAmount(amount);
        cardDTO.setIdAccount(uuid);
        cardDTO.setCardNumber(cardNumber);

        final MakeCardDTO makeCardDTO = new MakeCardDTO();
        makeCardDTO.setCodeCurrency(codeCurrency);
        makeCardDTO.setIdClient(idClient);

        when(mapperCard.toEntity(any())).thenCallRealMethod();
        when(mapperCard.toDto(any())).thenCallRealMethod();
        when(accountService.create(any())).thenReturn(accountDTO);
        when(cardRepository.findById(any())).thenReturn(card);

        final MakeCardResponseDTO makeCardResponseDTO = cardService.createCard(makeCardDTO);

        assertEquals(makeCardResponseDTO.getCard().getIdClient(), idClient);
        assertEquals(makeCardResponseDTO.getCard().getAmount(), amount);
        assertEquals(makeCardResponseDTO.getCard().getCardNumber(), cardNumber);
        assertEquals(makeCardResponseDTO.getCard().getIdAccount(), uuid);
        assertEquals(makeCardResponseDTO.getCard().getId(), idCard);

        assertEquals(makeCardResponseDTO.getAccount().getIdClient(), idClient);
        assertEquals(makeCardResponseDTO.getAccount().getAmount(), amount);
        assertEquals(makeCardResponseDTO.getAccount().getCodeCurrency(), codeCurrency);
        assertEquals(makeCardResponseDTO.getAccount().getId(), uuid);

    }
}
