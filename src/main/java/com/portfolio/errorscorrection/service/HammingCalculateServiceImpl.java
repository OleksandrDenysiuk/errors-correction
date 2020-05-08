package com.portfolio.errorscorrection.service;

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
    public int verification(List<Byte> message) {
        int pos = 1;

        int result = 0;

        for (int i = 0; i < message.size();){
            result += message.get(i);
            i = (int)Math.pow(2, pos) - 1;
            pos++;
        }

        if(result % 2 == 0){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public int calculatePositionBrokenBit(List<Byte> message) {
        List<Byte> oldHamming = new ArrayList<>();

        String messageWithoutHamming = "";

        for (int i = 0, pos = 0; i < message.size(); i++){
            if(pos > message.size()){
                break;
            }
            if((int)Math.pow(2, pos) - 1 == i){
                oldHamming.add(message.get(i));
                pos++;
            }else{
                messageWithoutHamming += message.get(i);
            }
        }

        int[] newHamming = calculation(messageWithoutHamming);

        int pos = 0;

        for (int i = 0; i < newHamming.length; i++){
            if(oldHamming.get(i) != newHamming[i]){
                pos += Math.pow(2, i);
            }
        }

        return pos;
    }
}
