package bank.service.impl;

import bank.dto.TransactionDTO;
import bank.entity.Transaction;
import bank.mapper.MapperTransaction;
import bank.repository.TransactionRepository;
import bank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
