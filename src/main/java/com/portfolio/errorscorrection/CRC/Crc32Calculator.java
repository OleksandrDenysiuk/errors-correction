package com.portfolio.errorscorrection.CRC;

public class Crc32Calculator {
    private static int[] calculateCrcTable(int polynomial) {

        int[] crcTable32 = new int[256];

        for (int divident = 0; divident < 256; divident++) /* iterate over all possible input byte values 0 - 255 */ {
            int curByte = divident << 24; /* move divident byte into MSB of 32Bit CRC */
            for (byte bit = 0; bit < 8; bit++) {
                if ((curByte & 0x80000000) != 0) {
                    curByte <<= 1;
                    curByte ^= polynomial;
                } else {
                    curByte <<= 1;
                }
            }

            crcTable32[divident] = curByte;
        }

        return crcTable32;
    }

    public static int compute(byte[] bytes,
                              boolean inputReflected,
                              boolean resultReflected,
                              int polynomial,
                              int initialValue,
                              int finalXorValue) {
        int[] crcTable = calculateCrcTable(polynomial);

        for (int b : bytes) {
            if (inputReflected) {
                b = reflect8((short)b);
            }
            /* XOR-in next input byte into MSB of crc and get this MSB, that's our new intermediate divident */
            byte pos = (byte)((initialValue ^ (b << 24)) >> 24);
            /* Shift out the MSB used for division per lookuptable and XOR with the remainder */
            initialValue = ((initialValue << 8) ^ (crcTable[pos & 0xff]));
        }

        if (resultReflected) {
            initialValue = reflect32(initialValue);
        }
        return initialValue ^ finalXorValue;
    }

    private static int reflect32(int val) {
        int resVal = 0;

        for (int i = 0; i < 32; i++) {
            if ((val & (1 << i)) != 0) {
                resVal |= (1 << (31 - i));
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
