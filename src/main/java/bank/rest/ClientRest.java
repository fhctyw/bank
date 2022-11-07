package bank.rest;

import bank.dto.ClientDTO;
import bank.service.ClientService;
import bank.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/client")
public class ClientRest {

    @Autowired
    private final ClientService clientService = new ClientServiceImpl();

    @PostMapping("create")
    public String create(final @RequestBody ClientDTO clientDto) {
        clientService.create(clientDto);
        return "All good";
    }
}
