package com.portfolio.errorscorrection.controller;

import com.portfolio.errorscorrection.command.CrcCommand;
import com.portfolio.errorscorrection.converter.CrcCommandToCrc;
import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.model.Message;
import com.portfolio.errorscorrection.service.CrcService;
import com.portfolio.errorscorrection.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CalculateControllerTest {

    @Mock
    CrcService crcService;

    @Mock
    MessageService messageService;

    @Mock
    CrcCommandToCrc crcCommandToCrc;

    MockMvc mockMvc;

    @InjectMocks
    CalculateController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void loadFormPage() throws Exception {
        mockMvc.perform(get("/input"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("listCrcCommands"))
                .andExpect(model().attributeExists("crc"))
                .andExpect(view().name("formInputData"));
    }

    @Test
    void calculate() throws Exception {
        when(crcCommandToCrc.convert(any(CrcCommand.class))).thenReturn(new Crc());
        when(messageService.generate(anyString(),any(Crc.class))).thenReturn(new Message());


        mockMvc.perform(post("/input/data/send")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("text","hello")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attribute("text", "hello"))
                .andExpect(view().name("formBrakeBits"));
    }
}