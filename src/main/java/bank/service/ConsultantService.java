package bank.service;

import bank.dto.ConsultantDTO;

public interface ConsultantService {
    void create(ConsultantDTO dto);
    ConsultantDTO read(Long id);
    void update(ConsultantDTO dto);
    void delete(Long id);
}
