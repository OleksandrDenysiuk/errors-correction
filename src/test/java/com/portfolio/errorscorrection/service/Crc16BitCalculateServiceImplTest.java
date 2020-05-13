package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Crc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class Crc16BitCalculateServiceImplTest {

    Crc16BitCalculateService crcService;

    @BeforeEach
    void setUp() {
        crcService = new Crc16BitCalculateServiceImpl();
    }

    @Test
    void compute() {
        Crc crc = new Crc();
        crc.setName("Crc16");
        crc.setBitLength(16);
        crc.setInputReflected(false);
        crc.setResultReflected(false);
        crc.setPolynomial(0x1021);
        crc.setInitialValue(0x0);
        crc.setFinalXorValue(0x0);

        String input = "hello";

        int result = crcService.compute(input.getBytes(), crc);

        assertEquals(-15518, result);
    }

    @Test
    void computeResultReflected() {
        Crc crc = new Crc();
        crc.setName("Crc16");
        crc.setBitLength(16);
        crc.setInputReflected(false);
        crc.setResultReflected(true);
        crc.setPolynomial(0x1021);
        crc.setInitialValue(0x0);
        crc.setFinalXorValue(0x0);

        String input = "hello";

        int result = crcService.compute(input.getBytes(), crc);

        assertEquals("46c3", Integer.toHexString(result));
    }

    @Test
    void computeInputReflected() {
        Crc crc = new Crc();
        crc.setName("Crc16");
        crc.setBitLength(16);
        crc.setInputReflected(true);
        crc.setResultReflected(false);
        crc.setPolynomial(0x1021);
        crc.setInitialValue(0x0);
        crc.setFinalXorValue(0x0);

        String input = "hello";

        int result = crcService.compute(input.getBytes(), crc);

        assertEquals("53df", Integer.toHexString(result));
    }

    @Test
    void computeVerificationPassed() {
        Crc crc = new Crc();
        crc.setName("Crc16");
        crc.setBitLength(16);
        crc.setInputReflected(true);
        crc.setResultReflected(false);
        crc.setPolynomial(0x1021);
        crc.setInitialValue(0x0);
        crc.setFinalXorValue(0x0);

        byte[] input = {0,0,1,1,0,0,0,1,0,0,1,0,0,1,1,0,0,1,1,1,0,0,1,0};

        int result = crcService.compute(input, crc);

        assertEquals("0", Integer.toHexString(result));
    }

    @Test
    void computeVerificationNotPassed() {
        Crc crc = new Crc();
        crc.setName("Crc16");
        crc.setBitLength(16);
        crc.setInputReflected(true);
        crc.setResultReflected(false);
        crc.setPolynomial(0x1021);
        crc.setInitialValue(0x0);
        crc.setFinalXorValue(0x0);

        byte[] input = {1,0,1,1,0,0,0,1,0,0,1,0,0,1,1,0,0,1,1,1,0,0,1,0};

        int result = crcService.compute(input, crc);

        assertNotEquals("0", Integer.toHexString(result));
    }
}