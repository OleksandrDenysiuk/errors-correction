package com.portfolio.errorscorrection.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntTo8BitStringTest {

    IntTo8BitString intTo8BitString;

    @BeforeEach
    void setUp() {
        intTo8BitString = new IntTo8BitString();
    }

    @Test
    void convert() {
       Integer bits = 40;

       String stringReturned = intTo8BitString.convert(bits);

       assertEquals("00101000", stringReturned);
    }
}