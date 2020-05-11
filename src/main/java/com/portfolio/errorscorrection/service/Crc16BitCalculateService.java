package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Crc;

public interface Crc16BitCalculateService {
    int compute(byte[] bytes, Crc crcObject);
}
