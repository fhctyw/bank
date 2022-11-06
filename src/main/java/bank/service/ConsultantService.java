package bank.service;

import bank.dto.ConsultantDto;
import bank.entity.Consultant;
import org.springframework.stereotype.Service;

public interface ConsultantService {
    void create(ConsultantDto dto);
    Consultant read(int id);
    void update(ConsultantDto dto);
    void delete(int id);
}
