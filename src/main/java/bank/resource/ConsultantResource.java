package bank.resource;

import bank.dto.ConsultantDTO;
import bank.service.ConsultantService;
import bank.service.impl.ConsultantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/consultant")
public class ConsultantResource {
    @Autowired
    private final ConsultantService consultantService = new ConsultantServiceImpl();

    @PostMapping
    public ResponseEntity<ConsultantDTO> create(final @RequestBody @Validated ConsultantDTO consultantDto) {
        consultantService.create(consultantDto);
        return ResponseEntity.ok(consultantDto);
    }

    @PutMapping
    public ResponseEntity<ConsultantDTO> put(final @RequestBody ConsultantDTO consultantDto) {
        consultantService.update(consultantDto);
        return ResponseEntity.ok(consultantDto);
    }

    @GetMapping
    public ConsultantDTO get(final @RequestBody Long id) {
        return consultantService.read(id);
    }

    @DeleteMapping
    public ResponseEntity<ConsultantDTO> delete(final @RequestBody Long id) {
        final ConsultantDTO consultantDTO = get(id);
        consultantService.delete(id);
        return ResponseEntity.ok(consultantDTO);
    }
}
