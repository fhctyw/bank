package bank.resource;

import bank.dto.ClientDTO;
import bank.dto.RegisterDTO;
import bank.dto.RegisterResponseDTO;
import bank.service.ClientService;
import bank.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientResource {

    @Autowired
    private final ClientService clientService = new ClientServiceImpl();

    @PostMapping
    public ResponseEntity<ClientDTO> create(final @Validated @RequestBody ClientDTO clientDTO) {
        clientService.create(clientDTO);
        return ResponseEntity.ok(clientDTO);
    }

    @PutMapping
    public ResponseEntity<ClientDTO> put(final @Validated @RequestBody ClientDTO clientDTO) {
        clientService.update(clientDTO);
        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping("/{id}")
    public ClientDTO get(final @PathVariable Long id) {
        return clientService.read(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDTO> delete(final @PathVariable Long id) {
        final ClientDTO clientDTO = get(id);
        clientService.delete(id);
        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping(value = "/all")
    public List<ClientDTO> getAll() {
        return clientService.getAll();
    }

    @PostMapping(value = "/register")
    public RegisterResponseDTO register(final @Validated @RequestBody RegisterDTO registerDTO) {
        return clientService.register(registerDTO);
    }
}
