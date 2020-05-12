package com.portfolio.errorscorrection.service;

public interface HammingCalculateService {
    int countAmountControlBits(int length);
    int[] calculation(String bytes);
    int[] setPositionControlPoints(String bytes);
    int verification(String message);
}
