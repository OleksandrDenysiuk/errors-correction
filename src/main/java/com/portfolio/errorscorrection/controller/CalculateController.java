package com.portfolio.errorscorrection.controller;

import com.portfolio.errorscorrection.service.CrcService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalculateController {

    private final CrcService crcService;

    public CalculateController(CrcService crcService) {
        this.crcService = crcService;
    }

    @GetMapping("/input")
    public String loadPage(Model model){
        model.addAttribute("listCrcCommands", crcService.findCrcCommandAll());
        return "formInputData";
    }
}
