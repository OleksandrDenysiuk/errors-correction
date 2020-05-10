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

    List<Bit> bits = new ArrayList<>();

    public void addBitsWithStatus(String bits, String status) {
        for (char bit : bits.toCharArray()) {
            this.bits.add(new Bit((byte) Character.getNumericValue(bit), status));
        }
    }

    public String getBitsString(){
        String bitsString = "";
        for (Bit bit : bits){
            bitsString += bit.getBit();
        }
        return bitsString;
    }

    public List<Byte> getByteList(){
        List<Byte> byteList = new ArrayList<>();
        bits.iterator().forEachRemaining(bit -> byteList.add((byte) bit.getBit()));
        return byteList;
    }

    public byte[] getByteArray(){

        byte[] bytes = new byte[bits.size()];

        for(int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) bits.get(i).getBit();
        }

        return bytes;
    }
}
