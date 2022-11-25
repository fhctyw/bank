package bank.service.impl;

import bank.Bank;
import bank.dto.*;
import bank.entity.Account;
import bank.entity.Card;
import bank.entity.Client;
import bank.mapper.MapperClient;
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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Bank.class)
@AutoConfigureMockMvc
public class ClientServiceImplTest {
    @Test
    public void test_register() throws Exception {
        final MapperClient mapperClient = Mockito.mock(MapperClient.class);
        final ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
        final CardServiceImpl cardService = Mockito.mock(CardServiceImpl.class);
        final AccountServiceImpl accountService = Mockito.mock(AccountServiceImpl.class);

        final ClientServiceImpl clientService = new ClientServiceImpl(mapperClient, clientRepository, cardService, accountService);

        final String fullName = "Markus Eduard";
        final String phoneNumber = "0953221430";
        final String email = "markedu211@gmail.com";
        final Long id = 2L;


        final Client client = new Client();
        client.setId(id);
        client.setFullName(fullName);
        client.setEmail(email);
        client.setPhoneNumber(phoneNumber);

        final ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFullName(fullName);
        clientDTO.setPhoneNumber(phoneNumber);
        clientDTO.setEmail(email);
        clientDTO.setId(id);

        final UUID uuid = UUID.fromString("26bb29ea-8284-487d-bca6-44c64b98e80b");
        final BigDecimal amount = BigDecimal.valueOf(2000);
        final String codeCurrency = "EUR";

        final Account account = new Account();
        account.setId(uuid);
        account.setIdClient(id);
        account.setCodeCurrency(codeCurrency);
        account.setAmount(amount);

        final AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(uuid);
        accountDTO.setIdClient(id);
        accountDTO.setCodeCurrency(codeCurrency);
        accountDTO.setAmount(amount);


        final Long idCard = 1L;
        final Long cardNumber = 8282973914177809L;

        final Card card = new Card();
        card.setId(idCard);
        card.setIdClient(id);
        card.setAmount(amount);
        card.setIdAccount(uuid);
        card.setCardNumber(cardNumber);

        final CardDTO cardDTO = new CardDTO();
        cardDTO.setId(idCard);
        cardDTO.setIdClient(id);
        cardDTO.setAmount(amount);
        cardDTO.setIdAccount(uuid);
        cardDTO.setCardNumber(cardNumber);

        final RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setCodeCurrency(codeCurrency);
        registerDTO.setEmail(email);
        registerDTO.setFullName(fullName);
        registerDTO.setPhoneNumber(phoneNumber);

        when(mapperClient.toEntity(any())).thenCallRealMethod();
        when(mapperClient.toDto(any())).thenCallRealMethod();
        when(clientRepository.findById(any())).thenReturn(client);
        when(accountService.create(any())).thenReturn(accountDTO);
        when(cardService.create(any())).thenReturn(cardDTO);

        final RegisterResponseDTO registerResponseDTO = clientService.register(registerDTO);

        assertEquals(registerResponseDTO.getClient().getId(), id);
        assertEquals(registerResponseDTO.getClient().getEmail(), email);
        assertEquals(registerResponseDTO.getClient().getFullName(), fullName);
        assertEquals(registerResponseDTO.getClient().getPhoneNumber(), phoneNumber);

        assertEquals(registerResponseDTO.getCard().getIdClient(), id);
        assertEquals(registerResponseDTO.getCard().getAmount(), amount);
        assertEquals(registerResponseDTO.getCard().getCardNumber(), cardNumber);
        assertEquals(registerResponseDTO.getCard().getIdAccount(), uuid);
        assertEquals(registerResponseDTO.getCard().getId(), idCard);

        assertEquals(registerResponseDTO.getAccount().getIdClient(), id);
        assertEquals(registerResponseDTO.getAccount().getAmount(), amount);
        assertEquals(registerResponseDTO.getAccount().getCodeCurrency(), codeCurrency);
        assertEquals(registerResponseDTO.getAccount().getId(), uuid);
    }

}
