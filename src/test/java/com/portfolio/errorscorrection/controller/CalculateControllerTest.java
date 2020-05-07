package com.portfolio.errorscorrection.controller;

import com.portfolio.errorscorrection.service.CrcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CalculateControllerTest {

    @Mock
    CrcService crcService;

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
                .andExpect(view().name("formInputData"));
    }
}