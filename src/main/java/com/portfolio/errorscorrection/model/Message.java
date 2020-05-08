package com.portfolio.errorscorrection.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    String content;
    String crc;
    int[] controlBits;

    List<Byte> message = new ArrayList<>();

    public void addBits(String bits){
        for (char bit : bits.toCharArray()) {
            message.add((byte)Character.getNumericValue(bit));
        }
    }

}
