package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Bit;
import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.model.Message;

import java.util.List;

public interface MessageService {
    Message generate(String text, Crc crc);
    Message brakeBits(List<Integer> bits, Message message);
    List<Bit> fixBit(List<Bit> bitList, int position);
}
