package com.portfolio.errorscorrection.converter;

import com.portfolio.errorscorrection.dto.CrcDto;
import com.portfolio.errorscorrection.model.Crc;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CrcToCrcDto implements Converter<Crc, CrcDto> {

    @Synchronized
    @Nullable
    @Override
    public CrcDto convert(Crc crc) {
        final CrcDto crcDto = new CrcDto();
        crcDto.setId(crc.getId());
        crcDto.setName(crc.getName());
        crcDto.setBitLength(String.valueOf(crc.getBitLength()));
        crcDto.setInputReflected(crc.isInputReflected());
        crcDto.setResultReflected(crc.isResultReflected());
        crcDto.setPolynomial("0x" + Integer.toHexString(crc.getPolynomial()).toUpperCase());
        crcDto.setInitialValue("0x" + Integer.toHexString(crc.getInitialValue()).toUpperCase());
        crcDto.setFinalXorValue("0x" + Integer.toHexString(crc.getFinalXorValue()).toUpperCase());
        return crcDto;
    }
}
