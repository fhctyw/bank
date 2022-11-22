package bank.resource;


import bank.dto.CreditDTO;
import bank.service.CreditService;
import bank.service.impl.CreditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/credit")
public class CreditResource {

    @Autowired
    private final CreditService creditService = new CreditServiceImpl();

    @PostMapping
    public CreditDTO create(final @RequestBody CreditDTO creditDTO){
        final CreditDTO dto = creditService.create(creditDTO);
        return dto;
    }

    @PutMapping
    public CreditDTO put(final @RequestBody CreditDTO dto){
        return creditService.update(dto);
    }

    @GetMapping
    public CreditDTO get(final @RequestBody Long id){
        return creditService.read(id);
    }

    @DeleteMapping
    public CreditDTO delete(final @RequestBody Long id){
        return creditService.delete(id);
    }

}
