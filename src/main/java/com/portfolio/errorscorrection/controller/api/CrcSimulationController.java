package com.portfolio.errorscorrection.controller.api;

import com.portfolio.errorscorrection.model.Bit;
import com.portfolio.errorscorrection.service.CrcService;
import com.portfolio.errorscorrection.service.CrcSimulationService;
import com.portfolio.errorscorrection.wrapper.SimulationWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/simulation")
public class CrcSimulationController {
    private final CrcSimulationService crcSimulationService;
    private final CrcService crcService;

    public CrcSimulationController(CrcSimulationService crcSimulationService, CrcService crcService) {
        this.crcSimulationService = crcSimulationService;
        this.crcService = crcService;
    }

    @PostMapping("/crc/custom")
    public @ResponseBody
    List<Bit> compute(@RequestBody SimulationWrapper wrapper){
        return crcSimulationService.computeCRC(wrapper.getBits(), wrapper.getCrcDto());
    }

    @PostMapping("/crc/custom/verify")
    public @ResponseBody
    int verify(@RequestBody SimulationWrapper wrapper){
        return crcSimulationService.verify(wrapper.getBits(), wrapper.getCrcDto());
    }
}
