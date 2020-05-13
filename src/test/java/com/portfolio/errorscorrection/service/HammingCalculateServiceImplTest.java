package com.portfolio.errorscorrection.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HammingCalculateServiceImplTest {
    HammingCalculateService service;

    @BeforeEach
    void setUp() {
        service = new HammingCalculateServiceImpl();
    }

    @Test
    void countAmountControlBits() {
        int length = 5;
        assertEquals(3,service.countAmountControlBits(length));
    }

    @Test
    void calculation() {
        int[] result = new int[]{1,1,1};

        assertEquals(Arrays.toString(result),Arrays.toString(service.calculation("0001")));
    }

    @Test
    void setPositionControlPoints() {
        int[] result = new int[]{0,0,0,0,0,0,1};
        assertEquals(Arrays.toString(result),Arrays.toString(service.setPositionControlPoints("0001")));
    }

    @Test
    void verificationPassed() {
        String input = "1101001";

        assertEquals(0,service.verification(input));
    }

    @Test
    void verificationNotPassed() {
        String input = "1001001";

        assertEquals(2,service.verification(input));
    }
}