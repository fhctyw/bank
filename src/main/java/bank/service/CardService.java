package bank.service;

import bank.dto.CardDTO;
import bank.entity.Card;

public interface CardService {
    void create (CardDTO dto);
    Card read(int id);
    void update(CardDTO dto);
    void delete(int id);
}
