package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class VerificationServiceImplTest {
    @Mock
    CrcCalculateService crcCalculateService;
    @Mock
    HammingCalculateService hammingCalculateService;
    @Mock
    MessageService messageService;

    VerificationService verificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        verificationService = new VerificationServiceImpl(crcCalculateService,
                hammingCalculateService, messageService);
    }

    @Test
    void verifyHamming() {
        int result = 0;
        Message message = new Message();

        when(hammingCalculateService.verification(anyString())).thenReturn(0);

        assertEquals(result,verificationService.verifyHamming(message));
        verify(hammingCalculateService,times(1)).verification(anyString());

    }

    @Test
    void verifyCrc() {
        List<Byte> bits = new ArrayList<>();
        bits.add((byte)1);

        int result = 1;

        when(messageService.deleteControlBits(anyList())).thenReturn(bits);
        when(crcCalculateService.compute(any(),any(Crc.class))).thenReturn(result);

        assertEquals(result,verificationService.verifyCrc(new Message(), new Crc()));
    }
}