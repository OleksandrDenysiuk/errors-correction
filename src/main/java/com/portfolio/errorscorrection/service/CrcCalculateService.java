package com.portfolio.errorscorrection.service;

public interface CrcCalculateService {

    int[] calculateCrcTable();
    int compute(byte[] bytes);
}
