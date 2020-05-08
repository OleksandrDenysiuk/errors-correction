package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.converter.IntTo16BitString;
import com.portfolio.errorscorrection.converter.IntTo8BitString;
import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.model.Message;
import org.springframework.stereotype.Service;

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

        for(int bytes : text.getBytes()){
            message.setContent(intTo8BitString.convert(bytes));
            message.addBits(message.getContent());
        }

        message.setCrc(intTo16BitString.convert(crcCalculateService.compute(text.getBytes())));
        message.addBits(message.getCrc());

        message.setControlBits(hammingCalculateService.calculation(message.getContent()+message.getCrc()));

        int posCP = 0;

        for (int bit : message.getControlBits()) {
            message.getMessage().add((int) Math.pow(2, posCP) - 1, (byte)bit);
            posCP++;
        }

        return message;
    }
}
