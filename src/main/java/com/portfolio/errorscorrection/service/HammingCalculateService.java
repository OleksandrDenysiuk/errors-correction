package com.portfolio.errorscorrection.service;

import java.util.List;

public interface HammingCalculateService {
    int countAmountControlBits(int length);
    int[] calculation(String bytes);
    int[] setPositionControlPoints(String bytes);
    int verification(List<Byte> message);
    int calculatePositionBrokenBit(List<Byte> message);
}
