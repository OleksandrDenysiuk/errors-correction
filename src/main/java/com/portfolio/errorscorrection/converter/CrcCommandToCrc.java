package com.portfolio.errorscorrection.converter;

import com.portfolio.errorscorrection.command.CrcCommand;
import com.portfolio.errorscorrection.model.Crc;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CrcCommandToCrc implements Converter<CrcCommand, Crc> {

    @Synchronized
    @Nullable
    @Override
    public Crc convert(CrcCommand crcCommand) {
        final Crc crc = new Crc();
        crc.setId(crcCommand.getId());
        crc.setName(crcCommand.getName());
        crc.setBitLength(Integer.parseInt(crcCommand.getBitLength()));
        crc.setInputReflected(crcCommand.isInputReflected());
        crc.setResultReflected(crcCommand.isResultReflected());
        crc.setPolynomial(Integer.parseInt(crcCommand.getPolynomial()));
        crc.setInitialValue(Integer.parseInt(crcCommand.getInitialValue()));
        crc.setFinalXorValue(Integer.parseInt(crcCommand.getFinalXorValue()));
        return crc;
    }
}
