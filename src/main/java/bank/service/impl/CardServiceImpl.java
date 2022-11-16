package bank.service.impl;

import bank.dto.CardDTO;
import bank.entity.Card;
import bank.mapper.MapperCard;
import bank.repository.CardRepository;
import bank.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    final MapperCard mapperCard = new MapperCard();
    @Autowired
    final CardRepository cardRepository = new CardRepository();

    @Override
    public void create(final CardDTO dto) {
        final Card card = mapperCard.toEntity(dto);
        cardRepository.add(card);
    }

    @Override
    public CardDTO read(final Long id) {
        return mapperCard.toDto(cardRepository.findById(id));
    }


    @Override
    public void update(final CardDTO dto) {
        cardRepository.setCard(dto.getId(),mapperCard.toEntity(dto));
    }

    @Override
    public void delete(final Long id) {
        cardRepository.deleteCard(id);
    }


}
