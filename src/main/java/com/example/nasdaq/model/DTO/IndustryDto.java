package com.example.nasdaq.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IndustryDto {
    private String industry;

    private String dailydate;

    private double avgMarketCap;

    private double avgPER;

    private double avgPSR;

    private double avgPBR;

    private double avgEV_EBITDA;

    private double avgFluc;
}
