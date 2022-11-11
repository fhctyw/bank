package bank.service;

import bank.dto.TransactionDTO;

public interface TransactionService {
    void create(TransactionDTO dto);
    TransactionDTO read(Long id);
    void update(TransactionDTO dto);
    void delete(Long id);
}
