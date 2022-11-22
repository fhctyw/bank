package bank.service.impl;


import bank.dto.CurrencyDTO;
import bank.entity.Currency;
import bank.mapper.MapperCurrency;
import bank.repository.CurrencyRepository;
import bank.service.CurrencyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    private final MapperCurrency mapperCurrency = new MapperCurrency();
    @Autowired
    private final CurrencyRepository currencyRepository = new CurrencyRepository();

    @PostConstruct
    public void postConstruct() {
        try {
            final URL url = new URL("https://api.currencyapi.com/v3/latest?apikey=uhqDe2LAIPmOA8TVH5GL46vv3I6dRji4UNQFMV7p");
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");

            final InputStream responseStream = connection.getInputStream();
            final ObjectMapper mapper = new ObjectMapper();
            final Map<String, Object> obj = (LinkedHashMap<String, Object>) mapper.readValue(responseStream, Object.class);
            final Map<String, Object> data
                    = (LinkedHashMap<String, Object>) obj.get("data");

            final List<CurrencyDTO> currencyList = new ArrayList<>();

            for (final Map.Entry<String, Object> entry : data.entrySet()) {
                final Map<String, Object> currency = (Map<String, Object>) entry.getValue();
                currencyList.add(new CurrencyDTO((String) currency.get("code"), new BigDecimal(currency.get("value").toString())));
            }

            currencyRepository.setCurrencies(currencyList.stream().map(mapperCurrency::toEntity).collect(Collectors.toList()));

        } catch (final Exception ex) {
            System.out.println("Cannot get currency");
        }
    }

    @Override
    public CurrencyDTO create(final CurrencyDTO dto) {
        final Currency currency = mapperCurrency.toEntity(dto);
        currencyRepository.add(currency);
        return mapperCurrency.toDto(currencyRepository.findByCode(dto.getCode()));
    }

    @Override
    public CurrencyDTO read(final String code) {
        return mapperCurrency.toDto(currencyRepository.findByCode(code));
    }

    @Override
    public CurrencyDTO update(final CurrencyDTO dto) {
        currencyRepository.set(mapperCurrency.toEntity(dto));
        return mapperCurrency.toDto(currencyRepository.findByCode(dto.getCode()));
    }

    @Override
    public CurrencyDTO delete(final String code) {
        final CurrencyDTO currencyDTO = mapperCurrency.toDto(currencyRepository.findByCode(code));
        currencyRepository.delete(code);
        return currencyDTO;
    }

    @Override
    public List<CurrencyDTO> getAll() {
        return currencyRepository.getCurrencies().stream().map(mapperCurrency::toDto).collect(Collectors.toList());
    }
}
