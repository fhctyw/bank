package bank.mapper;

import bank.dto.DepositDTO;
import bank.entity.Deposit;
import org.springframework.stereotype.Component;

@Component
public class MapperDeposit {
    public DepositDTO toDTO(final Deposit deposit){
        final DepositDTO dto = new DepositDTO();
        dto.setBalance(deposit.getBalance());
        dto.setAmount(deposit.getAmount());
        dto.setId(deposit.getId());
        dto.setCardId(deposit.getCardId());
        dto.setConsultantId(deposit.getConsultantId());
        dto.setPutTime(deposit.getPutTime());
        dto.setWithdrawTime(deposit.getWithdrawTime());
        dto.setPercentage(deposit.getPercentage());
        return dto;
    }

    public Deposit toEntity(DepositDTO dto){
        final Deposit deposit = new Deposit();
        deposit.setBalance(dto.getBalance());
        deposit.setAmount(dto.getAmount());
        deposit.setId(dto.getId());
        deposit.setCardId(dto.getCardId());
        deposit.setConsultantId(dto.getConsultantId());
        deposit.setPutTime(dto.getPutTime());
        deposit.setWithdrawTime(dto.getWithdrawTime());
        deposit.setPercentage(dto.getPercentage());
        return deposit;
    }
}
