package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Crc;

public interface Crc32BitCalculateService {
    int compute(byte[] bytes, Crc crc);
}
