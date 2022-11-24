package bank.service.impl;

import bank.Bank;
import bank.dto.*;
import bank.entity.*;
import bank.exception.TransferNotEnoughMoneyException;
import bank.exception.TransferSelfTransactionException;
import bank.mapper.MapperTransaction;
import bank.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Bank.class)
@AutoConfigureMockMvc
public class TransactionServiceImplTest {
    @Test
    public void test_create() throws Exception {
        final MapperTransaction mapperTransaction = Mockito.mock(MapperTransaction.class);
        final TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);
        final CardServiceImpl cardService = Mockito.mock(CardServiceImpl.class);
        final AccountServiceImpl accountService = Mockito.mock(AccountServiceImpl.class);
        final CurrencyServiceImpl currencyService = Mockito.mock(CurrencyServiceImpl.class);

        final TransactionServiceImpl transactionService
                = new TransactionServiceImpl(mapperTransaction, transactionRepository, cardService, accountService, currencyService);

        final BigDecimal amount = BigDecimal.valueOf(200);
        final Long idReceiver = 2L;
        final Long idSender = 3L;
        final String message = "some transaction";
        final LocalDateTime time =
                LocalDateTime.of(2022, 11, 25,
                        13, 32, 30, 621100);

        final TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTime(time);
        transactionDTO.setAmount(amount);
        transactionDTO.setMessage(message);
        transactionDTO.setIdReceiver(idReceiver);
        transactionDTO.setIdSender(idSender);

        final Transaction transaction = new Transaction();
        transaction.setId(4L);
        transaction.setAmount(amount);
        transaction.setTime(time);
        transaction.setMessage(message);
        transaction.setIdSender(idSender);
        transaction.setIdReceiver(idReceiver);

        when(mapperTransaction.toEntity(any())).thenCallRealMethod();
        when(mapperTransaction.toDto(any())).thenCallRealMethod();
        when(transactionRepository.findById(any())).thenReturn(transaction);

        final TransactionDTO resultTransaction = transactionService.create(transactionDTO);

