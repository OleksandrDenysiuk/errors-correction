package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Bit;

import java.util.List;

public interface HammingCalculateService {
    List<Bit> setControlBits(List<Bit> bitList);

    List<Bit> deleteControlBits(List<Bit> bitList);

    int verify(List<Bit> bitList);
}
