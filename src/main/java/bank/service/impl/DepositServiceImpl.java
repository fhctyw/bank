package bank.service.impl;

import bank.dto.AccountDTO;
import bank.dto.CardDTO;
import bank.dto.DepositDTO;
import bank.dto.MakeDepositDTO;
import bank.entity.Deposit;
import bank.exception.InvalidDepositException;
import bank.mapper.MapperDeposit;
import bank.repository.DepositRepository;
import bank.service.DepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    @Autowired
    final MapperDeposit mapperDeposit = new MapperDeposit();

    @Autowired
    final DepositRepository depositRepository = new DepositRepository();

    @Autowired
    final CardServiceImpl cardService = new CardServiceImpl();

    @Autowired
    final AccountServiceImpl accountService = new AccountServiceImpl();

    @Override
    public DepositDTO create(DepositDTO dto) {
        Deposit deposit = mapperDeposit.toEntity(dto);
        depositRepository.add(deposit);
        deposit = depositRepository.findById(depositRepository.getId());
        return mapperDeposit.toDTO(deposit);
    }

    @Override
    public DepositDTO read(final Long id) {
        final Deposit deposit = depositRepository.get(id);
        final DepositDTO dto = mapperDeposit.toDTO(deposit);
        return dto;
    }

    @Override
    public void update(final DepositDTO dto) {
        depositRepository.setDeposit(dto.getId(), mapperDeposit.toEntity(dto));
    }

    @Override
    public void delete(final Long id) {
        depositRepository.deleteDeposit(id);
    }

    @Override
    public List<DepositDTO> getAll() {
        return depositRepository.getDeposits().stream().map(mapperDeposit::toDTO).collect(Collectors.toList());
    }

    @Override
    public DepositDTO putDeposit(final MakeDepositDTO dto) {
        final CardDTO cardDTO = cardService.getByNumber(dto.getCardNumber());
        if (cardDTO.getAmount().compareTo(dto.getAmount()) < 0) {
            throw new InvalidDepositException("Not enough money for deposit");
        }
        final AccountDTO accountDTO = accountService.read(cardDTO.getIdAccount());

        cardDTO.setAmount(cardDTO.getAmount().subtract(dto.getAmount()));
        accountDTO.setAmount(accountDTO.getAmount().subtract(dto.getAmount()));

        accountService.update(accountDTO);
        cardService.update(cardDTO);

        DepositDTO depositDTO = new DepositDTO();
        depositDTO.setAmount(dto.getAmount());
        depositDTO.setPutTime(dto.getPutTime());
        depositDTO.setWithdrawTime(dto.getWithdrawTime());
        depositDTO.setPercentage(dto.getPercentage());
        depositDTO.setCardId(cardDTO.getId());

        depositDTO = create(depositDTO);

        return depositDTO;
    }
}
