package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.command.CrcCommand;
import com.portfolio.errorscorrection.model.Crc;

import java.util.Set;

public interface CrcService {
    Crc findById(Long id);
    Set<CrcCommand> findCrcCommandAll();
    CrcCommand findCrcCommandById(Long id);
}
