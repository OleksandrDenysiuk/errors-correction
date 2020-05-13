package com.portfolio.errorscorrection.controller;

import com.portfolio.errorscorrection.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class BrakeBitsControllerTest {
    @Mock
    MessageService messageService;

    @InjectMocks
    BrakeBitsController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void brake() throws Exception {
        mockMvc.perform(post("/brake/bits")
                .param("first", "1")
                .param("second", "2")
                .param("third", "3")
                .param("fourth", "4")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/verify/message"));
    }
}