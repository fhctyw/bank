package bank.service.impl;

import bank.dto.AccountDTO;
import bank.dto.TransactionDTO;
import bank.entity.Account;
import bank.entity.Transaction;
import bank.mapper.MapperTransaction;
import bank.repository.TransactionRepository;
import bank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    final MapperTransaction mapperTransaction = new MapperTransaction();
    @Autowired
    final TransactionRepository transactionRepository = new TransactionRepository();

    @Override
    public void create(TransactionDTO dto) {
        final Transaction transaction = mapperTransaction.toEntity(dto);
        transactionRepository.add(transaction);
    }

    @Override
    public TransactionDTO read(Long id) {
        final Transaction transaction = transactionRepository.get(id);
        final TransactionDTO dto = mapperTransaction.toDTO(transaction);
        return  dto;
    }

    @Override
    public List<Transaction> readAll(Long id) {
        List<Transaction> transaction  = new ArrayList<>();
        transaction =  transactionRepository.getTransactions()
                .stream()
                .filter(e->e.getIdSender().equals(id))
                .collect(Collectors.toList());
        return transaction;
    }

    @Override
    public void update(TransactionDTO dto) {
        transactionRepository.update(dto.getId(),dto);
    }

    @Override
    public void delete(Long id) {
        transactionRepository.delete(id);
    }
}
