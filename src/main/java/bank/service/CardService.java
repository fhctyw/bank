package bank.service;

import bank.dto.CardDTO;
import bank.entity.Card;

public interface CardService {
    void create (CardDTO dto);
    Card read(Long id);

    CardDTO read(Long id);

    void update(CardDTO dto);
    void delete(int id);
}
