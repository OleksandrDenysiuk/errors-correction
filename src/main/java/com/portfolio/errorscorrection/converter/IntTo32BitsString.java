package com.portfolio.errorscorrection.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IntTo32BitsString implements Converter<Integer, String> {
    @Override
    public String convert(Integer number) {
        return String.format("%32s", Integer.toBinaryString(number)).replace(' ', '0');
    }
}
