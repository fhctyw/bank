package bank.util.impl;

import bank.dto.ConsultantDTO;
import bank.entity.Consultant;
import bank.mapper.MapperConsultant;
import bank.repository.ConsultantRepository;
import bank.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConsultantServiceImpl implements ConsultantService {
    @Autowired
    final MapperConsultant mapperConsultant = new MapperConsultant();
    @Autowired
    final ConsultantRepository consultantRepository = new ConsultantRepository();

    @Override
    public void create(final ConsultantDTO dto) {

        final Consultant consultant = mapperConsultant.toEntity(dto);
        consultantRepository.add(consultant);
    }

    @Override
    public ConsultantDTO read(final Long id) {
        return mapperConsultant.toDto(consultantRepository.findById(id));
    }

    @Override
    public void update(final ConsultantDTO dto) {
        consultantRepository.setConsultant(dto.getId(), mapperConsultant.toEntity(dto));
    }

    @Override
    public void delete(final Long id) {
        consultantRepository.deleteConsultant(id);
    }
}
