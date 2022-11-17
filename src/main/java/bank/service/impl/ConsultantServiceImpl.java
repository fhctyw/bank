package bank.service.impl;

import bank.dto.ConsultantDTO;
import bank.entity.Consultant;
import bank.mapper.MapperConsultant;
import bank.repository.ConsultantRepository;
import bank.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ConsultantServiceImpl implements ConsultantService {
    @Autowired
    final MapperConsultant mapperConsultant = new MapperConsultant();
    @Autowired
    final ConsultantRepository consultantRepository = new ConsultantRepository();

    @Override
    public ConsultantDTO create(final ConsultantDTO dto) {
        final Consultant consultant = mapperConsultant.toEntity(dto);
        consultantRepository.add(consultant);
        final Consultant consultantInRepos = consultantRepository.findById(consultantRepository.getId());
        return mapperConsultant.toDto(consultantInRepos);
    }

    @Override
    public ConsultantDTO read(final Long id) {
        return mapperConsultant.toDto(consultantRepository.findById(id));
    }

    @Override
    public ConsultantDTO update(final ConsultantDTO dto) {
        consultantRepository.set(dto.getId(), mapperConsultant.toEntity(dto));
        return read(dto.getId());
    }

    @Override
    public ConsultantDTO delete(final Long id) {
        final Consultant consultant = consultantRepository.findById(id);
        consultantRepository.delete(id);
        return mapperConsultant.toDto(consultant);
    }

    @Override
    public List<ConsultantDTO> getAll() {
        return consultantRepository.getConsultants().stream().map(mapperConsultant::toDto).collect(Collectors.toList());
    }
}
