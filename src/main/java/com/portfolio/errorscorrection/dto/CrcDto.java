package com.portfolio.errorscorrection.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrcDto {

    private Long id;
    private String name;
    private String bitLength;
    private boolean inputReflected;
    private boolean resultReflected;
    private String polynomial;
    private String initialValue;
    private String finalXorValue;
}
