package bank.service;

import bank.dto.TransactionDTO;
import bank.dto.TransferMoneyDTO;

import java.util.List;

public interface TransactionService {
    TransactionDTO create(TransactionDTO dto);
    TransactionDTO read(Long id);
    List<TransactionDTO> readAll();
    List<TransactionDTO> readClient(Long id);
    TransactionDTO update(TransactionDTO dto);
    TransactionDTO delete(Long id);
    List<TransactionDTO> getAll();
    TransactionDTO transfer(TransferMoneyDTO dto);
}
