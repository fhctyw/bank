package bank.repository;

import bank.entity.Consultant;
import bank.entity.Currency;
import bank.util.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyRepository {
    final private String source = "currencies.txt";
    private List<Currency> currencies = new ArrayList<>();

    @PostConstruct
    public void postConstructor() {
        final Path file = Paths.get(source);
        try {
            currencies = JacksonUtil.deserialize(Files.readString(file, StandardCharsets.UTF_16), new TypeReference<List<Currency>>() {});

            if (currencies == null) {
                currencies = new ArrayList<>();
            }


        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get(source);

        try {
            Files.writeString(file, JacksonUtil.serialize(currencies), StandardCharsets.UTF_16);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void addCurrency(final Currency currency) {
        currencies.add(currency);
    }

    public Currency findByCode(final String code) {
        return currencies.stream().filter(e -> e.getCode().equals(code)).findFirst().orElseThrow();
    }

    public void setCurrency(final Currency currency) {
        final Currency c = findByCode(currency.getCode());
        c.setCode(currency.getCode());
        c.setValue(currency.getValue());

    }

    public void deleteCurrency(final String code) {
        currencies.removeIf(e -> e.getCode().equals(code));
    }
}
