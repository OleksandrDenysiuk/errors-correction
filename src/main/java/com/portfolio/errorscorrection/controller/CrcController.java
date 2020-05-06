package com.portfolio.errorscorrection.controller;

import com.portfolio.errorscorrection.command.CrcCommand;
import com.portfolio.errorscorrection.service.CrcService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class CrcController {

    private final CrcService crcService;

    public CrcController(CrcService crcService) {
        this.crcService = crcService;
    }

    @GetMapping("/listCrcCommands")
    public @ResponseBody Set<CrcCommand> getCrcCommandsJson(){
        return crcService.findCrcCommandAll();
    }

    @GetMapping("/CrcCommand/{id}")
    public @ResponseBody CrcCommand getCrcCommandJson(@PathVariable(name = "id") String crcCommandId){
        return crcService.findCrcCommandById(Long.valueOf(crcCommandId));
    }
}
