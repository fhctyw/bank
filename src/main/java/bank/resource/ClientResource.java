package bank.resource;

import bank.dto.ClientDTO;
import bank.service.ClientService;
import bank.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientResource {

    @Autowired
    private final ClientService clientService = new ClientServiceImpl();

    @PostMapping
    public ResponseEntity<ClientDTO> create(final @RequestBody ClientDTO clientDTO) {
        clientService.create(clientDTO);
        return ResponseEntity.ok(clientDTO);
    }

    @PutMapping
    public ResponseEntity<ClientDTO> put(final @RequestBody ClientDTO clientDTO) {
        clientService.update(clientDTO);
        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping
    public ClientDTO get(final @RequestBody Long id) {
        return clientService.read(id);
    }

    @DeleteMapping
    public ResponseEntity<ClientDTO> delete(final @RequestBody Long id) {
        final ClientDTO clientDTO = get(id);
        clientService.delete(id);
        return ResponseEntity.ok(clientDTO);
    }
    @GetMapping(value = "/all")
    public List<ClientDTO> getAll() {
        return clientService.getAll();
    }
}
