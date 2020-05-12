package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.converter.IntTo16BitString;
import com.portfolio.errorscorrection.converter.IntTo32BitsString;
import com.portfolio.errorscorrection.converter.IntTo8BitString;
import com.portfolio.errorscorrection.model.Bit;
import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final CrcCalculateService crcCalculateService;
    private final HammingCalculateService hammingCalculateService;
    private final IntTo8BitString intTo8BitString;
    private final IntTo16BitString intTo16BitString;
    private final IntTo32BitsString intTo32BitsString;

    public MessageServiceImpl(CrcCalculateService crcCalculateService, HammingCalculateService hammingCalculateService, IntTo8BitString intTo8BitString, IntTo16BitString intTo16BitString, IntTo32BitsString intTo32BitsString) {
        this.crcCalculateService = crcCalculateService;
        this.hammingCalculateService = hammingCalculateService;
        this.intTo8BitString = intTo8BitString;
        this.intTo16BitString = intTo16BitString;
        this.intTo32BitsString = intTo32BitsString;
    }

    @Override
    public Message generate(String text, Crc crc) {
        Message message = new Message();

        for (int bytes : text.getBytes()) {
            message.addBitsWithStatus(intTo8BitString.convert(bytes), "INPUT");
        }

        int crcResult = crcCalculateService.compute(text.getBytes(), crc);
        if(crc.getBitLength() == 16){
            message.addBitsWithStatus(intTo16BitString.convert(crcResult), "CRC");
        }else {
            message.addBitsWithStatus(intTo32BitsString.convert(crcResult), "CRC");
        }


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
            message.brakeBit(position);
        }

        return message;
    }

    @Override
    public Message fixBit(Message message, int position) {
            message.fixBit(position);
        return message;
    }

    @Override
    public List<Byte> deleteControlBits(List<Byte> bitList) {

        List<Byte> massageCopy = new ArrayList<>(bitList);
        int amount = hammingCalculateService.countAmountControlBits(bitList.size());

        int posCP = amount - 1;

        for (int i = 0; i < amount; i++) {
            massageCopy.remove((int) Math.pow(2, posCP) - 1);
            posCP--;
        }

        return massageCopy;
    }
}
