package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.dto.CrcDto;
import com.portfolio.errorscorrection.model.Bit;

import java.util.List;

public interface CrcSimulationService {
    List<Bit> computeCRC(List<Bit> bitList, CrcDto crcDto);
    int verify(List<Bit> bits,CrcDto crcDto);
}
