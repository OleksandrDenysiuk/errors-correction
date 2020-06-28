package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Bit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BitServiceImpl implements BitService{

    @Override
    public List<Bit> fixBit(List<Bit> bitList, int position) {
        Bit broken = bitList.get(position - 1);
        Bit fix = new Bit();
        fix.setValue(broken.getValue() ^ 1);
        fix.setStatus("FIXED");
        bitList.set(position - 1,  fix);
        return bitList;
    }

    @Override
    public List<Bit> brakeBit(List<Bit> bitList, List<Integer> positions) {
        for (int position : positions) {
            Bit currentBit = bitList.get(position - 1);
            currentBit.setValue((byte) (bitList.get(position - 1).getValue() ^ 1));
            currentBit.setStatus("BROKEN");
        }

        return bitList;
    }

    @Override
    public List<Bit> generateBitList(String text) {
        List<Bit> bits = new ArrayList<>();

        for (int byteToConvert : text.getBytes()) {
            String str = String.format("%8s", Integer.toBinaryString(byteToConvert & 0xFF)).replace(' ', '0');

            for (char bit : str.toCharArray()) {
                bits.add(new Bit((byte) Character.getNumericValue(bit), "INPUT"));
            }
        }

        return bits;
    }
}
