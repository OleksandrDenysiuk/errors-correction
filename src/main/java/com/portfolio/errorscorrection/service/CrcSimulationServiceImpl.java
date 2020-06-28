package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.CRC.Crc16Calculator;
import com.portfolio.errorscorrection.CRC.Crc32Calculator;
import com.portfolio.errorscorrection.converter.CrcDtoToCrc;
import com.portfolio.errorscorrection.dto.CrcDto;
import com.portfolio.errorscorrection.model.Bit;
import com.portfolio.errorscorrection.model.Crc;

import java.util.ArrayList;
import java.util.List;

public class CrcSimulationServiceImpl implements CrcSimulationService{
    private final CrcDtoToCrc crcDtoToCrc;

    public CrcSimulationServiceImpl(CrcDtoToCrc crcDtoToCrc) {
        this.crcDtoToCrc = crcDtoToCrc;
    }

    @Override
    public List<Bit> computeCRC(List<Bit> bitList, CrcDto crcDto) {
        Crc customCrc = crcDtoToCrc.convert(crcDto);
        if(customCrc != null){
            int crcValue;
            List<Bit> crc = new ArrayList<>();
            byte[] bits = bitListToByteArray(bitList);

            if(customCrc.getBitLength() == 32){
                crcValue = Crc32Calculator.compute(bits,
                        customCrc.isInputReflected(),
                        customCrc.isResultReflected(),
                        customCrc.getPolynomial(),
                        customCrc.getInitialValue(),
                        customCrc.getFinalXorValue());

                String crcValue32Bit = String.format("%32s", Integer.toBinaryString(crcValue)).replace(' ', '0');

                for (char bit : crcValue32Bit.toCharArray()) {
                    crc.add(new Bit((byte) Character.getNumericValue(bit), "CRC"));
                }
            }else {
                crcValue = Crc16Calculator.compute(bits,
                        customCrc.isInputReflected(),
                        customCrc.isResultReflected(),
                        customCrc.getPolynomial(),
                        customCrc.getInitialValue(),
                        customCrc.getFinalXorValue());

                String crcValue16bit = String.format("%16s", Integer.toBinaryString(crcValue & 0xFFFF)).replace(' ', '0');

                for (char bit : crcValue16bit.toCharArray()) {
                    crc.add(new Bit((byte) Character.getNumericValue(bit), "CRC"));
                }
            }

            return crc;
        }else {
            throw new RuntimeException("Bad request");
        }
    }

    @Override
    public int verify(List<Bit> bitList, CrcDto crcDto) {
        Crc customCrc = crcDtoToCrc.convert(crcDto);
        if(customCrc != null){
            int crcValue;

            byte[] bits = bitListToByteArray(bitList);

            if(customCrc.getBitLength() == 32){
                crcValue = Crc32Calculator.compute(bits,
                        customCrc.isInputReflected(),
                        customCrc.isResultReflected(),
                        customCrc.getPolynomial(),
                        customCrc.getInitialValue(),
                        customCrc.getFinalXorValue());
            }else {
                crcValue = Crc16Calculator.compute(bits,
                        customCrc.isInputReflected(),
                        customCrc.isResultReflected(),
                        customCrc.getPolynomial(),
                        customCrc.getInitialValue(),
                        customCrc.getFinalXorValue());
            }

            return crcValue;
        }else {
            throw new RuntimeException("Bad request");
        }
    }

    private byte[] bitListToByteArray(List<Bit> bitList){
        byte[] byteArray = new byte[bitList.size()];

        for(int i = 0; i < byteArray.length; i++){
            byteArray[i] = (byte) bitList.get(i).getValue();
        }

        return byteArray;
    }
}
