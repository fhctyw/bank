package bank.service.impl;

import bank.dto.DepositDTO;
import bank.entity.Deposit;
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

    @Override
    public void create(DepositDTO dto) {
        final Deposit deposit = mapperDeposit.toEntity(dto);
        depositRepository.add(deposit);
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

}
