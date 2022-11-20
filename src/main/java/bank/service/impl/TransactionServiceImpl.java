package bank.service.impl;

import bank.dto.AccountDTO;
import bank.dto.CardDTO;
import bank.dto.TransactionDTO;
import bank.dto.TransferMoneyDTO;
import bank.entity.Transaction;
import bank.exception.ServiceException;
import bank.mapper.MapperTransaction;
import bank.repository.TransactionRepository;
import bank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private final MapperTransaction mapperTransaction;
    @Autowired
    private final TransactionRepository transactionRepository;
    @Autowired
    private final CardServiceImpl cardService;
    @Autowired
    private final AccountServiceImpl accountService;

    public TransactionServiceImpl() {
        mapperTransaction = new MapperTransaction();
        transactionRepository = new TransactionRepository();
        cardService = new CardServiceImpl();
        accountService = new AccountServiceImpl();
    }

    @Override
    public TransactionDTO create(final TransactionDTO dto) {
        final Transaction transaction = mapperTransaction.toEntity(dto);
        transactionRepository.add(transaction);
        final Transaction transactionInRepos = transactionRepository.findById(transactionRepository.getId());
        return mapperTransaction.toDto(transactionInRepos);
    }

    @Override
    public TransactionDTO read(final Long id) {
        return mapperTransaction.toDto(transactionRepository.findById(id));
    }

    @Override
    public List<TransactionDTO> readAll() {
        return transactionRepository.getTransactions().stream()
                .map(mapperTransaction::toDto).collect(Collectors.toList());
    }

    @Override
    public TransactionDTO update(final TransactionDTO dto) {
        transactionRepository.set(dto.getId(), mapperTransaction.toEntity(dto));
        return read(dto.getId());
    }

    @Override
    public TransactionDTO delete(final Long id) {
        final Transaction transaction = transactionRepository.findById(id);
        transactionRepository.delete(id);
        return mapperTransaction.toDto(transaction);
    }

    @Override
    public List<TransactionDTO> getAll() {
        return transactionRepository.getTransactions().stream().map(mapperTransaction::toDto).collect(Collectors.toList());
    }

    private boolean validateAmount(final BigDecimal needAmount, final BigDecimal senderAmount) {
        return needAmount.compareTo(senderAmount) > 0;
    }

    @Override
    public TransactionDTO transfer(final TransferMoneyDTO dto) {
        final CardDTO senderCard = cardService.getByNumber(dto.getNumberCardSender());
        final CardDTO receiverCard = cardService.getByNumber(dto.getNumberCardReceiver());

        if (!validateAmount(dto.getAmount(), senderCard.getAmount())) {
            throw new ServiceException("Not enough money",
                    "Not enough " + dto.getAmount().subtract(senderCard.getAmount()));
        }

        final AccountDTO accountSenderDTO = accountService.read(senderCard.getIdAccount());

        senderCard.setAmount(senderCard.getAmount().subtract(dto.getAmount()));
        accountSenderDTO.setAmount(accountSenderDTO.getAmount().subtract(dto.getAmount()));

        accountService.update(accountSenderDTO);
        cardService.update(senderCard);

        final AccountDTO accountReceiverDTO = accountService.read(receiverCard.getIdAccount());

        receiverCard.setAmount(receiverCard.getAmount().subtract(dto.getAmount()));
        accountReceiverDTO.setAmount(accountReceiverDTO.getAmount().subtract(dto.getAmount()));

        accountService.update(accountSenderDTO);
        cardService.update(senderCard);

        final TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(null);
        transactionDTO.setAmount(dto.getAmount());
        transactionDTO.setIdSender(dto.getNumberCardSender());
        transactionDTO.setIdReceiver(dto.getNumberCardReceiver());
        transactionDTO.setMessage(dto.getMessage());
        transactionDTO.setTime(LocalDateTime.now());

        create(transactionDTO);

        return transactionDTO;
    }
}
