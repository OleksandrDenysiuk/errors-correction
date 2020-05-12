package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationServiceImpl implements VerificationService{
    private final CrcCalculateService crcCalculateService;
    private final HammingCalculateService hammingCalculateService;
    private final MessageService messageService;

    public VerificationServiceImpl(CrcCalculateService crcCalculateService, HammingCalculateService hammingCalculateService, MessageService messageService) {
        this.crcCalculateService = crcCalculateService;
        this.hammingCalculateService = hammingCalculateService;
        this.messageService = messageService;
    }

    @Override
    public int verifyHamming(Message message) {
        return hammingCalculateService.verification(message.getBitsString());
    }

    @Override
    public int verifyCrc(Message message, Crc crc) {
        List<Byte> bits = messageService.deleteControlBits(message.getByteList());

        byte[] bytes = new byte[bits.size()];

        for(int i = 0; i < bytes.length; i++) {
            bytes[i] = bits.get(i);
        }

        return crcCalculateService.compute(bytes, crc);
    }
}
