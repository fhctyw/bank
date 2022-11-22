package bank.service.impl;

import bank.dto.CardDTO;
import bank.entity.Card;
import bank.mapper.MapperCard;
import bank.repository.CardRepository;
import bank.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    final MapperCard mapperCard = new MapperCard();
    @Autowired
    final CardRepository cardRepository = new CardRepository();

    @Override
    public CardDTO create(final CardDTO dto) {
        Card card = mapperCard.toEntity(dto);
        cardRepository.add(card);
        card = cardRepository.findById(cardRepository.getId());
        return mapperCard.toDto(card);
    }

    @Override
    public CardDTO read(final Long id) {
        return mapperCard.toDto(cardRepository.findById(id));
    }


    @Override
    public CardDTO update(final CardDTO dto) {
        cardRepository.setCard(dto.getId(), mapperCard.toEntity(dto));
        return mapperCard.toDto(cardRepository.findById(dto.getId()));
    }

    @Override
    public CardDTO delete(final Long id) {
        final Card card = cardRepository.findById(id);
        cardRepository.deleteCard(id);
        return mapperCard.toDto(card);
    }

    @Override
    public List<CardDTO> getAll() {
        return cardRepository.getCards().stream().map(mapperCard::toDto).collect(Collectors.toList());
    }

    @Override
    public CardDTO getByNumber(final Long number) {
        return mapperCard.toDto(cardRepository.findByNumber(number));
    }
}
