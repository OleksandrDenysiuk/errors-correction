package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Crc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class Crc32BitCalculateServiceImplTest {

    Crc32BitCalculateService crcService;

    @BeforeEach
    void setUp() {
        crcService = new Crc32BitCalculateServiceImpl();
    }

    @Test
    void compute() {
        Crc crc = new Crc();
        crc.setName("Crc16");
        crc.setBitLength(32);
        crc.setInputReflected(false);
        crc.setResultReflected(false);
        crc.setPolynomial(0x814141AB);
        crc.setInitialValue(0x0);
        crc.setFinalXorValue(0x0);

        String input = "hello";

        int result = crcService.compute(input.getBytes(), crc);

        assertEquals(-1334282108, result);
    }

    @Test
    void computeResultReflected() {
        Crc crc = new Crc();
        crc.setName("Crc16");
        crc.setBitLength(32);
        crc.setInputReflected(false);
        crc.setResultReflected(true);
        crc.setPolynomial(0x814141AB);
        crc.setInitialValue(0x0);
        crc.setFinalXorValue(0x0);

        String input = "hello";

        int result = crcService.compute(input.getBytes(), crc);

        assertEquals("211e1e0d", Integer.toHexString(result));
    }

    @Test
    void computeInputReflected() {
        Crc crc = new Crc();
        crc.setName("Crc16");
        crc.setBitLength(32);
        crc.setInputReflected(true);
        crc.setResultReflected(false);
        crc.setPolynomial(0x814141AB);
        crc.setInitialValue(0x0);
        crc.setFinalXorValue(0x0);

        String input = "hello";

        int result = crcService.compute(input.getBytes(), crc);

        assertEquals("ff8f00fc", Integer.toHexString(result));
    }

    @Test
    void computeVerificationPassed() {
        Crc crc = new Crc();
        crc.setName("Crc16");
        crc.setBitLength(32);
        crc.setInputReflected(true);
        crc.setResultReflected(false);
        crc.setPolynomial(0x814141AB);
        crc.setInitialValue(0x0);
        crc.setFinalXorValue(0x0);

        byte[] input = {0,1,1,0,1,0,0,0,1,1,0,1,1,1,0,0,1,0,0,1,1,1,0,0,1,0,1,0,0,0,1,1,1,1,0,0,1,0,0,1};

        int result = crcService.compute(input, crc);

        assertEquals("0", Integer.toHexString(result));
    }

    @Test
    void computeVerificationNotPassed() {
        Crc crc = new Crc();
        crc.setName("Crc16");
        crc.setBitLength(32);
        crc.setInputReflected(true);
        crc.setResultReflected(false);
        crc.setPolynomial(0x814141AB);
        crc.setInitialValue(0x0);
        crc.setFinalXorValue(0x0);

        byte[] input = {1,1,1,0,1,0,0,0,1,1,0,1,1,1,0,0,1,0,0,1,1,1,0,0,1,0,1,0,0,0,1,1,1,1,0,0,1,0,0,1};

        int result = crcService.compute(input, crc);

        assertNotEquals("0", Integer.toHexString(result));
    }
}