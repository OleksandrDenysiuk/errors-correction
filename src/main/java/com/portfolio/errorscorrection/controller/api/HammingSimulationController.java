package com.portfolio.errorscorrection.controller.api;

import com.portfolio.errorscorrection.model.Bit;
import com.portfolio.errorscorrection.service.HammingService;
import com.portfolio.errorscorrection.wrapper.SimulationWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/simulation")
public class HammingSimulationController {
    private final HammingService hammingService;

    public HammingSimulationController(HammingService hammingService) {
        this.hammingService = hammingService;
    }

    @PostMapping("/hamming/setControlBits")
    public @ResponseBody
    List<Bit> setControlBits(@RequestBody SimulationWrapper simulationWrapper){
        return hammingService.setControlBits(simulationWrapper.getBits());
    }

    @PostMapping("/hamming/deleteControlBits")
    public @ResponseBody
    List<Bit> count(@RequestBody SimulationWrapper simulationWrapper){
        return hammingService.deleteControlBits(simulationWrapper.getBits());
    }

    @PostMapping("/hamming/verify")
    public @ResponseBody
    int  setPosition(@RequestBody SimulationWrapper simulationWrapper){
        return hammingService.verify(simulationWrapper.getBits());
    }
}
