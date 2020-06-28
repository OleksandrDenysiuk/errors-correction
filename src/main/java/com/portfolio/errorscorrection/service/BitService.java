package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Bit;

import java.util.List;

public interface BitService {
    List<Bit> fixBit(List<Bit> bitList, int position);
    List<Bit> brakeBit(List<Bit> bitList, List<Integer> positions);
    List<Bit> generateBitList(String str);
}
