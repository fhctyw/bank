package bank.service;

import bank.dto.*;

import java.util.List;

public interface CreditService {
    CreditDTO create(CreditDTO dto);
    CreditDTO read(Long id);
    CreditDTO update(CreditDTO dto);
    CreditDTO delete(Long id);
    void getCredit();
    List<CreditDTO> getAll();

    MakeCreditResponseDTO makeCredit(MakeCreditDTO makeCreditDTO);

    CardDTO payCredit(PayCreditDTO creditDTO);
}
