package com.portfolio.errorscorrection.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Crc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int bitLength;
    private boolean inputReflected;
    private boolean resultReflected;
    private int polynomial;
    private int initialValue;
    private int finalXorValue;
}
