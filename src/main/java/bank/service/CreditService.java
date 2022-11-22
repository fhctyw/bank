package bank.service;

import bank.dto.CreditDTO;

public interface CreditService {
    CreditDTO create(CreditDTO dto);
    CreditDTO read(Long id);
    CreditDTO update(CreditDTO dto);
    CreditDTO delete(Long id);
    void getCredit();
}
