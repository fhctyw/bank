package bank.service;

import bank.dto.CardDTO;

import java.util.List;

public interface CardService {
    CardDTO create(CardDTO dto);

    CardDTO read(Long id);

    CardDTO update(CardDTO dto);

    CardDTO delete(Long id);

    List<CardDTO> getAll();
    CardDTO getByNumber(final Long number);
}
