package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Crc;
import org.springframework.stereotype.Service;

@Service
public class CrcCalculateServiceImpl implements CrcCalculateService {

    private final Crc16BitCalculateService crc16BitCalculateService;
    private final Crc32BitCalculateService crc32BitCalculateService;

    public CrcCalculateServiceImpl(Crc16BitCalculateService crc16BitCalculateService, Crc32BitCalculateService crc32BitCalculateService) {
        this.crc16BitCalculateService = crc16BitCalculateService;
        this.crc32BitCalculateService = crc32BitCalculateService;
    }

    @Override
    public int compute(byte[] bytes, Crc crc) {
        if (crc.getBitLength() == 16) {
            return crc16BitCalculateService.compute(bytes, crc);
        } else {
            return crc32BitCalculateService.compute(bytes,crc);
        }
    }
}



