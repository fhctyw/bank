package bank.service;

import bank.dto.CreditDTO;

public interface CreditService {
    void create(CreditDTO dto);
    CreditDTO read(Long id);
    void update(CreditDTO dto);
    void delete(Long id);
    void getCredit();
}
