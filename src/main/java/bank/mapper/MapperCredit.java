package bank.mapper;

import bank.dto.CreditDTO;
import bank.entity.Credit;
import org.springframework.stereotype.Component;

@Component
public class MapperCredit {
    public CreditDTO toDTO(Credit credit){
        final CreditDTO dto = new CreditDTO();
        dto.setId(credit.getId());
        dto.setIdConsultant(credit.getIdConsultant());
        dto.setAmount(credit.getAmount());
        dto.setIdClient(credit.getIdClient());
        dto.setPercent(credit.getPercent());
        dto.setCreateDate(credit.getCreateDate());
        dto.setDeadlineDate(credit.getDeadlineDate());
        return dto;
    }

    public Credit toEntity(CreditDTO dto){
        final Credit credit = new Credit();
        credit.setId(dto.getId());
        credit.setAmount(dto.getAmount());
        credit.setIdClient(dto.getIdClient());
        credit.setPercent(dto.getPercent());
        credit.setCreateDate(dto.getCreateDate());
        credit.setDeadlineDate(dto.getDeadlineDate());
        return credit;
    }
}
