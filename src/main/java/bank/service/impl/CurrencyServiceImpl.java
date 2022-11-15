package bank.service.impl;

import bank.dto.CurrencyDTO;
import bank.entity.Currency;
import bank.mapper.MapperCurrency;
import bank.repository.CurrencyRepository;
import bank.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final MapperCurrency mapperCurrency;
    private final CurrencyRepository currencyRepository;

    @Override
    public void getCurrencies() {

    }

    @Override
    public void create(final CurrencyDTO dto) {
        final Currency currency = mapperCurrency.toEntity(dto);
        currencyRepository.addCurrency(currency);
    }

    @Override
    public CurrencyDTO read(final String code) {
        return mapperCurrency.toDto(currencyRepository.findByCode(code));
    }

    @Override
    public void update(final CurrencyDTO dto) {
        currencyRepository.setCurrency(mapperCurrency.toEntity(dto));
    }

    @Override
    public void delete(final String code) {
        currencyRepository.deleteCurrency(code);
    }
}
