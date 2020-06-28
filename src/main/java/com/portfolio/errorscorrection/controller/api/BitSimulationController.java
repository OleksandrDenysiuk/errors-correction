package com.portfolio.errorscorrection.controller.api;

import com.portfolio.errorscorrection.model.Bit;
import com.portfolio.errorscorrection.service.BitService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BitSimulationController {
    private final BitService bitService;

    public BitSimulationController(BitService bitService) {
        this.bitService = bitService;
    }

    @PostMapping("/bits/fix")
    public @ResponseBody
    List<Bit> fixBit(List<Bit> bitList, int position){
        return bitService.fixBit(bitList, position);
    }

    @PostMapping("/bits/brake")
    public @ResponseBody
    List<Bit> brakeBit(List<Bit> bitList, List<Integer> positions){
        return bitService.brakeBit(bitList, positions);
    }

    @PostMapping("/bits/generate")
    public @ResponseBody
    List<Bit> generateBitList(String input){
        return bitService.generateBitList(input);
    }
}
