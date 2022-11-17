package bank.service;

import bank.dto.CardDTO;

import java.util.List;

public interface CardService {
    void create(CardDTO dto);

    CardDTO read(Long id);

    void update(CardDTO dto);

    void delete(Long id);

    List<CardDTO> getAll();
    CardDTO getByNumber(final Long number);
}
