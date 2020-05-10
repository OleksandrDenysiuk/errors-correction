package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Bit;

import java.util.List;

public interface HammingCalculateService {
    int countAmountControlBits(int length);
    int[] calculation(String bytes);
    int[] setPositionControlPoints(String bytes);
    int verification(String message);
    List<Bit> deleteControlBits(List<Bit> bitList);
}
