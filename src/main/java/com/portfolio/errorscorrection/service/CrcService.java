package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.dto.CrcDto;

import java.util.List;

public interface CrcService {
    CrcDto getOneById(Long id);
    List<CrcDto> getAll();
    CrcDto create(CrcDto crcDto);
    CrcDto update(CrcDto crcDto);
    void delete(Long crcId);
}
