package com.portfolio.errorscorrection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String loadStartPage(){
        return "index";
    }

    @GetMapping("/crc")
    public String loadCrcPage(){
        return "simulation";
    }

    @GetMapping("/crc/result")
    public String loadCrcResultPage(){
        return "crcResult";
    }
}
