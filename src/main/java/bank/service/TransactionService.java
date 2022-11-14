package bank.service;

import bank.dto.TransactionDTO;
import bank.entity.Transaction;

import java.util.List;

public interface TransactionService {
    void create(TransactionDTO dto);
    TransactionDTO read(Long id);
    List<TransactionDTO> readAll(Long id);
    void update(TransactionDTO dto);
    void delete(Long id);
}
