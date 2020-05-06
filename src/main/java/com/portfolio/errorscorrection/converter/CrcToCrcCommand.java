package com.portfolio.errorscorrection.converter;

import com.portfolio.errorscorrection.command.CrcCommand;
import com.portfolio.errorscorrection.model.Crc;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CrcToCrcCommand implements Converter<Crc, CrcCommand> {

    @Synchronized
    @Nullable
    @Override
    public CrcCommand convert(Crc crc) {
        final CrcCommand crcCommand = new CrcCommand();
        crcCommand.setId(crc.getId());
        crcCommand.setName(crc.getName());
        crcCommand.setBitLength(String.valueOf(crc.getBitLength()));
        crcCommand.setInputReflected(crc.isInputReflected());
        crcCommand.setResultReflected(crc.isResultReflected());
        crcCommand.setPolynomial("0x" + Integer.toHexString(crc.getPolynomial()).toUpperCase());
        crcCommand.setInitialValue("0x" + Integer.toHexString(crc.getInitialValue()).toUpperCase());
        crcCommand.setFinalXorValue("0x" + Integer.toHexString(crc.getFinalXorValue()).toUpperCase());
        return crcCommand;
    }
}
