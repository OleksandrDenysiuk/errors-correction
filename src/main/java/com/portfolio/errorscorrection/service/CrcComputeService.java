package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Crc;

public interface CrcComputeService {
    int compute(byte[] bytes, Crc crc);
}
