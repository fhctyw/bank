package bank.resource;

import bank.dto.TransactionDTO;
import bank.dto.TransferMoneyDTO;
import bank.service.TransactionService;
import bank.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionResource {
    @Autowired
    private final TransactionService transactionService = new TransactionServiceImpl();

    @PostMapping
    public TransactionDTO create(final @RequestBody TransactionDTO transactionDTO) {
        return transactionService.create(transactionDTO);
    }

    @PutMapping
    public TransactionDTO put(final @RequestBody TransactionDTO transactionDTO) {
        return transactionService.update(transactionDTO);
    }

    @GetMapping("/{id}")
    public TransactionDTO get(final @PathVariable Long id) {
        return transactionService.read(id);
    }

    @DeleteMapping("/{id}")
    public TransactionDTO delete(final @PathVariable Long id) {
        return transactionService.delete(id);
    }

    @GetMapping("/history")
    public List<TransactionDTO> getHistory() {
        return transactionService.readAll();
    }

    @GetMapping("/history-client/{id}")
    public List<TransactionDTO> getHistoryByClient(final @PathVariable Long id) {
        return transactionService.readClient(id);
    }
    @GetMapping("/all")
    public List<TransactionDTO> getAll() {
        return transactionService.getAll();
    }

    @PostMapping("/transfer-money")
    public TransactionDTO transfer(final @Validated @RequestBody TransferMoneyDTO transferMoneyDTO) {
        return transactionService.transfer(transferMoneyDTO);
    }
}
