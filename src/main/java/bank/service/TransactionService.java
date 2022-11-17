package bank.service;

import bank.dto.TransactionDTO;
import bank.entity.Transaction;

import java.util.List;

public interface TransactionService {
    TransactionDTO create(TransactionDTO dto);
    TransactionDTO read(Long id);
    List<TransactionDTO> readAll();
    TransactionDTO update(TransactionDTO dto);
    TransactionDTO delete(Long id);
}
