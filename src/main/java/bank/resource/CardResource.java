package bank.resource;

import bank.dto.CardDTO;
import bank.service.CardService;
import bank.util.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/card")
public class CardResource {

    @Autowired
    private final CardService cardService = new CardServiceImpl();

    @PostMapping("create")
    public String create(final @RequestBody CardDTO cardDTO){
        cardService.create(cardDTO);
        return "All good";
    }
}
