package bank.service;

import bank.dto.CardDTO;
import bank.dto.MakeCardDTO;
import bank.dto.MakeCardResponseDTO;

import java.util.List;

public interface CardService {
    CardDTO create(CardDTO dto);

    MakeCardResponseDTO createCard(MakeCardDTO dto)  ;

    CardDTO read(Long id);

    CardDTO update(CardDTO dto);

    CardDTO delete(Long id);

    List<CardDTO> getAll();
    CardDTO getByNumber(final Long number);
}
