package bank.mapper;

import bank.dto.TransactionDTO;
import bank.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class MapperTransaction {
    public  TransactionDTO toDTO(final Transaction transaction) {
        final TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setTime(transaction.getTime());
        dto.setMessage(transaction.getMessage());
        dto.setAmount_of_transaction(transaction.getAmount_of_transaction());
        dto.setIdSender(transaction.getIdSender());
        dto.setIdReceiver(transaction.getIdReceiver());
        return dto;
    }
    public  Transaction toEntity(final TransactionDTO dto) {
        final Transaction transaction = new Transaction();
        transaction.setId(dto.getId());
        transaction.setTime(dto.getTime());
        transaction.setMessage(dto.getMessage());
        transaction.setAmount_of_transaction(dto.getAmount_of_transaction());
        transaction.setIdSender(dto.getIdSender());
        transaction.setIdReceiver(dto.getIdReceiver());
        return transaction;
    }
}
