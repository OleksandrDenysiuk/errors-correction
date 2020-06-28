package com.portfolio.errorscorrection.CRC;

public class Crc16Calculator {
    private static int[] calculateCrcTable(short polynomial) {

        int[] crcTable16 = new int[256];

        for (short divident = 0; divident < 256; divident++) /* iterate over all possible input byte values 0 - 255 */
        {
            short curByte = (short)(divident << 8); /* move divident byte into MSB of 16Bit CRC */
            for (byte bit = 0; bit < 8; bit++)
            {
                if ((curByte & 0x8000) != 0)
                {
                    curByte <<= 1;
                    curByte ^= polynomial;
                }
                else
                {
                    curByte <<= 1;
                }
            }

            crcTable16[divident] = curByte;
        }
        return crcTable16;
    }

    public static int compute(byte[] bytes,
                              boolean inputReflected,
                              boolean resultReflected,
                              int polynomial,
                              int initialValue,
                              int finalXorValue) {
        int[] crcTable16 = calculateCrcTable((short) polynomial);

        for (byte b : bytes) {
            if(inputReflected){
                b = (byte) reflect8(b);
            }
            /* XOR-in next input byte into MSB of crc, that's our new intermediate divident */
            byte pos = (byte)((initialValue >> 8) ^ b); /* equal: ((crc ^ (b << 8)) >> 8) */
            /* Shift out the MSB used for division per lookuptable and XOR with the remainder */
            initialValue = (short) ((initialValue << 8) ^ crcTable16[pos & 0xff]);
        }

        if(resultReflected){
            initialValue = reflect16((short) initialValue);
        }

        return initialValue ^ finalXorValue;
    }

    private static short reflect16(short val) {
        short resVal = 0;

        for (int i = 0; i < 16; i++) {
            if ((val & (1 << i)) != 0) {
                resVal |= (short) (1 << (15 - i));
            }
        }

        return resVal;
    }

    private static short reflect8(short val) {

        short resVal = 0;

        for (int i = 0; i < 8; i++) {
            if ((val & (1 << i)) != 0) {
                resVal |= (short) (1 << (7 - i));
            }
        }

        return resVal;
    }
}
