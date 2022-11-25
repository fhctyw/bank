package bank.resource;

import bank.dto.DepositDTO;
import bank.dto.MakeDepositDTO;
import bank.service.DepositService;
import bank.service.impl.DepositServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/deposit")
@EnableScheduling
public class DepositResource {
    @Autowired
    private final DepositService depositService = new DepositServiceImpl();

    @PostMapping
    public ResponseEntity<DepositDTO> create(final @RequestBody DepositDTO depositDTO) {
        depositService.create(depositDTO);
        return ResponseEntity.ok(depositDTO);
    }

    @PutMapping
    public ResponseEntity<DepositDTO> put(final @RequestBody DepositDTO depositDTO) {
        depositService.update(depositDTO);
        return ResponseEntity.ok(depositDTO);
    }

    @GetMapping("/{id}")
    public DepositDTO get(final @PathVariable Long id) {
        return depositService.read(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DepositDTO> delete(final @PathVariable Long id) {
        final DepositDTO depositDTO = get(id);
        depositService.delete(id);
        return ResponseEntity.ok(depositDTO);
    }

    @GetMapping(value = "/all")
    public List<DepositDTO> getAll() {
        return depositService.getAll();
    }

    @PostMapping(value = "/make-deposit")
    public DepositDTO makeDeposit(final @Validated @RequestBody MakeDepositDTO depositDTO) {
        return depositService.putDeposit(depositDTO);
    }

    @Scheduled(fixedRate = 1000 * 60)
    public void withdrawDeposit() {
        depositService.withdrawDeposit();
    }
}
