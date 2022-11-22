package bank.mapper;

import bank.dto.ConsultantDTO;
import bank.entity.Consultant;
import org.springframework.stereotype.Component;

@Component
public class MapperConsultant {
    public ConsultantDTO toDto(final Consultant consultant) {
        final ConsultantDTO dto = new ConsultantDTO();
        dto.setId(consultant.getId());
        dto.setFullName(consultant.getFullName());
        dto.setEmail(consultant.getEmail());
        dto.setPhoneNumber(consultant.getPhoneNumber());
        return dto;
    }

    public Consultant toEntity(final ConsultantDTO dto) {
        final Consultant consultant = new Consultant();
        consultant.setId(dto.getId());
        consultant.setFullName(dto.getFullName());
        consultant.setEmail(dto.getEmail());
        consultant.setPhoneNumber(dto.getPhoneNumber());
        return consultant;
    }
}
