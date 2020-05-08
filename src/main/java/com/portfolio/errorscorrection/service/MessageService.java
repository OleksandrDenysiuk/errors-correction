package com.portfolio.errorscorrection.service;

import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.model.Message;

public interface MessageService {
    Message generate(String text, Crc crc);
}
