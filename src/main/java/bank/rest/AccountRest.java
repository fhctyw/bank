package bank.rest;

import bank.dto.AccountDTO;
import bank.dto.ClientDTO;
import bank.entity.Account;
import bank.service.AccountService;
import bank.service.ClientService;
import bank.service.impl.AccountServiceImpl;
import bank.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
public class AccountRest {
    @Autowired
    private final AccountService accountService = new AccountServiceImpl();

    @PostMapping("create")
    public String create(final @RequestBody AccountDTO accountDTO) {
        accountService.create(accountDTO);
        return "All good";
    }

    @PostMapping
    public Account read(final @RequestBody int id) {
        return accountService.read(id);
    }

}
