package com.portfolio.errorscorrection.service;

import java.util.List;

public interface HammingCalculateService {
    int countAmountControlBits(int length);
    int[] calculation(String bytes);
    int[] setPositionControlPoints(String bytes);
    int verification(String message);
    List<Byte> deleteControlBits(List<Byte> bitList);
}
