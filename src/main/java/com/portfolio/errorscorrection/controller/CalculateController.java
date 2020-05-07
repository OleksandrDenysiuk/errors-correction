package com.portfolio.errorscorrection.controller;

import com.portfolio.errorscorrection.command.CrcCommand;
import com.portfolio.errorscorrection.service.CrcService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalculateController {

    private final CrcService crcService;

    public CalculateController(CrcService crcService) {
        this.crcService = crcService;
    }

    @GetMapping("/input")
    public String loadPage(Model model){
        model.addAttribute("listCrcCommands", crcService.findCrcCommandAll());
        model.addAttribute("crc", new CrcCommand());
        return "formInputData";
    }

    @PostMapping("/input/data/send")
    public String calculate(@ModelAttribute(name = "crc") CrcCommand crc){
        return "";
    }
}
