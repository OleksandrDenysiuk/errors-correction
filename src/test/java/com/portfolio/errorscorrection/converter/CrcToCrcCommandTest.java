package com.portfolio.errorscorrection.converter;

import com.portfolio.errorscorrection.command.CrcCommand;
import com.portfolio.errorscorrection.model.Crc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CrcToCrcCommandTest {

    public static final Long ID = 1L;
    public static final String NAME = "CRC_NAME";
    public static final int BIT_LENGTH = 8;
    public static final boolean INPUT_REFLECTED = true;
    public static final boolean RESULT_REFLECTED = false;
    public static final int POLYNOMIAL = 0x1021;
    public static final int INITIAL_VALUE = 0x0;
    public static final int FINAL_XOR_VALUE = 0x0;

    CrcToCrcCommand crcToCrcCommand;

    @BeforeEach
    void setUp() {
        crcToCrcCommand = new CrcToCrcCommand();
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(crcToCrcCommand.convert(new Crc()));
    }

    @Test
    void convert() {
        Crc crc = new Crc();
        crc.setId(ID);
        crc.setName(NAME);
        crc.setBitLength(BIT_LENGTH);
        crc.setInputReflected(INPUT_REFLECTED);
        crc.setResultReflected(RESULT_REFLECTED);
        crc.setPolynomial(POLYNOMIAL);
        crc.setInitialValue(INITIAL_VALUE);
        crc.setFinalXorValue(FINAL_XOR_VALUE);

        CrcCommand command = crcToCrcCommand.convert(crc);

        assertNotNull(command);
        assertEquals(ID,command.getId());
        assertEquals(NAME,command.getName());
        assertEquals(String.valueOf(BIT_LENGTH),command.getBitLength());
        assertEquals(INPUT_REFLECTED,command.isInputReflected());
        assertEquals(RESULT_REFLECTED,command.isResultReflected());
        assertEquals("0x" + Integer.toHexString(POLYNOMIAL).toUpperCase(),command.getPolynomial());
        assertEquals("0x" + Integer.toHexString(INITIAL_VALUE).toUpperCase(),command.getInitialValue());
        assertEquals("0x" + Integer.toHexString(FINAL_XOR_VALUE).toUpperCase(),command.getFinalXorValue());
    }
}