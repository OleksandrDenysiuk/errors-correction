package com.portfolio.errorscorrection.controller.api;

import com.portfolio.errorscorrection.converter.CrcDtoToCrc;
import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.service.CrcComputeService;
import com.portfolio.errorscorrection.service.CrcService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CrcComputeController {
    private final CrcComputeService crcComputeService;
    private final CrcService crcService;
    private final CrcDtoToCrc crcDtoToCrc;

    public CrcComputeController(CrcComputeService crcComputeService, CrcService crcService, CrcDtoToCrc crcDtoToCrc) {
        this.crcComputeService = crcComputeService;
        this.crcService = crcService;
        this.crcDtoToCrc = crcDtoToCrc;
    }

    @GetMapping("/crc/{crcId}/compute")
    public @ResponseBody
    int compute(@RequestParam("message") String message,
                @PathVariable Long crcId) {
        Crc crc = crcDtoToCrc.convert(crcService.getOneById(crcId));
        return crcComputeService.compute(message.getBytes(), crc);
    }
}
