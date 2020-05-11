package com.portfolio.errorscorrection.controller;

import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.model.Message;
import com.portfolio.errorscorrection.service.CrcCalculateService;
import com.portfolio.errorscorrection.service.HammingCalculateService;
import com.portfolio.errorscorrection.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class VerificationController {

    private final CrcCalculateService crcCalculateService;
    private final HammingCalculateService hammingCalculateService;
    private final MessageService messageService;

    public VerificationController(CrcCalculateService crcCalculateService,
                                  HammingCalculateService hammingCalculateService,
                                  MessageService messageService) {
        this.crcCalculateService = crcCalculateService;
        this.hammingCalculateService = hammingCalculateService;
        this.messageService = messageService;
    }

    @GetMapping("/verify/message")
    public String verifyMessage(HttpServletRequest request, Model model) {
        Message message = (Message) request.getSession().getAttribute("SESSION_BROKEN_MESSAGE");
        Crc crc = (Crc) request.getSession().getAttribute("SESSION_CRC");

        model.addAttribute("brokenMessage", message);

        int result = hammingCalculateService.verification(message.getBitsString());

        if (result == 0) {
            model.addAttribute("resultHamming", "Everything`s okay!");
        } else {
            model.addAttribute("resultHamming", "Something is wrong on position: " + result);
            model.addAttribute("hammingFixedMessage", messageService.fixBit(message.getBits(), result));
        }

        List<Byte> bits = hammingCalculateService.deleteControlBits(message.getByteList());

        byte[] bytes = new byte[bits.size()];

        for(int i = 0; i < bytes.length; i++) {
            bytes[i] = bits.get(i);
        }

        int resultCrc = crcCalculateService.compute(bytes, crc);

        if (resultCrc == 0) {
            model.addAttribute("resultCrc", "Everything okay! Result: 0x" + Integer.toHexString(resultCrc));
        } else {
            model.addAttribute("resultCrc", "Something wrong! Result: 0x" + Integer.toHexString(resultCrc));
        }

        return "result";
    }
}
