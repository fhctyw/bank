package bank.mapper;

import bank.dto.DepositDTO;
import bank.entity.Deposit;
import org.springframework.stereotype.Component;

@Component
public class MapperDeposit {
    public DepositDTO toDTO(final Deposit deposit){
        final DepositDTO dto = new DepositDTO();
        dto.setClient(deposit.getClient());
        dto.setAmount(deposit.getAmount());
        dto.setDepositId(deposit.getDepositId());
        dto.setCardId(deposit.getCardId());
        dto.setConsultantId(deposit.getConsultantId());
        dto.setPutTime(deposit.getPutTime());
        dto.setWithdrawTime(deposit.getWithdrawTime());
        dto.setPercentage(deposit.getPercentage());
        return dto;
    }

    public Deposit toEntity(DepositDTO dto){
        final Deposit deposit = new Deposit();
        deposit.setClient(dto.getClient());
        deposit.setAmount(dto.getAmount());
        deposit.setDepositId(dto.getDepositId());
        deposit.setCardId(dto.getCardId());
        deposit.setConsultantId(dto.getConsultantId());
        deposit.setPutTime(dto.getPutTime());
        deposit.setWithdrawTime(dto.getWithdrawTime());
        deposit.setPercentage(dto.getPercentage());
        return deposit;
    }
}
