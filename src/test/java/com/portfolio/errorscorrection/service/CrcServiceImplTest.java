package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.command.CrcCommand;
import com.portfolio.errorscorrection.converter.CrcToCrcCommand;
import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.repository.CrcRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CrcServiceImplTest {

    @Mock
    CrcRepository crcRepository;

    @Mock
    @Autowired
    CrcToCrcCommand crcToCrcCommand;

    CrcServiceImpl crcService;

    public static final Long ID = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        crcService = new CrcServiceImpl(crcRepository, crcToCrcCommand);
    }

    @Test
    void findById() {

        Crc crc = new Crc();
        crc.setId(1L);
        Optional<Crc> crcOptional = Optional.of(crc);
        when(crcRepository.findById(anyLong())).thenReturn(crcOptional);

        Crc crcFind = crcService.findById(1L);
        assertNotNull(crcFind);
        assertEquals(ID, crcFind.getId());
    }

    @Test
    void findCrcCommandAll() {
        Set<Crc> crcSet = new HashSet<>();
        Crc crc = new Crc();
        crc.setId(1L);
        crcSet.add(crc);

        CrcCommand crcCommand = new CrcCommand();
        crcCommand.setId(1L);

        when(crcRepository.findAll()).thenReturn(crcSet);
        when(crcToCrcCommand.convert(any(Crc.class))).thenReturn(crcCommand);

        Set<CrcCommand> crcCommandSet = crcService.findCrcCommandAll();

        assertEquals(1, crcCommandSet.size());
        verify(crcRepository, times(1)).findAll();
        verify(crcToCrcCommand, times(1)).convert(any(Crc.class));

    }

    @Test
    void findCrcCommandById() {
        Crc crc = new Crc();
        crc.setId(ID);
        CrcCommand crcCommand = new CrcCommand();
        crcCommand.setId(ID);

        when(crcRepository.findById(anyLong())).thenReturn(Optional.of(crc));
        when(crcToCrcCommand.convert(any(Crc.class))).thenReturn(crcCommand);

        CrcCommand crcCommandById = crcService.findCrcCommandById(1L);

        assertNotNull(crcCommandById);
        assertEquals(ID,crcCommandById.getId());
        verify(crcToCrcCommand,times(1)).convert(any(Crc.class));
        verify(crcRepository,times(1)).findById(anyLong());
    }
}