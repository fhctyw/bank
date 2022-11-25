package bank.service.impl;

import bank.dto.*;
import bank.entity.Client;
import bank.entity.Credit;
import bank.exception.InvalidMakeCreditException;
import bank.exception.InvalidPayCreditException;
import bank.mapper.MapperCredit;
import bank.repository.CreditRepository;
import bank.service.CreditService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreditServiceImpl implements CreditService {
    @Autowired
    final MapperCredit mapperCredit;
    @Autowired
    final CreditRepository creditRepository ;

    @Autowired
    final CardServiceImpl cardService;

    @Autowired
    final AccountServiceImpl accountService;

    public CreditServiceImpl() {
        mapperCredit = new MapperCredit();
        creditRepository = new CreditRepository();
        cardService = new CardServiceImpl();
        accountService = new AccountServiceImpl();
    }

    @Override
    public void getCredit() {

    }

    @Override
    public CreditDTO create(final CreditDTO dto) {
        final Credit credit = mapperCredit.toEntity(dto);
        creditRepository.add(credit);
        final Credit creditInRepos = creditRepository.findById(creditRepository.getId());
        return mapperCredit.toDTO(creditInRepos);
    }

    @Override
    public CreditDTO read(final Long id) {
        return mapperCredit.toDTO(creditRepository.findById(id));
    }

    @Override
    public CreditDTO update(final CreditDTO dto) {
        creditRepository.setCredits(dto.getId(), mapperCredit.toEntity(dto));
        return dto;
    }

    @Override
    public CreditDTO delete(final Long id) {
        final CreditDTO creditDTO = mapperCredit.toDTO(creditRepository.findById(id));
        creditRepository.deleteCredit(id);
        return creditDTO;
    }

    @Override
    public List<CreditDTO> getAll() {
        return creditRepository.getAll().stream().map(mapperCredit::toDTO).collect(Collectors.toList());
    }

    private boolean validateCredit(final Long idClient) {
        return creditRepository.getAll().stream().filter(credit -> credit.getIdClient().equals(idClient)).count() < 1L;
    }

    @Override
    public MakeCreditResponseDTO makeCredit(final MakeCreditDTO makeCreditDTO) {
        final CardDTO cardDTO = cardService.read(makeCreditDTO.getIdCard());
        final AccountDTO accountDTO = accountService.read(cardDTO.getIdAccount());

        if (!validateCredit(cardDTO.getIdClient())) {
            throw new InvalidMakeCreditException("You have one credit, STOP TAKE CREDIT!!!");
        }
        accountDTO.setAmount(accountDTO.getAmount().add(makeCreditDTO.getAmount()));
        cardDTO.setAmount(cardDTO.getAmount().add(makeCreditDTO.getAmount()));

        cardService.update(cardDTO);
        accountService.update(accountDTO);

        final CreditDTO creditDTO = new CreditDTO();
        creditDTO.setCardNumber(cardDTO.getCardNumber());
        creditDTO.setIdClient(cardDTO.getIdClient());
        creditDTO.setAmount(makeCreditDTO.getAmount());
        creditDTO.setPercent(makeCreditDTO.getPercent());
        creditDTO.setCreateTime(LocalDateTime.now());
        creditDTO.setPayTime(makeCreditDTO.getPayTime());

        return new MakeCreditResponseDTO(create(creditDTO), cardDTO);
    }


    @Override
    public CardDTO payCredit(final PayCreditDTO dto) {
        final CreditDTO creditDTO = read(dto.getCreditId());
        if (creditDTO.getPayTime().isAfter(LocalDateTime.now())) {
            throw new InvalidPayCreditException("Paying time is over", "Kolektoru is coming");
        }
        CardDTO cardDTO = cardService.getByNumber(dto.getCardNumber());
        final AccountDTO accountDTO = accountService.read(cardDTO.getIdAccount());
        final BigDecimal creditAmount = creditDTO.getAmount()
                .multiply(BigDecimal.valueOf(creditDTO.getPercent()))
                .add(creditDTO.getAmount());
        if (creditAmount.compareTo(cardDTO.getAmount()) > 0) {
            throw new InvalidPayCreditException("Not enough money for paying credit", "Kolektoru is coming");
        }

        cardDTO.setAmount(cardDTO.getAmount().subtract(creditAmount));
        accountDTO.setAmount(accountDTO.getAmount().subtract(creditAmount));

        accountService.update(accountDTO);
        cardDTO = cardService.update(cardDTO);

        delete(creditDTO.getId());

        return cardDTO;
    }

}
