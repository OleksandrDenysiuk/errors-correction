package com.portfolio.errorscorrection.controller.api;

import com.portfolio.errorscorrection.dto.CrcDto;
import com.portfolio.errorscorrection.service.CrcService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CrcController {

    private final CrcService crcService;

    public CrcController(CrcService crcService) {
        this.crcService = crcService;
    }

    @GetMapping("/crc")
    public @ResponseBody
    List<CrcDto> getAll(){
        return crcService.getAll();
    }

    @GetMapping("/crc/{crcId}")
    public @ResponseBody
    CrcDto getOne(@PathVariable Long crcId){
        return crcService.getOneById(crcId);
    }

    @PostMapping("/crc")
    public @ResponseBody
    CrcDto create(@RequestBody CrcDto crcDto){
        return crcService.create(crcDto);
    }

    @PutMapping("/crc/{crcId}")
    public @ResponseBody
    CrcDto update(@RequestBody CrcDto crcDto,
                  @PathVariable Long crcId){
        crcDto.setId(crcId);
        return crcService.update(crcDto);
    }

    @DeleteMapping("/crc/{crcId}")
    public @ResponseBody
    void delete(@PathVariable Long crcId){
        crcService.delete(crcId);
    }
}
