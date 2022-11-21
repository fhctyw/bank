package bank.resource;

import bank.dto.CurrencyDTO;
import bank.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/currency", produces = MediaType.APPLICATION_JSON_VALUE)
public class CurrencyResource {
    @Autowired
    private final CurrencyService currencyService;

    @PostMapping
    public CurrencyDTO create(final @Validated @RequestBody CurrencyDTO currencyDTO) {
        return currencyService.create(currencyDTO);
    }

    @PutMapping
    public CurrencyDTO put(final @Validated @RequestBody CurrencyDTO currencyDTO) {
        return currencyService.update(currencyDTO);
    }

    @GetMapping
    public CurrencyDTO get(final @RequestBody @Validated @NotBlank String code) {
        return currencyService.read(code);
    }

    @DeleteMapping
    public CurrencyDTO delete(final @RequestBody @Validated @NotBlank String code) {
        return currencyService.delete(code);
    }

    @GetMapping(value = "/all")
    public List<CurrencyDTO> getAll() {
        return currencyService.getAll();
    }
}


