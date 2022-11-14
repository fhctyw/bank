package bank.service;

import bank.dto.CardDTO;
import bank.entity.Card;

public interface CardService {
    void create (CardDTO dto);
    CardDTO read(Long id);
    void update(CardDTO dto);
    void delete(Long id);
}
