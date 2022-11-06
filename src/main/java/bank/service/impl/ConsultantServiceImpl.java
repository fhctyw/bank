package bank.service.impl;

import bank.dto.ConsultantDto;
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
    public void create(final ConsultantDto dto) {
        final Consultant consultant = mapperConsultant.toEntity(dto);
        consultantRepository.add(consultant);
    }

    @Override
    public Consultant read(final int id) {
        return null;
    }

    @Override
    public void update(final ConsultantDto dto) {

    }

    @Override
    public void delete(final int id) {

    }
}
