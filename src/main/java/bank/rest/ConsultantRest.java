package bank.rest;

import bank.dto.ConsultantDto;
import bank.service.ConsultantService;
import bank.service.impl.ConsultantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/consultant")
public class ConsultantRest {
    @Autowired
    private final ConsultantService consultantService = new ConsultantServiceImpl();

    @PostMapping("create")
    public String create(final @RequestBody ConsultantDto consultantDto) {
        consultantService.create(consultantDto);
        return "All good";
    }
}
