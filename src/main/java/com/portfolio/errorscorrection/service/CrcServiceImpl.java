package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.command.CrcCommand;
import com.portfolio.errorscorrection.converter.CrcToCrcCommand;
import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.repository.CrcRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CrcServiceImpl implements CrcService{

    private final CrcRepository crcRepository;
    private final CrcToCrcCommand crcToCrcCommand;

    public CrcServiceImpl(CrcRepository crcRepository, CrcToCrcCommand crcToCrcCommand) {
        this.crcRepository = crcRepository;
        this.crcToCrcCommand = crcToCrcCommand;
    }

    @Override
    public Crc findById(Long id) {
        Optional<Crc> crcOptional = crcRepository.findById(id);
        if(crcOptional.isEmpty()){
            throw new RuntimeException("crc not found by id: " + id.toString());
        }
        return crcOptional.get();
    }

    @Override
    public Set<CrcCommand> findCrcCommandAll() {
        Set<CrcCommand> crcCommandSet = new HashSet<>();
        crcRepository.findAll().iterator().forEachRemaining(crc -> {
            crcCommandSet.add(crcToCrcCommand.convert(crc));
        });
        return crcCommandSet;
    }

    @Override
    public CrcCommand findCrcCommandById(Long id) {
        return crcToCrcCommand.convert(findById(id));
    }
}
