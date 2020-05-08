package com.portfolio.errorscorrection.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IntTo16BitString implements Converter<Integer, String> {
    @Override
    public String convert(Integer number) {
        return String.format("%16s", Integer.toBinaryString(number & 0xFFFF)).replace(' ', '0');
    }
}
