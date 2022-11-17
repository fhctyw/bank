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
    public String create(final @RequestBody CreditDTO creditDTO){
        creditService.create(creditDTO);
        return "All good";
    }

    @PutMapping
    public ResponseEntity<CreditDTO> put(final @RequestBody CreditDTO dto){
        creditService.update(dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public CreditDTO get(final @RequestBody Long id){
        return creditService.read(id);
    }

    @DeleteMapping
    public ResponseEntity<CreditDTO> delete(final @RequestBody Long id){
        final CreditDTO creditDTO = get(id);
        creditService.delete(id);
        return ResponseEntity.ok(creditDTO);
    }

}
