package bank.repository;

import bank.entity.Currency;
import bank.exception.ServiceException;
import bank.util.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class CurrencyRepository {
    final private String source = "currencies.txt";
    private List<Currency> currencies = new ArrayList<>();

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(final List<Currency> currencies) {
        this.currencies = currencies;
    }

    @PostConstruct
    public void postConstructor() {
        final Path file = Paths.get(source);
        try {
            currencies = JacksonUtil.deserialize(Files.readString(file, StandardCharsets.UTF_16), new TypeReference<>() {});

            if (currencies == null) {
                currencies = new ArrayList<>();
            }

        } catch (final IOException e) {
            System.out.println("file " + source + " doesn't exist");
        }
    }

    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get(source);

        try {
            Files.writeString(file, Objects.requireNonNull(JacksonUtil.serialize(currencies)), StandardCharsets.UTF_16);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void add(final Currency currency) {
        currencies.add(currency);
    }

    public Currency findByCode(final String code) {
        return currencies.stream().filter(e -> e.getCode().equals(code)).findFirst()
                .orElseThrow(() -> new ServiceException("No such id when finding"));
    }

    public void set(final Currency currency) {
        final Currency c = findByCode(currency.getCode());
        c.setCode(currency.getCode());
        c.setValue(currency.getValue());
    }

    public void delete(final String code) {
        setCurrencies(currencies.stream().filter(e -> !e.getCode().equals(code)).collect(Collectors.toList()));
    }
}
