package bank.resource;


import bank.dto.*;
import bank.service.CreditService;
import bank.service.impl.CreditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/credit")
public class CreditResource {

    @Autowired
    private final CreditService creditService = new CreditServiceImpl();

    @PostMapping
    public CreditDTO create(final @RequestBody CreditDTO creditDTO) {
        final CreditDTO dto = creditService.create(creditDTO);
        return dto;
    }

    @PutMapping
    public CreditDTO put(final @RequestBody CreditDTO dto) {
        return creditService.update(dto);
    }

    @GetMapping
    public CreditDTO get(final @RequestBody Long id) {
        return creditService.read(id);
    }

    @DeleteMapping
    public CreditDTO delete(final @RequestBody Long id) {
        return creditService.delete(id);
    }

    @GetMapping(value = "/all")
    public List<CreditDTO> getAll() {
        return creditService.getAll();
    }

    @PostMapping(value = "/make-credit")
    public MakeCreditResponseDTO makeCredit(final @Validated @RequestBody MakeCreditDTO dto){
        return creditService.makeCredit(dto);
    }

    @PostMapping(value = "/pay-credit")
    public CardDTO payCredit(final @Validated @RequestBody PayCreditDTO creditDTO) {
        return creditService.payCredit(creditDTO);
    }
}
