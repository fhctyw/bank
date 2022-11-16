package bank.resource;

import bank.dto.ConsultantDTO;
import bank.service.ConsultantService;
import bank.service.impl.ConsultantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/consultant", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsultantResource {
    @Autowired
    private final ConsultantService consultantService = new ConsultantServiceImpl();

    @PostMapping
    public ResponseEntity<ConsultantDTO> create(final @RequestBody @Validated ConsultantDTO consultantDto) {
        return ResponseEntity.ok(consultantService.create(consultantDto));
    }

    @PutMapping
    public ResponseEntity<ConsultantDTO> put(final @RequestBody ConsultantDTO consultantDto) {
        return ResponseEntity.ok(consultantService.update(consultantDto));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ConsultantDTO>> getAll() {
        return ResponseEntity.ok(consultantService.getAll());
    }

    @GetMapping
    public ResponseEntity<ConsultantDTO> get(final @RequestBody Long id) {
        return ResponseEntity.ok(consultantService.read(id));
    }

    @DeleteMapping
    public ResponseEntity<ConsultantDTO> delete(final @RequestBody Long id) {
        return ResponseEntity.ok(consultantService.delete(id));
    }
}
