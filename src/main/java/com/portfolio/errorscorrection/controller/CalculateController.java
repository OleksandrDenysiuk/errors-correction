package com.portfolio.errorscorrection.controller;

import com.portfolio.errorscorrection.command.CrcCommand;
import com.portfolio.errorscorrection.converter.CrcCommandToCrc;
import com.portfolio.errorscorrection.service.CrcService;
import com.portfolio.errorscorrection.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculateController {

    private final CrcService crcService;
    private final MessageService messageService;
    private final CrcCommandToCrc crcCommandToCrc;

    public CalculateController(CrcService crcService, MessageService messageService, CrcCommandToCrc crcCommandToCrc) {
        this.crcService = crcService;
        this.messageService = messageService;
        this.crcCommandToCrc = crcCommandToCrc;
    }

    @GetMapping("/input")
    public String loadPage(Model model){
        model.addAttribute("listCrcCommands", crcService.findCrcCommandAll());
        model.addAttribute("crc", new CrcCommand());
        return "formInputData";
    }

    @PostMapping("/input/data/send")
    public String calculate(@RequestParam(name = "text") String text,
                            @ModelAttribute(name = "crc") CrcCommand crc,
                            Model model){
        System.out.println((int)(long)Long.decode("0x814141AB".toLowerCase()));
        long polynomial = 0x814141AB;
        System.out.println(polynomial);
        model.addAttribute("message", messageService.generate(text,crcCommandToCrc.convert(crc)));
        model.addAttribute("text", text);
        return "formBrakeBits";
    }
}
