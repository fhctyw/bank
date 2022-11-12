package bank.resource;

import bank.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyResource {

    private final CurrencyService currencyService;


/*
    public void CurrencyResource() {
        //currencyService.getCurrencies();
    }*/
}
