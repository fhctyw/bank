package bank.resource;

import bank.dto.ConsultantDTO;
import bank.service.ConsultantService;
import bank.service.impl.ConsultantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/consultant", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsultantResource {
    @Autowired
    private final ConsultantService consultantService = new ConsultantServiceImpl();

    @PostMapping
    public ConsultantDTO create(final @Validated @RequestBody ConsultantDTO consultantDto) {
        return consultantService.create(consultantDto);
    }

    @PutMapping
    public ConsultantDTO put(final @Validated @RequestBody ConsultantDTO consultantDto) {
        return consultantService.update(consultantDto);
    }

    @GetMapping("/{id}")
    public ConsultantDTO get(final @PathVariable Long id) {
        return consultantService.read(id);
    }

    @DeleteMapping("/{id}")
    public ConsultantDTO delete(final @PathVariable Long id) {
        return consultantService.delete(id);
    }

    @GetMapping(value = "/all")
    public List<ConsultantDTO> getAll() {
        return consultantService.getAll();
    }
}
