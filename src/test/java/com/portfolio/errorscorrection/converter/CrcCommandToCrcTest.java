package com.portfolio.errorscorrection.converter;

import com.portfolio.errorscorrection.command.CrcCommand;
import com.portfolio.errorscorrection.model.Crc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CrcCommandToCrcTest {

    public static final Long ID = 1L;
    public static final String NAME = "CRC_NAME";
    public static final String BIT_LENGTH = "8";
    public static final boolean INPUT_REFLECTED = true;
    public static final boolean RESULT_REFLECTED = false;
    public static final String POLYNOMIAL = "0x1021";
    public static final String INITIAL_VALUE = "0x0";
    public static final String FINAL_XOR_VALUE = "0x0";

    CrcCommandToCrc crcCommandToCrc;

    @BeforeEach
    void setUp() {
        crcCommandToCrc = new CrcCommandToCrc();
    }

    @Test
    void convert() {
        CrcCommand crcCommand = new CrcCommand();
        crcCommand.setId(ID);
        crcCommand.setName(NAME);
        crcCommand.setBitLength(BIT_LENGTH);
        crcCommand.setInputReflected(INPUT_REFLECTED);
        crcCommand.setResultReflected(RESULT_REFLECTED);
        crcCommand.setPolynomial(POLYNOMIAL);
        crcCommand.setInitialValue(INITIAL_VALUE);
        crcCommand.setFinalXorValue(FINAL_XOR_VALUE);

        Crc crc = crcCommandToCrc.convert(crcCommand);

        assertNotNull(crc);
        assertEquals(ID,crc.getId());
        assertEquals(NAME,crc.getName());
        assertEquals(Integer.parseInt(BIT_LENGTH),crc.getBitLength());
        assertEquals(INPUT_REFLECTED,crc.isInputReflected());
        assertEquals(RESULT_REFLECTED,crc.isResultReflected());
        assertEquals(Integer.decode(POLYNOMIAL.toLowerCase()),crc.getPolynomial());
        assertEquals(Integer.decode(INITIAL_VALUE.toLowerCase()),crc.getInitialValue());
        assertEquals(Integer.decode(FINAL_XOR_VALUE.toLowerCase()),crc.getFinalXorValue());
    }
}