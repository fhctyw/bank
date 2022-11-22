package bank.mapper;

import bank.dto.TransactionDTO;
import bank.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class MapperTransaction {
    public  TransactionDTO toDto(final Transaction transaction) {
        final TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setTime(transaction.getTime());
        dto.setMessage(transaction.getMessage());
        dto.setAmount(transaction.getAmount());
        dto.setIdSender(transaction.getIdSender());
        dto.setIdReceiver(transaction.getIdReceiver());
        return dto;
    }
    public  Transaction toEntity(final TransactionDTO dto) {
        final Transaction transaction = new Transaction();
        transaction.setId(dto.getId());
        transaction.setTime(dto.getTime());
        transaction.setMessage(dto.getMessage());
        transaction.setAmount(dto.getAmount());
        transaction.setIdSender(dto.getIdSender());
        transaction.setIdReceiver(dto.getIdReceiver());
        return transaction;
    }
}
