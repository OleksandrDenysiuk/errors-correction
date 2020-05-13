package com.portfolio.errorscorrection.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntTo32BitsStringTest {

    IntTo32BitsString converter;

    @BeforeEach
    void setUp() {
        converter = new IntTo32BitsString();
    }

    @Test
    void convert() {
        Integer bits = 1;

        String stringReturned = converter.convert(bits);

        assertEquals("00000000000000000000000000000001", stringReturned);
    }
}