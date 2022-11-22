package bank.service;

import bank.dto.CurrencyDTO;

import java.util.List;

public interface CurrencyService {
    CurrencyDTO create(CurrencyDTO dto);
    CurrencyDTO read(String code);
    CurrencyDTO update(CurrencyDTO dto);
    CurrencyDTO delete(String code);
    List<CurrencyDTO> getAll();
}
