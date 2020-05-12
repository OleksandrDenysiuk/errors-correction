package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Crc;
import org.springframework.stereotype.Service;

@Service
public class Crc16BitCalculateServiceImpl implements Crc16BitCalculateService{

    private int[] calculateCrcTable16Bit(short polynomial) {

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

    @Override
    public int compute(byte[] bytes, Crc crc) {
        int[] crcTable16 = calculateCrcTable16Bit((short) crc.getPolynomial());

        int initialValue = crc.getInitialValue();

        for (byte b : bytes) {
            if(crc.isInputReflected()){
                b = (byte) reflect8(b);
            }
            /* XOR-in next input byte into MSB of crc, that's our new intermediate divident */
            byte pos = (byte)((initialValue >> 8) ^ b); /* equal: ((crc ^ (b << 8)) >> 8) */
            /* Shift out the MSB used for division per lookuptable and XOR with the remainder */
            initialValue = (short) ((initialValue << 8) ^ crcTable16[pos & 0xff]);
        }

        if(crc.isResultReflected()){
            initialValue = reflect16((short) initialValue);
        }

        return initialValue ^ crc.getFinalXorValue();
    }

    private short reflect16(short val) {
        short resVal = 0;

        for (int i = 0; i < 16; i++) {
            if ((val & (1 << i)) != 0) {
                resVal |= (short) (1 << (15 - i));
            }
        }

        return resVal;
    }

    private short reflect8(short val) {

        short resVal = 0;

        for (int i = 0; i < 8; i++) {
            if ((val & (1 << i)) != 0) {
                resVal |= (short) (1 << (7 - i));
            }
        }

        return resVal;
    }
}
