package com.portfolio.errorscorrection.controller;

import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.model.Message;
import com.portfolio.errorscorrection.service.MessageService;
import com.portfolio.errorscorrection.service.VerificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VerificationControllerTest {

    @Mock
    VerificationService verificationService;

    @Mock
    MessageService messageService;

    @InjectMocks
    VerificationController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void verificationPassed() throws Exception {
        int resultHamming = 0;
        int resultCrc = 0;

        when(verificationService.verifyHamming(any(Message.class)))
                .thenReturn(resultHamming);
        when(verificationService.verifyCrc(any(Message.class),any(Crc.class)))
                .thenReturn(resultCrc);

        mockMvc.perform(get("/verify/message").with(request -> {
            request.getSession().setAttribute("SESSION_BROKEN_MESSAGE",new Message());
            request.getSession().setAttribute("SESSION_CRC",new Crc());
            return request;
        }))
                .andExpect(model().attribute("resultHamming","Everything`s okay!"))
                .andExpect(model().attribute("resultCrc","Everything okay! Result: 0x0"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"));
    }

    @Test
    void verificationNotPassed() throws Exception {
        int resultHamming = 1;
        int resultCrc = 1;

        when(verificationService.verifyHamming(any(Message.class)))
                .thenReturn(resultHamming);
        when(verificationService.verifyCrc(any(Message.class),any(Crc.class)))
                .thenReturn(resultCrc);
        when(messageService.fixBit(any(Message.class),anyInt())).thenReturn(new Message());

        mockMvc.perform(get("/verify/message").with(request -> {
            request.getSession().setAttribute("SESSION_BROKEN_MESSAGE",new Message());
            request.getSession().setAttribute("SESSION_CRC",new Crc());
            return request;
        }))
                .andExpect(model().attribute("resultHamming","Something is wrong on position: 1"))
                .andExpect(model().attributeExists("hammingFixedMessage"))
                .andExpect(model().attribute("resultCrc","Something wrong! Result: 0x1"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"));
    }
}