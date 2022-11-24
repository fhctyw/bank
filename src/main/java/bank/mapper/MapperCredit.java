package bank.mapper;

import bank.dto.CreditDTO;
import bank.entity.Credit;
import org.springframework.stereotype.Component;

@Component
public class MapperCredit {
    public CreditDTO toDTO(Credit credit){
        final CreditDTO dto = new CreditDTO();
        dto.setId(credit.getId());
        dto.setIdClient(credit.getIdClient());
        dto.setAmount(credit.getAmount());
        dto.setCardNumber(credit.getCardNumber());
        dto.setPercent(credit.getPercent());
        dto.setCreateTime(credit.getCreateTime());
        dto.setPayTime(credit.getPayTime());
        return dto;
    }

    public Credit toEntity(CreditDTO dto){
        final Credit credit = new Credit();
        credit.setId(dto.getId());
        credit.setIdClient(dto.getIdClient());
        credit.setAmount(dto.getAmount());
        credit.setCardNumber(dto.getCardNumber());
        credit.setPercent(dto.getPercent());
        credit.setCreateTime(dto.getCreateTime());
        credit.setPayTime(dto.getPayTime());
        return credit;
    }
}
