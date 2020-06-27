package com.portfolio.errorscorrection.converter;

import com.portfolio.errorscorrection.dto.CrcDto;
import com.portfolio.errorscorrection.model.Crc;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CrcDtoToCrc implements Converter<CrcDto, Crc> {

    @Synchronized
    @Nullable
    @Override
    public Crc convert(CrcDto crcDto) {
        final Crc crc = new Crc();
        crc.setId(crcDto.getId());
        crc.setName(crcDto.getName());
        crc.setBitLength(Integer.parseInt(crcDto.getBitLength()));
        crc.setInputReflected(crcDto.isInputReflected());
        crc.setResultReflected(crcDto.isResultReflected());
        crc.setPolynomial((int)(long)Long.decode(crcDto.getPolynomial().toLowerCase()));
        crc.setInitialValue((int)(long)Long.decode(crcDto.getInitialValue().toLowerCase()));
        crc.setFinalXorValue((int)(long)Long.decode(crcDto.getFinalXorValue().toLowerCase()));
        return crc;
    }
}
