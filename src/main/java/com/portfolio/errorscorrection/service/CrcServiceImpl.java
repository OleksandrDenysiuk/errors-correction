package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.converter.CrcDtoToCrc;
import com.portfolio.errorscorrection.converter.CrcToCrcDto;
import com.portfolio.errorscorrection.dto.CrcDto;
import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.repository.CrcRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CrcServiceImpl implements CrcService {

    private final CrcRepository crcRepository;
    private final CrcToCrcDto crcToCrcDto;
    private final CrcDtoToCrc crcDtoToCrc;

    public CrcServiceImpl(CrcRepository crcRepository, CrcToCrcDto crcToCrcDto, CrcDtoToCrc crcDtoToCrc) {
        this.crcRepository = crcRepository;
        this.crcToCrcDto = crcToCrcDto;
        this.crcDtoToCrc = crcDtoToCrc;
    }

    @Override
    public CrcDto getOneById(Long id) {
        Optional<Crc> crcOptional = crcRepository.findById(id);
        if (crcOptional.isEmpty()) {
            throw new RuntimeException("crc not found by id: " + id.toString());
        }
        return crcToCrcDto.convert(crcOptional.get());
    }

    @Override
    public List<CrcDto> getAll() {
        return crcRepository.findAll().stream()
                .map(crcToCrcDto::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CrcDto create(CrcDto crcDto) {
        Optional<Crc> optionalCrc = crcRepository.findAll().stream()
                .filter(crc -> crc.equals(crcDtoToCrc.convert(crcDto)))
                .findFirst();
        if (optionalCrc.isEmpty()) {
            Crc crc = crcDtoToCrc.convert(crcDto);
            return crcToCrcDto.convert(crcRepository.save(crc));
        } else {
            return crcToCrcDto.convert(optionalCrc.get());
        }
    }

    @Override
    public CrcDto update(CrcDto crcDto) {
        //todo: compare crc with crcDTO
        return new CrcDto();
    }


    @Override
    public void delete(Long crcId) {
        Optional<Crc> optionalCrc = crcRepository.findAll().stream()
                .filter(crc -> crc.getId().equals(crcId))
                .findFirst();
        if(optionalCrc.isPresent()){
            crcRepository.delete(optionalCrc.get());
        }else{
            throw  new RuntimeException("Crc not found");
        }
    }
}
