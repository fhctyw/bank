package bank.service;

import bank.dto.CurrencyDTO;

public interface CurrencyService {
    void create(CurrencyDTO dto);
    CurrencyDTO read(String code);
    void update(CurrencyDTO dto);
    void delete(String code);
    void getCurrencies();
}
