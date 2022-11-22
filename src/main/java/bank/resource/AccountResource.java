package bank.resource;

import bank.dto.AccountDTO;
import bank.service.AccountService;
import bank.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/account")
public class AccountResource {
    @Autowired
    private final AccountService accountService = new AccountServiceImpl();

    @PostMapping
    public AccountDTO create(final @Validated @RequestBody AccountDTO accountDTO) {
        accountService.create(accountDTO);
        return accountDTO;
    }
    @PutMapping
    public ResponseEntity<AccountDTO> put(final @Validated @RequestBody AccountDTO accountDTO) {
        accountService.update(accountDTO);
        return ResponseEntity.ok(accountDTO);
    }

    @GetMapping("/{id}")
    public AccountDTO get(final @PathVariable UUID id) {
        return accountService.read(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDTO> delete(final @PathVariable UUID id) {
        final AccountDTO accountDTO = get(id);
        accountService.delete(id);
        return ResponseEntity.ok(accountDTO);
    }

    @GetMapping(value = "/all")
    public List<AccountDTO> getAll() {
        return accountService.getAll();
    }
}
