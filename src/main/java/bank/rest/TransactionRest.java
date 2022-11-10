package bank.rest;

import bank.dto.AccountDTO;
import bank.dto.TransactionDTO;
import bank.service.AccountService;
import bank.service.TransactionService;
import bank.service.impl.AccountServiceImpl;
import bank.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/transaction")
public class TransactionRest {
    @Autowired
    private final TransactionService transactionService = new TransactionServiceImpl();

    @PostMapping
    public String create(final @RequestBody TransactionDTO transactionDTO) {
        transactionService.create(transactionDTO);
        return "All good";
    }
    @PutMapping
    public ResponseEntity<TransactionDTO> put(final @RequestBody TransactionDTO transactionDTO) {
        transactionService.update(transactionDTO);
        return ResponseEntity.ok(transactionDTO);
    }
    @GetMapping
    public TransactionDTO get(final @RequestBody Long id) {
        return transactionService.read(id);
    }

    @DeleteMapping
    public ResponseEntity<TransactionDTO> delete(final @RequestBody Long id) {
        final TransactionDTO transactionDTO = get(id);
        transactionService.delete(id);
        return ResponseEntity.ok(transactionDTO);
    }

}
