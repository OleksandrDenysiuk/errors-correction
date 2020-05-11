package com.portfolio.errorscorrection.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntTo16BitStringTest {

    IntTo16BitString intTo16BitString;

    @BeforeEach
    void setUp() {
        intTo16BitString = new IntTo16BitString();
    }

    @Test
    void convert() {
        Integer bits = 907060870;

        String stringReturned = intTo16BitString.convert(bits);

        assertEquals("1010011010000110", stringReturned);
    }
}