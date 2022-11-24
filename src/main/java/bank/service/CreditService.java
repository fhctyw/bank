package bank.service;

import bank.dto.CardDTO;
import bank.dto.CreditDTO;
import bank.dto.PayCreditDTO;

import java.util.List;

public interface CreditService {
    CreditDTO create(CreditDTO dto);
    CreditDTO read(Long id);
    CreditDTO update(CreditDTO dto);
    CreditDTO delete(Long id);
    void getCredit();
    List<CreditDTO> getAll();
    CardDTO payCredit(PayCreditDTO creditDTO);
}
