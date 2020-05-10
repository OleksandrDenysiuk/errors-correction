package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Bit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HammingCalculateServiceImpl implements HammingCalculateService {
    @Override
    public int countAmountControlBits(int length) {
        int amount = 0;

        while (Math.pow(2, amount) <= length) {
            amount++;
        }

        return amount;
    }

    @Override
    public int[] calculation(String bytes) {
        int[] ar =  setPositionControlPoints(bytes);
        int r = countAmountControlBits(bytes.length());

        int[] bits = new int[r];

        for (int i = 0; i < r; i++) {
            int x = (int) Math.pow(2, i);
            int amount = 0;
            for (int j = x - 1; j < ar.length; j += (x + x)) {
                for (int k = j; k < j + x; k++) {
                    if (k > ar.length - 1) {
                        break;
                    }
                    if (ar[k] == 1) {
                        amount++;
                    }
                }
            }
            if (amount % 2 != 0) {
                bits[i] = 1;
                ar[x - 1] = 1;
            }
        }

        return bits;
    }

    @Override
    public int[] setPositionControlPoints(String bytes) {
        int amount =  countAmountControlBits(bytes.length());

        int[] hamming = new int[bytes.length() + amount];

        int posCP = 0;

        int j = 0;

        for (int i = 0; i < hamming.length; i++) {
            if (Math.pow(2, posCP) - 1 == i) {
                posCP++;
            } else {
                hamming[i] = bytes.charAt(j) - '0';
                j++;
            }
        }

        return hamming;
    }

    @Override
    public int verification(String bits) {

        char[] bitsArray = bits.toCharArray();

        int[] oldControlBits = new int[countAmountControlBits(bits.length())];


        for (int i = 0, pos = 1; i < bitsArray.length; i = (int)Math.pow(2, pos) - 1, pos++){
            oldControlBits[pos - 1] = Character.getNumericValue(bitsArray[i]);
        }

        String deletedControlBits = "";

        for (int i = 0, pos = 0; i < bitsArray.length; i++){
            if(i == (int)Math.pow(2, pos) - 1){
                pos++;
            }else {
                deletedControlBits += bitsArray[i];
            }
        }

        int[] newControlBits = calculation(deletedControlBits);

        int position = 0;

        for(int i = 0; i < oldControlBits.length; i++){
            if(oldControlBits[i] != newControlBits[i]){
                position += (i + 1);
            }
        }

        return position;
    }

    @Override
    public List<Bit> deleteControlBits(List<Bit> bitList) {
        List<Bit> massageCopy = new ArrayList<>(bitList);

        int posCP = countAmountControlBits(bitList.size()) - 1;

        for (int i = 0; i < countAmountControlBits(bitList.size()); i++) {
            massageCopy.remove((int) Math.pow(2, posCP) - 1);
            posCP--;
        }

        return massageCopy;
    }
}
