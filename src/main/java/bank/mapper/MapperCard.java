package bank.mapper;


import bank.dto.CardDTO;
import bank.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class MapperCard {

    public CardDTO toDto(final Card card) {
        final CardDTO dto = new CardDTO();
        dto.setIdCard(card.getId());
        dto.setAmount(card.getAmount());
        dto.setCardNumber(card.getCardNumber());
        dto.setIdClient(card.getIdClient());

        return dto;
    }

    public Card toEntity(final CardDTO dto) {
        final Card card = new Card();
        card.setAmount(dto.getAmount());
        card.setCardNumber(dto.getCardNumber());
        card.setIdClient(dto.getIdClient());
        card.setIdClient(dto.getIdCard());

        return card;
    }

}
