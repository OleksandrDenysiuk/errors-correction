package com.portfolio.errorscorrection.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CrcCommand{

    private Long id;
    private String name;
    private String bitLength;
    private boolean inputReflected;
    private boolean resultReflected;
    private String polynomial;
    private String initialValue;
    private String finalXorValue;
}
