package com.portfolio.errorscorrection.controller;

import com.portfolio.errorscorrection.model.Message;
import com.portfolio.errorscorrection.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BrakeBitsController {

    private final MessageService messageService;

    public BrakeBitsController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/brake/bits")
    public String brake(@RequestParam(name = "first", required = false)String firstNumber,
                        @RequestParam(name = "second", required = false)String secondNumber,
                        @RequestParam(name = "third",required = false)String thirdNumber,
                        @RequestParam(name = "fourth",required = false)String fourthNumber,
                        HttpServletRequest request) {
        Message message = (Message) request.getSession().getAttribute("SESSION_MESSAGE");
        List<Integer> bits = new ArrayList<>();

        if(!firstNumber.equals("")){
            bits.add(Integer.valueOf(firstNumber));
        }
        if(!secondNumber.equals("")) {
            bits.add(Integer.valueOf(secondNumber));
        }
        if(!thirdNumber.equals("")) {
            bits.add(Integer.valueOf(thirdNumber));
        }
        if(!fourthNumber.equals("")) {
            bits.add(Integer.valueOf(fourthNumber));
        }

        request.getSession().setAttribute("SESSION_BROKEN_MESSAGE", messageService.brakeBits(bits,message));
        return "redirect:/verify/message";
    }
}
