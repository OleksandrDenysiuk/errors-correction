package com.portfolio.errorscorrection.controller;

import com.portfolio.errorscorrection.model.Bit;
import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.model.Message;
import com.portfolio.errorscorrection.service.MessageService;
import com.portfolio.errorscorrection.service.VerificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VerificationController {

    private final VerificationService verificationService;
    private final MessageService messageService;

    public VerificationController(VerificationService verificationService, MessageService messageService) {
        this.verificationService = verificationService;
        this.messageService = messageService;
    }

    @GetMapping("/verify/message")
    public String verifyMessage(HttpServletRequest request, Model model) {
        Message message = (Message) request.getSession().getAttribute("SESSION_BROKEN_MESSAGE");
        Crc crc = (Crc) request.getSession().getAttribute("SESSION_CRC");

        List<Bit> brokenMessage = new ArrayList<>(message.getBits());

        model.addAttribute("brokenMessage", brokenMessage);

        int result = verificationService.verifyHamming(message);

        if (result == 0) {
            model.addAttribute("resultHamming", "Everything`s okay!");
        } else {
            model.addAttribute("resultHamming", "Something is wrong on position: " + result);
            model.addAttribute("hammingFixedMessage", messageService.fixBit(message, result));
        }

        int resultCrc = verificationService.verifyCrc(message, crc);

        if (resultCrc == 0) {
            model.addAttribute("resultCrc", "Everything okay! Result: 0x" + Integer.toHexString(resultCrc));
        } else {
            model.addAttribute("resultCrc", "Something wrong! Result: 0x" + Integer.toHexString(resultCrc));
        }

        return "result";
    }
}
