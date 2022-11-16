package bank.service;

import bank.dto.ConsultantDTO;

import java.util.List;

public interface ConsultantService {
    ConsultantDTO create(ConsultantDTO dto);
    ConsultantDTO read(Long id);
    ConsultantDTO update(ConsultantDTO dto);
    ConsultantDTO delete(Long id);
    List<ConsultantDTO> getAll();
}
