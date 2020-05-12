package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.model.Message;

public interface VerificationService {
    int verifyHamming(Message message);

    int verifyCrc(Message message, Crc crc);
}
