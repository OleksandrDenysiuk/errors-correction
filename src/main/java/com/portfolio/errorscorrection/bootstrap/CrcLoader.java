package com.portfolio.errorscorrection.bootstrap;

import com.portfolio.errorscorrection.model.Crc;
import com.portfolio.errorscorrection.repository.CrcRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class CrcLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final CrcRepository crcRepository;

    public CrcLoader(CrcRepository crcRepository) {
        this.crcRepository = crcRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Crc crc1 = new Crc();
        crc1.setName("Crc16");
        crc1.setBitLength(16);
        crc1.setInputReflected(false);
        crc1.setResultReflected(false);
        crc1.setPolynomial(0x1021);
        crc1.setInitialValue(0x0);
        crc1.setFinalXorValue(0x0);

        crcRepository.save(crc1);

        Crc crc2 = new Crc();
        crc2.setName("Crc32_Q");
        crc2.setBitLength(32);
        crc2.setInputReflected(false);
        crc2.setResultReflected(false);
        crc2.setPolynomial(0x814141AB);
        crc2.setInitialValue(0x0);
        crc2.setFinalXorValue(0x0);

        crcRepository.save(crc2);


    }
}
