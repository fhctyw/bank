package bank.mapper;

import bank.dto.CardDTO;
import bank.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class MapperCard {

    public CardDTO toDto(final Card card) {
        final CardDTO dto = new CardDTO();
        dto.setId(card.getId());
        dto.setIdAccount(card.getIdAccount());
        dto.setAmount(card.getAmount());
        dto.setCardNumber(card.getCardNumber());
        dto.setIdClient(card.getIdClient());

        return dto;
    }

    public Card toEntity(final CardDTO dto) {
        final Card card = new Card();
        card.setAmount(dto.getAmount());
        card.setIdAccount(dto.getIdAccount());
        card.setCardNumber(dto.getCardNumber());
        card.setIdClient(dto.getIdClient());
        card.setId(dto.getId());

        return card;
    }

}
