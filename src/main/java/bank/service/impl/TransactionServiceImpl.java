package bank.service.impl;

import bank.dto.TransactionDTO;
import bank.entity.Transaction;
import bank.mapper.MapperTransaction;
import bank.repository.TransactionRepository;
import bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    final MapperTransaction mapperTransaction = new MapperTransaction();
    @Autowired
    final TransactionRepository transactionRepository = new TransactionRepository();

    @Override
    public void create(final TransactionDTO dto) {
        final Transaction transaction = mapperTransaction.toEntity(dto);
        transactionRepository.add(transaction);
    }

    @Override
    public TransactionDTO read(final Long id) {
        final Transaction transaction = transactionRepository.get(id);
        final TransactionDTO dto = mapperTransaction.toDTO(transaction);
        return  dto;
    }

    @Override
    public void update(final TransactionDTO dto) {
        transactionRepository.update(dto.getId(),dto);
    }

    @Override
    public void delete(Long id) {
        transactionRepository.delete(id);
    }
}
