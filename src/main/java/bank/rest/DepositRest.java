package bank.rest;

import bank.dto.DepositDTO;
import bank.service.DepositService;
import bank.service.impl.DepositServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/deposit")
public class DepositRest {
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

    @GetMapping
    public DepositDTO get(final @RequestBody Long id) {
        return depositService.read(id);
    }

    @DeleteMapping
    public ResponseEntity<DepositDTO> delete(final @RequestBody Long id) {
        final DepositDTO depositDTO = get(id);
        depositService.delete(id);
        return ResponseEntity.ok(depositDTO);
    }
}
