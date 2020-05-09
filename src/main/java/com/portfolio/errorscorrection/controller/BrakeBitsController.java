package com.portfolio.errorscorrection.controller;

import com.portfolio.errorscorrection.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BrakeBitsController {
    @PostMapping("/brake/bits/{message}")
    public String brake(@PathVariable("message") Message message) {

        return "resultPage";
    }
}
