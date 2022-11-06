package bank.mapper;

import bank.dto.ConsultantDto;
import bank.entity.Consultant;
import org.springframework.stereotype.Component;

@Component
public class MapperConsultant {
    public ConsultantDto toDto(final Consultant consultant) {
        final ConsultantDto dto = new ConsultantDto();
        dto.setId(consultant.getId());
        dto.setFullName(consultant.getFullName());
        return dto;
    }

    public Consultant toEntity(final ConsultantDto dto) {
        final Consultant consultant = new Consultant();
        consultant.setId(dto.getId());
        consultant.setFullName(dto.getFullName());
        return consultant;
    }
}