        assertEquals(resultTransaction.getMessage(), message);
        assertEquals(resultTransaction.getId(), Long.valueOf(4L));
        assertEquals(resultTransaction.getTime(), time);
        assertEquals(resultTransaction.getAmount(), amount);
        assertEquals(resultTransaction.getIdSender(), idSender);
        assertEquals(resultTransaction.getIdReceiver(), idReceiver);
    }

    @Test
    public void test_transfer_checkNotEnoughMoney() throws Exception {
        final MapperTransaction mapperTransaction = Mockito.mock(MapperTransaction.class);
        final TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);
        final CardServiceImpl cardService = Mockito.mock(CardServiceImpl.class);
        final AccountServiceImpl accountService = Mockito.mock(AccountServiceImpl.class);
        final CurrencyServiceImpl currencyService = Mockito.mock(CurrencyServiceImpl.class);

        final TransactionServiceImpl transactionService
                = new TransactionServiceImpl(mapperTransaction, transactionRepository, cardService, accountService, currencyService);

        // Sender

        // first Client
        final String fullName1 = "Markus Eduard";
        final String phoneNumber1 = "0953221430";
        final String email1 = "markedu211@gmail.com";
        final Long id1 = 2L;


        final Client client1 = new Client();
        client1.setId(id1);
        client1.setFullName(fullName1);
        client1.setEmail(email1);
        client1.setPhoneNumber(phoneNumber1);

        final ClientDTO clientDTO1 = new ClientDTO();
        clientDTO1.setFullName(fullName1);
        clientDTO1.setPhoneNumber(phoneNumber1);
        clientDTO1.setEmail(email1);
        clientDTO1.setId(id1);

        // first Currency
        final String codeCurrency1 = "EUR";
        final BigDecimal value1 = BigDecimal.valueOf(0.976258);

        final Currency currency1 = new Currency();
        currency1.setCode(codeCurrency1);
        currency1.setValue(value1);

        // first Account
        final UUID uuid1 = UUID.fromString("26bb29ea-8284-487d-bca6-44c64b98e80b");
        final BigDecimal amount1 = BigDecimal.valueOf(2000);

        final Account account1 = new Account();
        account1.setId(uuid1);
        account1.setIdClient(id1);
        account1.setCodeCurrency(codeCurrency1);
        account1.setAmount(amount1);

        final AccountDTO accountDTO1 = new AccountDTO();
        accountDTO1.setId(uuid1);
        accountDTO1.setIdClient(id1);
        accountDTO1.setCodeCurrency(codeCurrency1);
        accountDTO1.setAmount(amount1);

        // first Card
        final Long idCard1 = 1L;
        final Long cardNumber1 = 8282973914177809L;

        final Card card1 = new Card();
        card1.setId(idCard1);
        card1.setIdClient(id1);
        card1.setAmount(amount1);
        card1.setIdAccount(uuid1);
        card1.setCardNumber(cardNumber1);

        final CardDTO cardDTO1 = new CardDTO();
        cardDTO1.setId(idCard1);
        cardDTO1.setIdClient(id1);
        cardDTO1.setAmount(amount1);
        cardDTO1.setIdAccount(uuid1);
        cardDTO1.setCardNumber(cardNumber1);


        // Receiver

        // second Client
        final String fullName2 = "Jan Malone";
        final String phoneNumber2 = "0995620255";
        final String email2 = "jamalonePP@gmail.com";
        final Long id2 = 4L;

        final Client client2 = new Client();
        client2.setId(id2);
        client2.setFullName(fullName2);
        client2.setEmail(email2);
        client2.setPhoneNumber(phoneNumber2);

        final ClientDTO clientDTO2 = new ClientDTO();
        clientDTO2.setFullName(fullName2);
        clientDTO2.setPhoneNumber(phoneNumber2);
        clientDTO2.setEmail(email2);
        clientDTO2.setId(id2);

        // second Currency
        final String codeCurrency2 = "UAH";
        final BigDecimal value2 = BigDecimal.valueOf(36.769917);

        final Currency currency2 = new Currency();
        currency2.setCode(codeCurrency2);
        currency2.setValue(value2);

        // second Account
        final UUID uuid2 = UUID.fromString("681637c5-586e-4617-acb9-3aa57a427783");
        final BigDecimal amount2 = BigDecimal.valueOf(150.4);

        final Account account2 = new Account();
        account2.setId(uuid2);
        account2.setIdClient(id2);
        account2.setCodeCurrency(codeCurrency2);
        account2.setAmount(amount2);

        final AccountDTO accountDTO2 = new AccountDTO();
        accountDTO2.setId(uuid2);
        accountDTO2.setIdClient(id2);
        accountDTO2.setCodeCurrency(codeCurrency2);
        accountDTO2.setAmount(amount2);

        // second Card
        final Long idCard2 = 2L;
        final Long cardNumber2 = 3212453198635552L;

        final Card card2 = new Card();
        card1.setId(idCard2);
        card1.setIdClient(id2);
        card1.setAmount(amount2);
        card1.setIdAccount(uuid2);
        card1.setCardNumber(cardNumber2);

        final CardDTO cardDTO2 = new CardDTO();
        cardDTO2.setId(idCard2);
        cardDTO2.setIdClient(id2);
        cardDTO2.setAmount(amount2);
        cardDTO2.setIdAccount(uuid2);
        cardDTO2.setCardNumber(cardNumber2);

        final TransferMoneyDTO transferMoneyDTO = new TransferMoneyDTO();
        transferMoneyDTO.setNumberCardSender(cardNumber1);
        transferMoneyDTO.setNumberCardReceiver(cardNumber2);
        transferMoneyDTO.setAmount(BigDecimal.valueOf(3000));
        transferMoneyDTO.setMessage("Send 150 dollars to 3212453198635552 card");

        when(cardService.getByNumber(cardNumber1)).thenReturn(cardDTO1);
        when(cardService.getByNumber(cardNumber2)).thenReturn(cardDTO2);


        try {
            final TransactionDTO transactionDTO = transactionService.transfer(transferMoneyDTO);

            fail();
        } catch (final TransferNotEnoughMoneyException ignored) {

        } catch (final Exception ex) {
            fail();
        }
    }

    @Test
    public void test_transfer_checkEnoughMoney() throws Exception {
        final MapperTransaction mapperTransaction = Mockito.mock(MapperTransaction.class);
        final TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);
        final CardServiceImpl cardService = Mockito.mock(CardServiceImpl.class);
        final AccountServiceImpl accountService = Mockito.mock(AccountServiceImpl.class);
        final CurrencyServiceImpl currencyService = Mockito.mock(CurrencyServiceImpl.class);

        final TransactionServiceImpl transactionService
                = new TransactionServiceImpl(mapperTransaction, transactionRepository, cardService, accountService, currencyService);

        // Sender

        // first Client
        final String fullName1 = "Markus Eduard";
        final String phoneNumber1 = "0953221430";
        final String email1 = "markedu211@gmail.com";
        final Long id1 = 2L;


        final Client client1 = new Client();
        client1.setId(id1);
        client1.setFullName(fullName1);
        client1.setEmail(email1);
        client1.setPhoneNumber(phoneNumber1);

        final ClientDTO clientDTO1 = new ClientDTO();
        clientDTO1.setFullName(fullName1);
        clientDTO1.setPhoneNumber(phoneNumber1);
        clientDTO1.setEmail(email1);
        clientDTO1.setId(id1);

        // first Currency
        final String codeCurrency1 = "EUR";
        final BigDecimal value1 = BigDecimal.valueOf(0.976258);

        final CurrencyDTO currency1 = new CurrencyDTO();
        currency1.setCode(codeCurrency1);
        currency1.setValue(value1);

        // first Account
        final UUID uuid1 = UUID.fromString("26bb29ea-8284-487d-bca6-44c64b98e80b");
        final BigDecimal amount1 = BigDecimal.valueOf(2000);

        final Account account1 = new Account();
        account1.setId(uuid1);
        account1.setIdClient(id1);
        account1.setCodeCurrency(codeCurrency1);
        account1.setAmount(amount1);

        final AccountDTO accountDTO1 = new AccountDTO();
        accountDTO1.setId(uuid1);
        accountDTO1.setIdClient(id1);
        accountDTO1.setCodeCurrency(codeCurrency1);
        accountDTO1.setAmount(amount1);

        // first Card
        final Long idCard1 = 1L;
        final Long cardNumber1 = 8282973914177809L;

        final Card card1 = new Card();
        card1.setId(idCard1);
        card1.setIdClient(id1);
        card1.setAmount(amount1);
        card1.setIdAccount(uuid1);
        card1.setCardNumber(cardNumber1);

        final CardDTO cardDTO1 = new CardDTO();
        cardDTO1.setId(idCard1);
        cardDTO1.setIdClient(id1);
        cardDTO1.setAmount(amount1);
        cardDTO1.setIdAccount(uuid1);
        cardDTO1.setCardNumber(cardNumber1);


        // Receiver

        // second Client
        final String fullName2 = "Jan Malone";
        final String phoneNumber2 = "0995620255";
        final String email2 = "jamalonePP@gmail.com";
        final Long id2 = 4L;

        final Client client2 = new Client();
        client2.setId(id2);
        client2.setFullName(fullName2);
        client2.setEmail(email2);
        client2.setPhoneNumber(phoneNumber2);

        final ClientDTO clientDTO2 = new ClientDTO();
        clientDTO2.setFullName(fullName2);
        clientDTO2.setPhoneNumber(phoneNumber2);
        clientDTO2.setEmail(email2);
        clientDTO2.setId(id2);

        // second Currency
        final String codeCurrency2 = "UAH";
        final BigDecimal value2 = BigDecimal.valueOf(36.769917);

        final CurrencyDTO currency2 = new CurrencyDTO();
        currency2.setCode(codeCurrency2);
        currency2.setValue(value2);

        // second Account
        final UUID uuid2 = UUID.fromString("681637c5-586e-4617-acb9-3aa57a427783");
        final BigDecimal amount2 = BigDecimal.valueOf(150.4);

        final Account account2 = new Account();
        account2.setId(uuid2);
        account2.setIdClient(id2);
        account2.setCodeCurrency(codeCurrency2);
        account2.setAmount(amount2);

        final AccountDTO accountDTO2 = new AccountDTO();
        accountDTO2.setId(uuid2);
        accountDTO2.setIdClient(id2);
        accountDTO2.setCodeCurrency(codeCurrency2);
        accountDTO2.setAmount(amount2);

        // second Card
        final Long idCard2 = 2L;
        final Long cardNumber2 = 3212453198635552L;

        final Card card2 = new Card();
        card1.setId(idCard2);
        card1.setIdClient(id2);
        card1.setAmount(amount2);
        card1.setIdAccount(uuid2);
        card1.setCardNumber(cardNumber2);

        final CardDTO cardDTO2 = new CardDTO();
        cardDTO2.setId(idCard2);
        cardDTO2.setIdClient(id2);
        cardDTO2.setAmount(amount2);
        cardDTO2.setIdAccount(uuid2);
        cardDTO2.setCardNumber(cardNumber2);

        final Long transactionId = 3L;
        final BigDecimal amount = BigDecimal.valueOf(150);
        final LocalDateTime time = LocalDateTime.of(2022, 11, 25, 0, 0, 0, 0);
        final String message = "Send 150 dollars to 3212453198635552 card";

        final Transaction transaction = new Transaction();
        transaction.setId(transactionId);
        transaction.setAmount(amount);
        transaction.setTime(time);
        transaction.setMessage(message);
        transaction.setIdSender(id1);
        transaction.setIdReceiver(id2);

        final TransferMoneyDTO transferMoneyDTO = new TransferMoneyDTO();
        transferMoneyDTO.setNumberCardSender(cardNumber1);
        transferMoneyDTO.setNumberCardReceiver(cardNumber2);
        transferMoneyDTO.setAmount(amount);
        transferMoneyDTO.setMessage(message);

        when(cardService.getByNumber(cardNumber1)).thenReturn(cardDTO1);
        when(cardService.getByNumber(cardNumber2)).thenReturn(cardDTO2);
        when(accountService.read(uuid1)).thenReturn(accountDTO1);
        when(accountService.read(uuid2)).thenReturn(accountDTO2);
        when(currencyService.read(codeCurrency1)).thenReturn(currency1);
        when(currencyService.read(codeCurrency2)).thenReturn(currency2);
        when(mapperTransaction.toEntity(any())).thenCallRealMethod();
        when(mapperTransaction.toDto(any())).thenCallRealMethod();
        when(transactionRepository.getId()).thenReturn(transactionId);
        when(transactionRepository.findById(transactionId)).thenReturn(transaction);

        try {
            final TransactionDTO transactionDTO = transactionService.transfer(transferMoneyDTO);


        } catch (final TransferNotEnoughMoneyException ex) {
            fail();
        }
    }

    @Test
    public void test_transfer() throws Exception {
        final MapperTransaction mapperTransaction = Mockito.mock(MapperTransaction.class);
        final TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);
        final CardServiceImpl cardService = Mockito.mock(CardServiceImpl.class);
        final AccountServiceImpl accountService = Mockito.mock(AccountServiceImpl.class);
        final CurrencyServiceImpl currencyService = Mockito.mock(CurrencyServiceImpl.class);

        final TransactionServiceImpl transactionService
                = new TransactionServiceImpl(mapperTransaction, transactionRepository, cardService, accountService, currencyService);

        // Sender

        // first Client
        final String fullName1 = "Markus Eduard";
        final String phoneNumber1 = "0953221430";
        final String email1 = "markedu211@gmail.com";
        final Long id1 = 2L;


        final Client client1 = new Client();
        client1.setId(id1);
        client1.setFullName(fullName1);
        client1.setEmail(email1);
        client1.setPhoneNumber(phoneNumber1);

        final ClientDTO clientDTO1 = new ClientDTO();
        clientDTO1.setFullName(fullName1);
        clientDTO1.setPhoneNumber(phoneNumber1);
        clientDTO1.setEmail(email1);
        clientDTO1.setId(id1);

        // first Currency
        final String codeCurrency1 = "EUR";
        final BigDecimal value1 = BigDecimal.valueOf(0.976258);

        final CurrencyDTO currency1 = new CurrencyDTO();
        currency1.setCode(codeCurrency1);
        currency1.setValue(value1);

        // first Account
        final UUID uuid1 = UUID.fromString("26bb29ea-8284-487d-bca6-44c64b98e80b");
        final BigDecimal amount1 = BigDecimal.valueOf(2000);

        final Account account1 = new Account();
        account1.setId(uuid1);
        account1.setIdClient(id1);
        account1.setCodeCurrency(codeCurrency1);
        account1.setAmount(amount1);

        final AccountDTO accountDTO1 = new AccountDTO();
        accountDTO1.setId(uuid1);
        accountDTO1.setIdClient(id1);
        accountDTO1.setCodeCurrency(codeCurrency1);
        accountDTO1.setAmount(amount1);

        // first Card
        final Long idCard1 = 1L;
        final Long cardNumber1 = 8282973914177809L;

        final Card card1 = new Card();
        card1.setId(idCard1);
        card1.setIdClient(id1);
        card1.setAmount(amount1);
        card1.setIdAccount(uuid1);
        card1.setCardNumber(cardNumber1);

        final CardDTO cardDTO1 = new CardDTO();
        cardDTO1.setId(idCard1);
        cardDTO1.setIdClient(id1);
        cardDTO1.setAmount(amount1);
        cardDTO1.setIdAccount(uuid1);
        cardDTO1.setCardNumber(cardNumber1);


        // Receiver

        // second Client
        final String fullName2 = "Jan Malone";
        final String phoneNumber2 = "0995620255";
        final String email2 = "jamalonePP@gmail.com";
        final Long id2 = 4L;

        final Client client2 = new Client();
        client2.setId(id2);
        client2.setFullName(fullName2);
        client2.setEmail(email2);
        client2.setPhoneNumber(phoneNumber2);

        final ClientDTO clientDTO2 = new ClientDTO();
        clientDTO2.setFullName(fullName2);
        clientDTO2.setPhoneNumber(phoneNumber2);
        clientDTO2.setEmail(email2);
        clientDTO2.setId(id2);

        // second Currency
        final String codeCurrency2 = "UAH";
        final BigDecimal value2 = BigDecimal.valueOf(36.769917);

        final CurrencyDTO currency2 = new CurrencyDTO();
        currency2.setCode(codeCurrency2);
        currency2.setValue(value2);

        // second Account
        final UUID uuid2 = UUID.fromString("681637c5-586e-4617-acb9-3aa57a427783");
        final BigDecimal amount2 = BigDecimal.valueOf(150.4);

        final Account account2 = new Account();
        account2.setId(uuid2);
        account2.setIdClient(id2);
        account2.setCodeCurrency(codeCurrency2);
        account2.setAmount(amount2);

        final AccountDTO accountDTO2 = new AccountDTO();
        accountDTO2.setId(uuid2);
        accountDTO2.setIdClient(id2);
        accountDTO2.setCodeCurrency(codeCurrency2);
        accountDTO2.setAmount(amount2);

        // second Card
        final Long idCard2 = 2L;
        final Long cardNumber2 = 3212453198635552L;

        final Card card2 = new Card();
        card1.setId(idCard2);
        card1.setIdClient(id2);
        card1.setAmount(amount2);
        card1.setIdAccount(uuid2);
        card1.setCardNumber(cardNumber2);

        final CardDTO cardDTO2 = new CardDTO();
        cardDTO2.setId(idCard2);
        cardDTO2.setIdClient(id2);
        cardDTO2.setAmount(amount2);
        cardDTO2.setIdAccount(uuid2);
        cardDTO2.setCardNumber(cardNumber2);

        final Long transactionId = 3L;
        final BigDecimal amount = BigDecimal.valueOf(150);
        final LocalDateTime time = LocalDateTime.of(2022, 11, 25, 0, 0, 0, 0);
        final String message = "Send 150 dollars to 3212453198635552 card";

        final Transaction transaction = new Transaction();
        transaction.setId(transactionId);
        transaction.setAmount(amount);
        transaction.setTime(time);
        transaction.setMessage(message);
        transaction.setIdSender(id1);
        transaction.setIdReceiver(id2);

        final TransferMoneyDTO transferMoneyDTO = new TransferMoneyDTO();
        transferMoneyDTO.setNumberCardSender(cardNumber1);
        transferMoneyDTO.setNumberCardReceiver(cardNumber2);
        transferMoneyDTO.setAmount(amount);
        transferMoneyDTO.setMessage(message);

        when(cardService.getByNumber(cardNumber1)).thenReturn(cardDTO1);
        when(cardService.getByNumber(cardNumber2)).thenReturn(cardDTO2);
        when(accountService.read(uuid1)).thenReturn(accountDTO1);
        when(accountService.read(uuid2)).thenReturn(accountDTO2);
        when(currencyService.read(codeCurrency1)).thenReturn(currency1);
        when(currencyService.read(codeCurrency2)).thenReturn(currency2);
        when(mapperTransaction.toEntity(any())).thenCallRealMethod();
        when(mapperTransaction.toDto(any())).thenCallRealMethod();
        when(transactionRepository.getId()).thenReturn(transactionId);
        when(transactionRepository.findById(transactionId)).thenReturn(transaction);

        final TransactionDTO transactionDTO = transactionService.transfer(transferMoneyDTO);

        assertEquals(transactionDTO.getId(), transactionId);
        assertEquals(transactionDTO.getIdSender(), id1);
        assertEquals(transactionDTO.getIdReceiver(), id2);
        assertEquals(transactionDTO.getAmount(), amount);
        assertEquals(transactionDTO.getMessage(), message);
        assertEquals(transactionDTO.getTime(), time);
    }

    @Test
    public void test_transfer_checkSelfTransfer() throws Exception {
        final MapperTransaction mapperTransaction = Mockito.mock(MapperTransaction.class);
        final TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);
        final CardServiceImpl cardService = Mockito.mock(CardServiceImpl.class);
        final AccountServiceImpl accountService = Mockito.mock(AccountServiceImpl.class);
        final CurrencyServiceImpl currencyService = Mockito.mock(CurrencyServiceImpl.class);

        final TransactionServiceImpl transactionService
                = new TransactionServiceImpl(mapperTransaction, transactionRepository, cardService, accountService, currencyService);

        // Sender

        // first Client
        final String fullName1 = "Markus Eduard";
        final String phoneNumber1 = "0953221430";
        final String email1 = "markedu211@gmail.com";
        final Long id1 = 2L;


        final Client client1 = new Client();
        client1.setId(id1);
        client1.setFullName(fullName1);
        client1.setEmail(email1);
        client1.setPhoneNumber(phoneNumber1);

        final ClientDTO clientDTO1 = new ClientDTO();
        clientDTO1.setFullName(fullName1);
        clientDTO1.setPhoneNumber(phoneNumber1);
        clientDTO1.setEmail(email1);
        clientDTO1.setId(id1);

        // first Currency
        final String codeCurrency1 = "EUR";
        final BigDecimal value1 = BigDecimal.valueOf(0.976258);

        final Currency currency1 = new Currency();
        currency1.setCode(codeCurrency1);
        currency1.setValue(value1);

        // first Account
        final UUID uuid1 = UUID.fromString("26bb29ea-8284-487d-bca6-44c64b98e80b");
        final BigDecimal amount1 = BigDecimal.valueOf(2000);

        final Account account1 = new Account();
        account1.setId(uuid1);
        account1.setIdClient(id1);
        account1.setCodeCurrency(codeCurrency1);
        account1.setAmount(amount1);

        final AccountDTO accountDTO1 = new AccountDTO();
        accountDTO1.setId(uuid1);
        accountDTO1.setIdClient(id1);
        accountDTO1.setCodeCurrency(codeCurrency1);
        accountDTO1.setAmount(amount1);

        // first Card
        final Long idCard1 = 1L;
        final Long cardNumber1 = 8282973914177809L;

        final Card card1 = new Card();
        card1.setId(idCard1);
        card1.setIdClient(id1);
        card1.setAmount(amount1);
        card1.setIdAccount(uuid1);
        card1.setCardNumber(cardNumber1);

        final CardDTO cardDTO1 = new CardDTO();
        cardDTO1.setId(idCard1);
        cardDTO1.setIdClient(id1);
        cardDTO1.setAmount(amount1);
        cardDTO1.setIdAccount(uuid1);
        cardDTO1.setCardNumber(cardNumber1);


        // Receiver

        // second Client
        final String fullName2 = "Jan Malone";
        final String phoneNumber2 = "0995620255";
        final String email2 = "jamalonePP@gmail.com";
        final Long id2 = 4L;

        final Client client2 = new Client();
        client2.setId(id2);
        client2.setFullName(fullName2);
        client2.setEmail(email2);
        client2.setPhoneNumber(phoneNumber2);

        final ClientDTO clientDTO2 = new ClientDTO();
        clientDTO2.setFullName(fullName2);
        clientDTO2.setPhoneNumber(phoneNumber2);
        clientDTO2.setEmail(email2);
        clientDTO2.setId(id2);

        // second Currency
        final String codeCurrency2 = "UAH";
        final BigDecimal value2 = BigDecimal.valueOf(36.769917);

        final Currency currency2 = new Currency();
        currency2.setCode(codeCurrency2);
        currency2.setValue(value2);

        // second Account
        final UUID uuid2 = UUID.fromString("681637c5-586e-4617-acb9-3aa57a427783");
        final BigDecimal amount2 = BigDecimal.valueOf(150.4);

        final Account account2 = new Account();
        account2.setId(uuid2);
        account2.setIdClient(id2);
        account2.setCodeCurrency(codeCurrency2);
        account2.setAmount(amount2);

        final AccountDTO accountDTO2 = new AccountDTO();
        accountDTO2.setId(uuid2);
        accountDTO2.setIdClient(id2);
        accountDTO2.setCodeCurrency(codeCurrency2);
        accountDTO2.setAmount(amount2);

        // second Card
        final Long idCard2 = 2L;
        final Long cardNumber2 = 8282973914177809L;

        final Card card2 = new Card();
        card1.setId(idCard2);
        card1.setIdClient(id2);
        card1.setAmount(amount2);
        card1.setIdAccount(uuid2);
        card1.setCardNumber(cardNumber2);

        final CardDTO cardDTO2 = new CardDTO();
        cardDTO2.setId(idCard2);
        cardDTO2.setIdClient(id2);
        cardDTO2.setAmount(amount2);
        cardDTO2.setIdAccount(uuid2);
        cardDTO2.setCardNumber(cardNumber2);

        final TransferMoneyDTO transferMoneyDTO = new TransferMoneyDTO();
        transferMoneyDTO.setNumberCardSender(cardNumber1);
        transferMoneyDTO.setNumberCardReceiver(cardNumber2);
        transferMoneyDTO.setAmount(BigDecimal.valueOf(3000));
        transferMoneyDTO.setMessage("Send 150 dollars to 3212453198635552 card");

        when(cardService.getByNumber(cardNumber1)).thenReturn(cardDTO1);
        when(cardService.getByNumber(cardNumber2)).thenReturn(cardDTO2);

        try {
            final TransactionDTO transactionDTO = transactionService.transfer(transferMoneyDTO);

            fail();
        } catch (final TransferSelfTransactionException ignored) {
        } catch (final Exception ex) {
            fail();
        }
    }

    @Test
    public void test_transfer_checkNotSelfTransfer() throws Exception {
        final MapperTransaction mapperTransaction = Mockito.mock(MapperTransaction.class);
        final TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);
        final CardServiceImpl cardService = Mockito.mock(CardServiceImpl.class);
        final AccountServiceImpl accountService = Mockito.mock(AccountServiceImpl.class);
        final CurrencyServiceImpl currencyService = Mockito.mock(CurrencyServiceImpl.class);

        final TransactionServiceImpl transactionService
                = new TransactionServiceImpl(mapperTransaction, transactionRepository, cardService, accountService, currencyService);

        // Sender

        // first Client
        final String fullName1 = "Markus Eduard";
        final String phoneNumber1 = "0953221430";
        final String email1 = "markedu211@gmail.com";
        final Long id1 = 2L;


        final Client client1 = new Client();
        client1.setId(id1);
        client1.setFullName(fullName1);
        client1.setEmail(email1);
        client1.setPhoneNumber(phoneNumber1);

        final ClientDTO clientDTO1 = new ClientDTO();
        clientDTO1.setFullName(fullName1);
        clientDTO1.setPhoneNumber(phoneNumber1);
        clientDTO1.setEmail(email1);
        clientDTO1.setId(id1);

        // first Currency
        final String codeCurrency1 = "EUR";
        final BigDecimal value1 = BigDecimal.valueOf(0.976258);

        final CurrencyDTO currency1 = new CurrencyDTO();
        currency1.setCode(codeCurrency1);
        currency1.setValue(value1);

        // first Account
        final UUID uuid1 = UUID.fromString("26bb29ea-8284-487d-bca6-44c64b98e80b");
        final BigDecimal amount1 = BigDecimal.valueOf(2000);

        final Account account1 = new Account();
        account1.setId(uuid1);
        account1.setIdClient(id1);
        account1.setCodeCurrency(codeCurrency1);
        account1.setAmount(amount1);

        final AccountDTO accountDTO1 = new AccountDTO();
        accountDTO1.setId(uuid1);
        accountDTO1.setIdClient(id1);
        accountDTO1.setCodeCurrency(codeCurrency1);
        accountDTO1.setAmount(amount1);

        // first Card
        final Long idCard1 = 1L;
        final Long cardNumber1 = 8282973914177809L;

        final Card card1 = new Card();
        card1.setId(idCard1);
        card1.setIdClient(id1);
        card1.setAmount(amount1);
        card1.setIdAccount(uuid1);
        card1.setCardNumber(cardNumber1);

        final CardDTO cardDTO1 = new CardDTO();
        cardDTO1.setId(idCard1);
        cardDTO1.setIdClient(id1);
        cardDTO1.setAmount(amount1);
        cardDTO1.setIdAccount(uuid1);
        cardDTO1.setCardNumber(cardNumber1);


        // Receiver

        // second Client
        final String fullName2 = "Jan Malone";
        final String phoneNumber2 = "0995620255";
        final String email2 = "jamalonePP@gmail.com";
        final Long id2 = 4L;

        final Client client2 = new Client();
        client2.setId(id2);
        client2.setFullName(fullName2);
        client2.setEmail(email2);
        client2.setPhoneNumber(phoneNumber2);

        final ClientDTO clientDTO2 = new ClientDTO();
        clientDTO2.setFullName(fullName2);
        clientDTO2.setPhoneNumber(phoneNumber2);
        clientDTO2.setEmail(email2);
        clientDTO2.setId(id2);

        // second Currency
        final String codeCurrency2 = "UAH";
        final BigDecimal value2 = BigDecimal.valueOf(36.769917);

        final CurrencyDTO currency2 = new CurrencyDTO();
        currency2.setCode(codeCurrency2);
        currency2.setValue(value2);

        // second Account
        final UUID uuid2 = UUID.fromString("681637c5-586e-4617-acb9-3aa57a427783");
        final BigDecimal amount2 = BigDecimal.valueOf(150.4);

        final Account account2 = new Account();
        account2.setId(uuid2);
        account2.setIdClient(id2);
        account2.setCodeCurrency(codeCurrency2);
        account2.setAmount(amount2);

        final AccountDTO accountDTO2 = new AccountDTO();
        accountDTO2.setId(uuid2);
        accountDTO2.setIdClient(id2);
        accountDTO2.setCodeCurrency(codeCurrency2);
        accountDTO2.setAmount(amount2);

        // second Card
        final Long idCard2 = 2L;
        final Long cardNumber2 = 3212453198635552L;

        final Card card2 = new Card();
        card1.setId(idCard2);
        card1.setIdClient(id2);
        card1.setAmount(amount2);
        card1.setIdAccount(uuid2);
        card1.setCardNumber(cardNumber2);

        final CardDTO cardDTO2 = new CardDTO();
        cardDTO2.setId(idCard2);
        cardDTO2.setIdClient(id2);
        cardDTO2.setAmount(amount2);
        cardDTO2.setIdAccount(uuid2);
        cardDTO2.setCardNumber(cardNumber2);

        final Long transactionId = 3L;
        final BigDecimal amount = BigDecimal.valueOf(150);
        final LocalDateTime time = LocalDateTime.of(2022, 11, 25, 0, 0, 0, 0);
        final String message = "Send 150 dollars to 3212453198635552 card";

        final Transaction transaction = new Transaction();
        transaction.setId(transactionId);
        transaction.setAmount(amount);
        transaction.setTime(time);
        transaction.setMessage(message);
        transaction.setIdSender(id1);
        transaction.setIdReceiver(id2);

        final TransferMoneyDTO transferMoneyDTO = new TransferMoneyDTO();
        transferMoneyDTO.setNumberCardSender(cardNumber1);
        transferMoneyDTO.setNumberCardReceiver(cardNumber2);
        transferMoneyDTO.setAmount(amount);
        transferMoneyDTO.setMessage(message);

        when(cardService.getByNumber(cardNumber1)).thenReturn(cardDTO1);
        when(cardService.getByNumber(cardNumber2)).thenReturn(cardDTO2);
        when(accountService.read(uuid1)).thenReturn(accountDTO1);
        when(accountService.read(uuid2)).thenReturn(accountDTO2);
        when(currencyService.read(codeCurrency1)).thenReturn(currency1);
        when(currencyService.read(codeCurrency2)).thenReturn(currency2);
        when(mapperTransaction.toEntity(any())).thenCallRealMethod();
        when(mapperTransaction.toDto(any())).thenCallRealMethod();
        when(transactionRepository.getId()).thenReturn(transactionId);
        when(transactionRepository.findById(transactionId)).thenReturn(transaction);

        try {
            final TransactionDTO transactionDTO = transactionService.transfer(transferMoneyDTO);

        } catch (final TransferSelfTransactionException ex) {
            fail();
        }
    }
}