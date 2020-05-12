package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.converter.IntTo16BitString;
import com.portfolio.errorscorrection.converter.IntTo32BitsString;
import com.portfolio.errorscorrection.converter.IntTo8BitString;
import com.portfolio.errorscorrection.model.Bit;
import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class MessageServiceImplTest {

    @Mock
    CrcCalculateService crcCalculateService;
    @Mock
    HammingCalculateService hammingCalculateService;
    @Mock
    IntTo8BitString intTo8BitString;
    @Mock
    IntTo16BitString intTo16BitString;
    @Mock
    IntTo32BitsString intTo32BitsString;

    MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        messageService = new MessageServiceImpl(crcCalculateService,hammingCalculateService,
                intTo8BitString,intTo16BitString,intTo32BitsString);
    }

    @Test
    void generate() {
        String bitString = "01";
        int[] controlBits = new int[]{1,1};

        when(intTo8BitString.convert(anyInt())).thenReturn(bitString);
        when(intTo16BitString.convert(anyInt())).thenReturn(bitString);
        when(intTo32BitsString.convert(anyInt())).thenReturn(bitString);
        when(hammingCalculateService.calculation(anyString())).thenReturn(controlBits);
        Message message = messageService.generate("hello",new Crc());
        assertEquals("11010101010101", message.getBitsString());
    }

    @Test
    void brakeBits() {
        String result = "01";
        List<Integer> positions = new ArrayList<>();
        positions.add(1);
        Message message = new Message();
        Bit bit1 = new Bit();
        bit1.setBit(1);
        bit1.setStatus("NORMAL");
        Bit bit2 = new Bit();
        bit2.setBit(1);
        message.setBits(Arrays.asList(bit1,bit2));

        Message brokenMessage = messageService.brakeBits(positions,message);

        assertEquals(result,brokenMessage.getBitsString());
        assertEquals("BROKEN",brokenMessage.getBits().get(0).getStatus());
    }

    @Test
    void fixBit() {
        String result = "01";
        Message message = new Message();
        Bit bit1 = new Bit();
        bit1.setBit(1);
        bit1.setStatus("NORMAL");
        Bit bit2 = new Bit();
        bit2.setBit(1);
        message.setBits(Arrays.asList(bit1,bit2));

        Message brokenMessage = messageService.fixBit(message, 1);

        assertEquals(result,brokenMessage.getBitsString());
        assertEquals("FIXED",brokenMessage.getBits().get(0).getStatus());
    }

    @Test
    void deleteControlBits() {
        List<Byte> resultList = new ArrayList<>();
        resultList.add((byte)0);
        resultList.add((byte)0);
        List<Byte> bitList = new ArrayList<>();
        bitList.add((byte)1);
        bitList.add((byte)1);
        bitList.add((byte)0);
        bitList.add((byte)1);
        bitList.add((byte)0);

        when(hammingCalculateService.countAmountControlBits(anyInt())).thenReturn(3);

        List<Byte> returnedList = messageService.deleteControlBits(bitList);

        assertEquals(resultList.toString(),resultList.toString());
    }
}