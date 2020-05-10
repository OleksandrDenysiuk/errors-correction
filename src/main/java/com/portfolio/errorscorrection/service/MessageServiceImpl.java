package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.converter.IntTo16BitString;
import com.portfolio.errorscorrection.converter.IntTo8BitString;
import com.portfolio.errorscorrection.model.Bit;
import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final CrcCalculateService crcCalculateService;
    private final HammingCalculateService hammingCalculateService;
    private final IntTo8BitString intTo8BitString;
    private final IntTo16BitString intTo16BitString;

    public MessageServiceImpl(CrcCalculateService crcCalculateService, HammingCalculateService hammingCalculateService, IntTo8BitString intTo8BitString, IntTo16BitString intTo16BitString) {
        this.crcCalculateService = crcCalculateService;
        this.hammingCalculateService = hammingCalculateService;
        this.intTo8BitString = intTo8BitString;
        this.intTo16BitString = intTo16BitString;
    }

    @Override
    public Message generate(String text, Crc crc) {
        Message message = new Message();

        for (int bytes : text.getBytes()) {
            message.addBitsWithStatus(intTo8BitString.convert(bytes), "INPUT");
        }

        message.addBitsWithStatus(intTo16BitString.convert(crcCalculateService.compute(text.getBytes())), "CRC");

        int posCP = 0;

        for (int bit : hammingCalculateService.calculation(message.getBitsString())) {
            message.getBits().add((int) Math.pow(2, posCP) - 1, new Bit(bit, "CONTROL"));
            posCP++;
        }

        return message;
    }

    @Override
    public Message brakeBits(List<Integer> bitPositionList,
                             Message message) {

        for (int position : bitPositionList) {
            Bit currentBit = message.getBits().get(position - 1);
            currentBit.setBit((byte) (message.getBits().get(position - 1).getBit() ^ 1));
            currentBit.setStatus("BROKEN");
        }

        return message;
    }

    @Override
    public Message fixBit(Message message, int position) {
        Bit currentBit = message.getBits().get(position - 1);
        currentBit.setBit((byte) (message.getBits().get(position - 1).getBit() ^ 1));
        currentBit.setStatus("FIXED");
        return message;
    }
}
