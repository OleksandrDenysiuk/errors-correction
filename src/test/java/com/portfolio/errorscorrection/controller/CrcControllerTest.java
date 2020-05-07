package com.portfolio.errorscorrection.controller;

import com.portfolio.errorscorrection.command.CrcCommand;
import com.portfolio.errorscorrection.service.CrcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CrcControllerTest {

    @Mock
    CrcService crcService;

    @InjectMocks
    CrcController crcController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(crcController).build();
    }

    @Test
    void getCrcCommandsJson() throws Exception {

        CrcCommand crcCommand = new CrcCommand();

        crcCommand.setName("CrcCommandName");

        Set<CrcCommand> allCrc = new HashSet<>();
        allCrc.add(crcCommand);

        when(crcService.findCrcCommandAll()).thenReturn(allCrc);

        mockMvc.perform(get("/listCrcCommands")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(crcCommand.getName())));
    }

    @Test
    void getCrcCommandJson() throws Exception {
        CrcCommand crcCommand = new CrcCommand();

        crcCommand.setName("CrcCommandName");

        when(crcService.findCrcCommandById(anyLong())).thenReturn(crcCommand);

        mockMvc.perform(get("/CrcCommand/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(crcCommand.getName())));
    }
}