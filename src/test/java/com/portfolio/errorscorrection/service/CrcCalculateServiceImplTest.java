package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Crc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CrcCalculateServiceImplTest {

    @Mock
    Crc16BitCalculateService crc16BitCalculateService;

    @Mock
    Crc32BitCalculateService crc32BitCalculateService;

    CrcCalculateService crcCalculateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        crcCalculateService = new CrcCalculateServiceImpl(crc16BitCalculateService, crc32BitCalculateService);
    }

    @Test
    void compute16Bit() {

        int crcResult = 1000;

        Crc crc = new Crc();
        crc.setBitLength(16);

        byte[] bytes = {1,2};

        when(crc16BitCalculateService.compute(any(),any(Crc.class))).thenReturn(crcResult);

        assertEquals(crcResult, crcCalculateService.compute(bytes,crc));

        verify(crc16BitCalculateService,times(1)).compute(any(),any(Crc.class));
        verify(crc32BitCalculateService,times(0)).compute(any(),any(Crc.class));
    }

    @Test
    void compute32Bit() {

        int crcResult = 1000;

        Crc crc = new Crc();
        crc.setBitLength(32);

        byte[] bytes = {1,2};

        when(crc32BitCalculateService.compute(any(),any(Crc.class))).thenReturn(crcResult);

        assertEquals(crcResult, crcCalculateService.compute(bytes,crc));

        verify(crc16BitCalculateService,times(0)).compute(any(),any(Crc.class));
        verify(crc32BitCalculateService,times(1)).compute(any(),any(Crc.class));
    }


}