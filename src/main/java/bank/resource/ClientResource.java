package bank.resource;

import bank.dto.ClientDTO;
import bank.service.ClientService;
import bank.util.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/client")
public class ClientResource {

    @Autowired
    private final ClientService clientService = new ClientServiceImpl();

    @PostMapping
    public ResponseEntity<ClientDTO> create(final @RequestBody ClientDTO consultantDto) {
        clientService.create(consultantDto);
        return ResponseEntity.ok(consultantDto);
    }

    @PutMapping
    public ResponseEntity<ClientDTO> put(final @RequestBody ClientDTO consultantDto) {
        clientService.update(consultantDto);
        return ResponseEntity.ok(consultantDto);
    }

    @GetMapping
    public ClientDTO get(final @RequestBody Long id) {
        return clientService.read(id);
    }

    @DeleteMapping
    public ResponseEntity<ClientDTO> delete(final @RequestBody Long id) {
        final ClientDTO consultantDTO = get(id);
        clientService.delete(id);
        return ResponseEntity.ok(consultantDTO);
    }
}
