package com.portfolio.errorscorrection.wrapper;

import com.portfolio.errorscorrection.dto.CrcDto;
import com.portfolio.errorscorrection.model.Bit;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimulationWrapper {
    List<Bit> bits;
    CrcDto crcDto;
}
