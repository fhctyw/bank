package bank.mapper;

import bank.dto.CurrencyDTO;
import bank.entity.Currency;
import org.springframework.stereotype.Component;

@Component
public class MapperCurrency {
    public CurrencyDTO toDto(final Currency currency) {
        final CurrencyDTO currencyDto = new CurrencyDTO();
        currencyDto.setCode(currency.getCode());
        currencyDto.setValue(currency.getValue());
        return currencyDto;
    }

    public Currency toEntity(final CurrencyDTO dto) {
        final Currency currency = new Currency();
        currency.setCode(dto.getCode());
        currency.setValue(dto.getValue());
        return currency;
    }
}
