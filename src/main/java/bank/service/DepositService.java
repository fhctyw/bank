package bank.service;

import bank.dto.DepositDTO;
import bank.dto.MakeDepositDTO;

import java.util.List;

public interface DepositService {
    DepositDTO create(DepositDTO dto);
    DepositDTO read(Long id);
    void update(DepositDTO dto);
    void delete(Long id);
    List<DepositDTO> getAll();
    DepositDTO putDeposit(MakeDepositDTO dto);
    void withdrawDeposit();
}
