package bank.service.impl;

import bank.dto.CreditDTO;
import bank.entity.Credit;
import bank.mapper.MapperCredit;
import bank.repository.CreditRepository;
import bank.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditServiceImpl implements CreditService {
    @Autowired
    final MapperCredit mapperCredit = new MapperCredit();
    @Autowired
    final CreditRepository creditRepository = new CreditRepository();


    @Override
    public void getCredit(){

    }

    @Override
    public CreditDTO create(final CreditDTO dto){
        final Credit credit = mapperCredit.toEntity(dto);
        creditRepository.add(credit);
        final Credit creditInRepos = creditRepository.findById(creditRepository.getId());
        return mapperCredit.toDTO(creditInRepos);
    }

    @Override
    public CreditDTO read(final Long id){
        return mapperCredit.toDTO(creditRepository.findById(id));
    }

    @Override
    public CreditDTO update(final CreditDTO dto){
        creditRepository.setCredits(dto.getId(), mapperCredit.toEntity(dto));
        return mapperCredit.toDTO(creditRepository.findById(dto.getId()));
    }

    @Override
    public CreditDTO delete(final Long id){
        CreditDTO dto = mapperCredit.toDTO(creditRepository.findById(id));
        creditRepository.deleteCredit(id);
        return dto;
    }

    @Override
    public List<CreditDTO> getAll(){
        return creditRepository.getAll().stream().map(mapperCredit::toDTO).collect(Collectors.toList());
    }




}
