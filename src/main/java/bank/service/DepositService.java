package bank.service;

import bank.dto.DepositDTO;

public interface DepositService {
    void create(DepositDTO dto);
    DepositDTO read(Long id);
    void update(DepositDTO dto);
    void delete(Long id);
}
