package bank.repository;

import bank.db.FileCurrency;
import bank.entity.Consultant;
import bank.entity.Currency;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyRepository {
    final private List<Currency> currencies = new ArrayList<>();
    final public FileCurrency fileCurrency = new FileCurrency(this);
    private Long id = 0L;

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public CurrencyRepository() {
        fileCurrency.read();
    }

    public void addCurrency(final Currency currency) {
        currencies.add(currency);

        fileCurrency.write();
    }
    public Currency findByCode(final String code) {
        return currencies.stream().filter(e->e.getCode().equals(code)).findFirst().orElseThrow();
    }

    public void setCurrency(final Currency currency) {
        final Currency c = findByCode(currency.getCode());
        c.setCode(currency.getCode());
        c.setValue(currency.getValue());

        fileCurrency.write();
    }

    public void deleteCurrency(final String code) {
        currencies.removeIf(e->e.getCode().equals(code));
        fileCurrency.write();
    }
}
