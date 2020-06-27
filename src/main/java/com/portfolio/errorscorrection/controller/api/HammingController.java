package com.portfolio.errorscorrection.controller.api;

import com.portfolio.errorscorrection.service.HammingCalculateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HammingController {
    private final HammingCalculateService hammingCalculateService;

    public HammingController(HammingCalculateService hammingCalculateService) {
        this.hammingCalculateService = hammingCalculateService;
    }

    @PostMapping("/hamming/control_bits/amount")
    public @ResponseBody
    int countAmount(@RequestParam int messageLength){
        return hammingCalculateService.countAmountControlBits(messageLength);
    }

    @PostMapping("/hamming/control_bits/calculate")
    public @ResponseBody
    int[] count(@RequestParam String bytes){
        return hammingCalculateService.calculation(bytes);
    }

    @PostMapping("/hamming/control_bits/set_position")
    public @ResponseBody
    int[] setPosition(@RequestParam String bytes){
        return hammingCalculateService.setPositionControlPoints(bytes);
    }

    @PostMapping("/hamming/control_bits/verify")
    public @ResponseBody
    int verify(@RequestParam String bytes){
        return hammingCalculateService.verification(bytes);
    }
}
