package bank.resource;

import bank.dto.CardDTO;
import bank.entity.Card;
import bank.service.CardService;
import bank.service.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/card")
public class CardResource {

    @Autowired
    private final CardService cardService = new CardServiceImpl();

    @PostMapping
    public String create(final @RequestBody CardDTO cardDTO){
        cardService.create(cardDTO);
        return "All good";
    }

    @PutMapping
    public ResponseEntity<CardDTO> put(final @RequestBody CardDTO cardDTO) {
        cardService.update(cardDTO);
        return ResponseEntity.ok(cardDTO);
    }

    @GetMapping
    public CardDTO get(final @RequestBody Long id) {
        return cardService.read(id);
    }

    @DeleteMapping
    public ResponseEntity<CardDTO> delete(final @RequestBody Long id) {
        final CardDTO cardDTO = get(id);
        cardService.delete(id);
        return ResponseEntity.ok(cardDTO);
    }
}
