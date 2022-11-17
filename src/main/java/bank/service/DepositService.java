package bank.service;

import bank.dto.DepositDTO;

import java.util.List;

public interface DepositService {
    void create(DepositDTO dto);
    DepositDTO read(Long id);
    void update(DepositDTO dto);
    void delete(Long id);
    List<DepositDTO> getAll();
}
