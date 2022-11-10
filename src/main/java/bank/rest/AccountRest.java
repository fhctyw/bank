package bank.rest;

import bank.dto.AccountDTO;
import bank.dto.ClientDTO;
import bank.dto.ConsultantDTO;
import bank.entity.Account;
import bank.service.AccountService;
import bank.service.ClientService;
import bank.service.impl.AccountServiceImpl;
import bank.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
public class AccountRest {
    @Autowired
    private final AccountService accountService = new AccountServiceImpl();

    @PostMapping
    public String create(final @RequestBody AccountDTO accountDTO) {
        accountService.create(accountDTO);
        return "All good";
    }
    @PutMapping
    public ResponseEntity<AccountDTO> put(final @RequestBody AccountDTO accountDTO) {
        accountService.update(accountDTO);
        return ResponseEntity.ok(accountDTO);
    }

    @GetMapping
    public AccountDTO get(final @RequestBody Long id) {
        return accountService.read(id);
    }

    @DeleteMapping
    public ResponseEntity<AccountDTO> delete(final @RequestBody Long id) {
        final AccountDTO accountDTO = get(id);
        accountService.delete(id);
        return ResponseEntity.ok(accountDTO);
    }

}
